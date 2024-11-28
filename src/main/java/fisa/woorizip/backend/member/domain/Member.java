package fisa.woorizip.backend.member.domain;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Column(name = "available_assets", nullable = false)
    private long availableAssets;
}
