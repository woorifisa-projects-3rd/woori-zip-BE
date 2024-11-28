package fisa.woorizip.backend.member.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class RevokeApprovalRequest {

    @NotNull private List<Long> admins;

    public RevokeApprovalRequest(List<Long> admins) {
        this.admins = admins;
    }

    protected RevokeApprovalRequest() {
    }
}
