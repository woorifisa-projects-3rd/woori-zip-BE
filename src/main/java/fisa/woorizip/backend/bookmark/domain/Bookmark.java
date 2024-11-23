package fisa.woorizip.backend.bookmark.domain;

import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.member.domain.Member;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    private Bookmark(Long id, Member member, House house, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.house = house;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }
}
