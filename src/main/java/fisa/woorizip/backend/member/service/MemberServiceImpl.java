package fisa.woorizip.backend.member.service;

import static fisa.woorizip.backend.member.MemberErrorCode.*;

import static java.util.Objects.isNull;

import fisa.woorizip.backend.agent.AgentRepository;
import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.dto.response.MemberInfoResponse;
import fisa.woorizip.backend.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(MemberIdentity memberIdentity) {
        return MemberInfoResponse.from(findMemberById(memberIdentity.getId()));
    }

    private Member findMemberById(Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }
}
