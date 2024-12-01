package fisa.woorizip.backend.member.dto.response;

import fisa.woorizip.backend.member.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponse {

    private Long id;
    private String username;
    private String name;
    private String status;

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .status(member.getStatus().getDescription())
                .build();
    }
}
