package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.member.domain.*;

import java.time.LocalDate;

public class MemberFixture {

    private Long id;
    private String username = "rlfrkdms1";
    private String password = "password12!@";
    private String name = "길가은";
    private Gender gender = Gender.MALE;
    private Membership membership = Membership.PLATINUM;
    private LifeStage lifeStage = LifeStage.NEW_JOB;
    private Role role = Role.MEMBER;
    private Status status = Status.NOT_ADMIN;
    private long availableAssets = 1000000;
    private LocalDate birthday = LocalDate.of(2000, 1, 1);

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

    public MemberFixture gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public MemberFixture membership(Membership membership) {
        this.membership = membership;
        return this;
    }

    public MemberFixture lifeStage(LifeStage lifeStage) {
        this.lifeStage = lifeStage;
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

    public MemberFixture role(Role role) {
        this.role = role;
        return this;
    }

    public MemberFixture status(Status status) {
        this.status = status;
        return this;
    }
  
    public MemberFixture availableAssets(long availableAssets) {
        this.availableAssets = availableAssets;
        return this;
    }

    public MemberFixture birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Member build() {
        return Member.builder()
                .id(id)
                .username(username)
                .name(name)
                .gender(gender)
                .membership(membership)
                .lifeStage(lifeStage)
                .password(password)
                .role(role)
                .birthday(birthday)
                .status(status)
                .availableAssets(availableAssets)
                .build();
    }
}
