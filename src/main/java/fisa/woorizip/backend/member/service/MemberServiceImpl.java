package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
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

    @Override
    @Transactional
    public void signUp(SignUpRequest signUpRequest, Role role) {
        Member member =
                signUpRequest.toMember(passwordEncoder.encode(signUpRequest.getPassword()), role);
        memberRepository.save(member);
    }
}
