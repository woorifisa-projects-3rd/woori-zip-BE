package fisa.woorizip.backend.member.service;

import static fisa.woorizip.backend.member.MemberErrorCode.ADMINS_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.AGENT_LICENSE_ID_IS_NULL;
import static fisa.woorizip.backend.member.MemberErrorCode.AGENT_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.ALREADY_EXIST_USERNAME;
import static fisa.woorizip.backend.member.MemberErrorCode.NOT_ADMINS;
import static fisa.woorizip.backend.member.MemberErrorCode.NOT_ALLOWED_SIGN_UP;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.agent.AgentRepository;
import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.common.exception.WoorizipDetailException;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

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
    public void revokeApprovals(RevokeApprovalRequest revokeApprovalRequest) {
        List<Member> admins = memberRepository.findAdminsInIds(revokeApprovalRequest.getAdmins());
        validateExistAdmins(admins, revokeApprovalRequest.getAdmins());
        validateAreAdmin(admins);
        admins.stream().forEach(admin -> admin.revokeApproval());
    }

    private void validateExistAdmins(List<Member> admins, List<Long> requestIds) {
        List<Long> adminIds = admins.stream().mapToLong(Member::getId).boxed().toList();
        List<Long> notExistIds = requestIds.stream().filter(requestId -> !adminIds.contains(requestId)).toList();
        if (!notExistIds.isEmpty()) {
            throw new WoorizipDetailException(ADMINS_NOT_FOUND,
                    new String[]{notExistIds.stream().map(String::valueOf).collect(Collectors.joining(", "))});
        }
    }

    private void validateAreAdmin(List<Member> admins) {
        List<Member> notAdmins = admins.stream().filter(admin -> !admin.isAdmin()).toList();
        if(!notAdmins.isEmpty()) {
            throw new WoorizipDetailException(NOT_ADMINS,
                    new String[]{notAdmins.stream().map(member -> String.valueOf(member.getId())).collect(Collectors.joining(", "))});
        }
    }
}
