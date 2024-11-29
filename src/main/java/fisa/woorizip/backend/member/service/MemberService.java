package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.dto.response.MemberInfoResponse;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest, Role role);

    void validateAlreadyExistUsername(String username);

    MemberInfoResponse getMemberInfo(MemberIdentity memberIdentity);
}
