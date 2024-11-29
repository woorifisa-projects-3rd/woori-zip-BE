package fisa.woorizip.backend.member.controller;

import static fisa.woorizip.backend.member.domain.Role.ADMIN;

import fisa.woorizip.backend.member.controller.auth.Login;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.ApprovalRequest;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.service.MemberService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
