package fisa.woorizip.backend.member.dto.request;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RevokeApprovalRequest {

    @NotNull private List<Long> admins;

    public RevokeApprovalRequest(List<Long> admins) {
        this.admins = admins;
    }

    protected RevokeApprovalRequest() {}
}
