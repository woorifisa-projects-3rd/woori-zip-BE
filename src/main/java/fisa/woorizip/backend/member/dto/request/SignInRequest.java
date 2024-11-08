package fisa.woorizip.backend.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public SignInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    protected SignInRequest() {
    }
}