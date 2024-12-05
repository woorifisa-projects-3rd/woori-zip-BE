package fisa.woorizip.backend.log.domain;

import fisa.woorizip.backend.member.domain.Member;

import jakarta.persistence.*;

import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = {AuditingEntityListener.class})
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "client_ip", nullable = false)
    private String clientIp;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_status", nullable = false)
    private String responseStatus;

    @Column(name = "response_body", nullable = false)
    private String responseBody;

    @Column(name = "is_success", nullable = false)
    private boolean isSuccess;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private Log(Long id, Member member, String clientIp, String requestUrl, String requestBody, String responseStatus, String responseBody, boolean isSuccess, LocalDateTime createdAt) {
        this.id = id;
        this.member = member;
        this.clientIp = clientIp;
        this.requestUrl = requestUrl;
        this.requestBody = isNull(requestBody) ? "" : requestBody;
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
        this.isSuccess = isSuccess;
        this.createdAt = createdAt;
    }
}
