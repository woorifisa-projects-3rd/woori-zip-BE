package fisa.woorizip.backend.member.dto.request;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ApprovalRequest {

    @NotNull private List<Long> admins;

    public ApprovalRequest(List<Long> admins) {
        this.admins = admins;
    }

    protected ApprovalRequest() {}
}
