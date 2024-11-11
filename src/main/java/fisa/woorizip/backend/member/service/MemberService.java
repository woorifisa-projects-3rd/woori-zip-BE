package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest, Role role);
    void validateAlreadyExistUsername(String username);

}
