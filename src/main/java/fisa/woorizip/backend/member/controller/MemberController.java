package fisa.woorizip.backend.member.controller;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;
import static fisa.woorizip.backend.member.domain.Role.AGENT;
import static fisa.woorizip.backend.member.domain.Role.MEMBER;

import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.controller.auth.VerifiedMember;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.ApprovalRequest;
import fisa.woorizip.backend.member.dto.request.MemberProfileRequest;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.dto.response.MemberProfileResponse;
import fisa.woorizip.backend.member.dto.response.ShowMembersResponse;
import fisa.woorizip.backend.member.service.MemberService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody @Valid SignUpRequest signUpRequest, @RequestParam("role") Role role) {
        memberService.signUp(signUpRequest, role);
        return ResponseEntity.created(URI.create("/api/v1/sign-in")).build();
    }

    @GetMapping("/members/valid")
    public ResponseEntity<Void> validateUsername(@RequestParam(name = "username") String username) {
        memberService.validateAlreadyExistUsername(username);
        return ResponseEntity.ok().build();
    }

    @Login(role = ADMIN)
    @DeleteMapping("/admins")
    public ResponseEntity<Void> revokeApproval(
            @RequestBody @Valid RevokeApprovalRequest revokeApprovalRequest) {
        memberService.revokeApprovals(revokeApprovalRequest);
        return ResponseEntity.ok().build();
    }

    @Login(role = ADMIN)
    @PostMapping("/admins")
    public ResponseEntity<Void> approve(@RequestBody @Valid ApprovalRequest approvalRequest) {
        memberService.approve(approvalRequest);
        return ResponseEntity.ok().build();
    }

    @Login(role = {MEMBER, AGENT})
    @GetMapping("/members/profile")
    public MemberProfileResponse showMemberInfo(@VerifiedMember MemberIdentity memberIdentity) {
        return memberService.getMemberProfile(memberIdentity);
    }

    @Login(role = ADMIN)
    @GetMapping("/members")
    public ShowMembersResponse showMembers(
            @RequestParam("role") Role role,
            Pageable pageable,
            @VerifiedMember MemberIdentity memberIdentity) {
        return memberService.getMembers(role, pageable);
    }

    @Login(role = AGENT)
    @PutMapping("/members/profile")
    public ResponseEntity<Void> updateProfile(
            @VerifiedMember MemberIdentity memberIdentity,
            @RequestBody @Valid MemberProfileRequest memberProfileRequest) {
        memberService.updateProfile(memberIdentity, memberProfileRequest);
        return ResponseEntity.ok().build();
    }
}
