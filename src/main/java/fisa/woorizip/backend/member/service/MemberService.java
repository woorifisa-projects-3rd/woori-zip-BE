package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.ApprovalRequest;
import fisa.woorizip.backend.member.dto.request.ModifyMemberProfileRequest;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.dto.response.MemberProfileResponse;
import fisa.woorizip.backend.member.dto.response.ShowMembersResponse;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;

public interface MemberService {
    void signUp(SignUpRequest signUpRequest, Role role);

    void validateAlreadyExistUsername(String username);

    void revokeApprovals(@Valid RevokeApprovalRequest revokeApprovalRequest);

    void approve(@Valid ApprovalRequest approvalRequest);

    MemberProfileResponse getMemberProfile(MemberIdentity memberIdentity);

    ShowMembersResponse getMembers(Role role, Pageable pageable);

    void updateProfile(MemberIdentity memberIdentity, ModifyMemberProfileRequest memberProfileRequest);
}
