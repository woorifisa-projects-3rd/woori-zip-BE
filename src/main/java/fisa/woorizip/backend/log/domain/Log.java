package fisa.woorizip.backend.log.domain;

import fisa.woorizip.backend.member.domain.Member;

import jakarta.persistence.*;

import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "request_body", nullable = false)
    private String requestBody;

    @Column(name = "response_status", nullable = false)
    private String responseStatus;

    @Column(name = "response_body", nullable = false)
    private String responseBody;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
