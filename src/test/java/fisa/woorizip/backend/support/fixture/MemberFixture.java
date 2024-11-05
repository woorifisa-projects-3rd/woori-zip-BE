package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.member.domain.Member;

public class MemberFixture {

    private Long id;
    private String username = "rlfrkdms1@naver.com";
    private String password = "password12!@";
    private String name = "길가은";

    public static MemberFixture builder() {
        return new MemberFixture();
    }

    public MemberFixture id(Long id) {
        this.id = id;
        return this;
    }

    public MemberFixture name(String name) {
        this.name = name;
        return this;
    }

    public MemberFixture username(String username) {
        this.username = username;
        return this;
    }

    public MemberFixture password(String password) {
        this.password = password;
        return this;
    }

    public Member build() {
        return Member.builder()
                .id(id)
                .username(username)
                .name(name)
                .password(password)
                .build();
    }
}
