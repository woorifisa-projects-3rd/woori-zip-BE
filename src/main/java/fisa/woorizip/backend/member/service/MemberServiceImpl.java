package fisa.woorizip.backend.member.service;

import static fisa.woorizip.backend.member.MemberErrorCode.*;
import static fisa.woorizip.backend.member.MemberErrorCode.ADMINS_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.AGENT_LICENSE_ID_IS_NULL;
import static fisa.woorizip.backend.member.MemberErrorCode.AGENT_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.ALREADY_EXIST_USERNAME;
import static fisa.woorizip.backend.member.MemberErrorCode.NOT_ADMINS;
import static fisa.woorizip.backend.member.MemberErrorCode.NOT_APPROVED_ADMINS;
import static fisa.woorizip.backend.member.MemberErrorCode.NOT_PENDING_APPROVAL_ADMINS;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.agent.AgentRepository;
import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.common.exception.WoorizipDetailException;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.domain.Status;
import fisa.woorizip.backend.member.dto.request.ApprovalRequest;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.dto.response.MemberInfoResponse;
import fisa.woorizip.backend.member.dto.response.ShowMembersResponse;
import fisa.woorizip.backend.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AgentRepository agentRepository;

    @Override
    @Transactional
    public void signUp(SignUpRequest signUpRequest, Role role) {
        validateAlreadyExistUsername(signUpRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        Member member = createMemberByRole(signUpRequest, role, encodedPassword);
        memberRepository.save(member);
    }

    private Member createMemberByRole(
            SignUpRequest signUpRequest, Role role, String encodedPassword) {
        if (role == Role.ADMIN) {
            return signUpRequest.toAdminMember(encodedPassword);
        }
        if (role == Role.AGENT) {
            validateLicenseIdIsNull(signUpRequest);
            validateExistAgent(signUpRequest);
            return signUpRequest.toAgentMember(encodedPassword);
        }
                throw new WooriZipException(NOT_ALLOWED_SIGN_UP);
        return signUpRequest.toMember(encodedPassword);
    }

    private void validateExistAgent(SignUpRequest signUpRequest) {
        if (!agentRepository.existsByNameAndLicenseId(
                signUpRequest.getName(), signUpRequest.getLicenseId())) {
            throw new WooriZipException(AGENT_NOT_FOUND);
        }
    }

    private void validateLicenseIdIsNull(SignUpRequest signUpRequest) {
        if (isNull(signUpRequest.getLicenseId())) {
            throw new WooriZipException(AGENT_LICENSE_ID_IS_NULL);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void validateAlreadyExistUsername(String username) {
        if (memberRepository.existsByUsername(username)) {
            throw new WooriZipException(ALREADY_EXIST_USERNAME);
        }
    }

    @Override
    @Transactional
    public void approve(ApprovalRequest approvalRequest) {
        List<Member> admins = memberRepository.findAllByIdIn(approvalRequest.getAdmins());
        validateExistAdmins(admins, approvalRequest.getAdmins());
        validateAreAdmin(admins);
        validatePendingApprovalAdmins(admins);
        admins.stream().forEach(admin -> admin.approve());
    }

    @Override
    @Transactional
    public void revokeApprovals(RevokeApprovalRequest revokeApprovalRequest) {
        List<Member> admins = memberRepository.findAllByIdIn(revokeApprovalRequest.getAdmins());
        validateExistAdmins(admins, revokeApprovalRequest.getAdmins());
        validateAreAdmin(admins);
        validateApprovedAdmins(admins);
        admins.stream().forEach(admin -> admin.revokeApproval());
    }

    private void validateApprovedAdmins(List<Member> admins) {
        String notApprovedAdmins =
                admins.stream()
                        .filter(admin -> admin.getStatus() != Status.APPROVED)
                        .map(admin -> String.format("(%d : %s)", admin.getId(), admin.getStatus()))
                        .collect(Collectors.joining(", "));
        if (!isNull(notApprovedAdmins) && !notApprovedAdmins.isBlank()) {
            throw new WoorizipDetailException(
                    NOT_APPROVED_ADMINS, new String[] {notApprovedAdmins});
        }
    }

    private void validatePendingApprovalAdmins(List<Member> admins) {
        String notPendingApprovalAdmins =
                admins.stream()
                        .filter(admin -> admin.getStatus() != Status.PENDING_APPROVAL)
                        .map(admin -> String.format("(%d : %s)", admin.getId(), admin.getStatus()))
                        .collect(Collectors.joining(", "));
        if (!isNull(notPendingApprovalAdmins) && !notPendingApprovalAdmins.isBlank()) {
            throw new WoorizipDetailException(
                    NOT_PENDING_APPROVAL_ADMINS, new String[] {notPendingApprovalAdmins});
        }
    }

    private void validateExistAdmins(List<Member> admins, List<Long> requestIds) {
        List<Long> adminIds = admins.stream().mapToLong(Member::getId).boxed().toList();
        List<Long> notExistIds =
                requestIds.stream().filter(requestId -> !adminIds.contains(requestId)).toList();
        if (!notExistIds.isEmpty()) {
            throw new WoorizipDetailException(
                    ADMINS_NOT_FOUND,
                    new String[] {
                        notExistIds.stream().map(String::valueOf).collect(Collectors.joining(", "))
                    });
        }
    }

    private void validateAreAdmin(List<Member> admins) {
        List<Member> notAdmins = admins.stream().filter(admin -> !admin.isAdmin()).toList();
        if (!notAdmins.isEmpty()) {
            throw new WoorizipDetailException(
                    NOT_ADMINS,
                    new String[] {
                        notAdmins.stream()
                                .map(member -> String.valueOf(member.getId()))
                                .collect(Collectors.joining(", "))
                    });
        }
    }

    @Override
    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(MemberIdentity memberIdentity) {
        return MemberInfoResponse.from(findMemberById(memberIdentity.getId()));
    }

    @Override
    @Transactional(readOnly = true)
    public ShowMembersResponse getMembers(Role role, Pageable pageable) {
        Page<Member> members = memberRepository.findAllByRole(role, pageable);
        return ShowMembersResponse.of(members);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }
}
