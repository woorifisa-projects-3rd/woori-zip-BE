package fisa.woorizip.backend.member.dto.response;

import fisa.woorizip.backend.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ShowMembersResponse {

    private int page;
    private int totalPages;
    private List<MemberResponse> members;

    public static ShowMembersResponse of(Page<Member> members) {
        return ShowMembersResponse.builder()
                .page(members.getNumber())
                .totalPages(members.getTotalPages())
                .members(members.getContent().stream().map(MemberResponse::from).toList())
                .build();
    }

}
