package fisa.woorizip.backend.log.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fisa.woorizip.backend.log.domain.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static fisa.woorizip.backend.log.domain.QLog.log;
import static fisa.woorizip.backend.member.domain.QMember.member;
import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class LogCustomRepositoryImpl implements LogCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Log> searchLogs(String username, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {

        JPAQuery<Long> countQuery = jpaQueryFactory.select(log.count()).from(log).join(log.member);
        JPAQuery<Log> contentQuery = jpaQueryFactory.selectFrom(log).join(log.member).fetchJoin();

        Long count = searchLogsConditions(countQuery, username, startDate, endDate).fetchOne();
        List<Log> content = searchLogsConditions(contentQuery, username, startDate, endDate)
                .orderBy(log.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, count);
    }

    private <T> JPAQuery<T> searchLogsConditions(JPAQuery<T> query, String username, LocalDateTime startDate, LocalDateTime endDate) {
        return query.where(usernameContains(username)
                .and(betweenDate(startDate, endDate)));
    }

    private BooleanExpression usernameContains(String username) {
        return isNull(username) ? null : log.member.username.contains(username);
    }

    private BooleanExpression betweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return isNull(startDate) || isNull(endDate) ? null : log.createdAt.between(startDate, endDate);
    }
}
