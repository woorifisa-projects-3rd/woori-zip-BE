package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import jakarta.validation.Valid;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest, Role role);

    void validateAlreadyExistUsername(String username);

    void revokeApprovals(@Valid RevokeApprovalRequest revokeApprovalRequest);
}
