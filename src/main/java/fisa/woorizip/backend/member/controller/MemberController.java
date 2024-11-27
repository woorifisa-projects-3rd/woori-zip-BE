package fisa.woorizip.backend.member.controller;

import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.dto.request.SignUpRequest;
import fisa.woorizip.backend.member.service.MemberService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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
            @RequestBody @Valid SignUpRequest signUpRequest, @RequestParam Role role) {
        memberService.signUp(signUpRequest, role);
        return ResponseEntity.created(URI.create("/api/v1/sign-in")).build();
    }

    @GetMapping("/members/valid")
    public ResponseEntity<Void> validateUsername(@RequestParam(name = "username") String username) {
        memberService.validateAlreadyExistUsername(username);
        return ResponseEntity.ok().build();
    }
}
