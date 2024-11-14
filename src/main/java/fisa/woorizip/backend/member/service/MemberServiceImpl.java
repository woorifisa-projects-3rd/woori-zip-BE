package fisa.woorizip.backend.member.service;

import static fisa.woorizip.backend.member.MemberErrorCode.ALREADY_EXIST_USERNAME;

import fisa.woorizip.backend.common.exception.WooriZipException;
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

}
