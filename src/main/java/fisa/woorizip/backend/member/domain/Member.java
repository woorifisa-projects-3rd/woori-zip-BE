package fisa.woorizip.backend.member.domain;

import static fisa.woorizip.backend.member.domain.LifeStage.NONE_LIFE_STAGE;
import static fisa.woorizip.backend.member.domain.Membership.NONE_MEMBERSHIP;

import static java.util.Objects.isNull;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "license_id", nullable = false)
    private String licenseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "membership",
            nullable = false,
            columnDefinition = "VARCHAR(255) DEFAULT 'NONE_MEMBERSHIP'")
    private Membership membership;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "life_stage",
            nullable = false,
            columnDefinition = "VARCHAR(255) DEFAULT 'NONE_LIFE_STAGE'")
    private LifeStage lifeStage;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Builder
    private Member(
            Long id,
            String username,
            String password,
            String customerId,
            String licenseId,
            Status status,
            String name,
            LocalDate birthday,
            Gender gender,
            Membership membership,
            LifeStage lifeStage,
            Role role) {
        this.id = id;
        this.username = isNull(username) ? "" : username;
        this.password = isNull(password) ? "" : password;
        this.customerId = isNull(customerId) ? "" : customerId;
        this.licenseId = isNull(licenseId) ? "" : licenseId;
        this.status = status;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.membership = isNull(membership) ? NONE_MEMBERSHIP : membership;
        this.lifeStage = isNull(lifeStage) ? NONE_LIFE_STAGE : lifeStage;
        this.role = role;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public void revokeApproval() {
        this.status = Status.REVOKED_APPROVAL;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    private void updateBirthDay(LocalDate birthday) {
        if(!isNull(birthday)) this.birthday = birthday;
    }

    private void updateGender(Gender gender) {
        if(!isNull(gender)) this.gender = gender;
    }

    public void updateProfile(Member member) {
        updateBirthDay(member.getBirthday());
        updateGender(member.getGender());
    }
}
