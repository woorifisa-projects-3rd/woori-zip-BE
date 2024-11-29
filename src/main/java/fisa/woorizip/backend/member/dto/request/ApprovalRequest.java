package fisa.woorizip.backend.member.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ApprovalRequest {

    @NotNull
    private List<Long> admins;

    public ApprovalRequest(List<Long> admins) {
        this.admins = admins;
    }

    protected ApprovalRequest() {
    }
}
