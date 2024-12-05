package fisa.woorizip.backend.log.repository;

import static fisa.woorizip.backend.log.domain.QLog.log;

import static java.util.Objects.isNull;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import fisa.woorizip.backend.log.domain.Log;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogCustomRepositoryImpl implements LogCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Log> searchLogs(
            Long logId,
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable) {

        JPAQuery<Long> countQuery = jpaQueryFactory.select(log.count()).from(log).leftJoin(log.member);
        JPAQuery<Log> contentQuery = jpaQueryFactory.selectFrom(log).leftJoin(log.member).fetchJoin();

        Long count =
                searchLogsConditions(countQuery, logId, username, startDate, endDate).fetchOne();
        List<Log> content =
                searchLogsConditions(contentQuery, logId, username, startDate, endDate)
                        .orderBy(log.createdAt.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(content, pageable, count);
    }

    private <T> JPAQuery<T> searchLogsConditions(
            JPAQuery<T> query,
            Long logId,
            String username,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return query.where(
                usernameContains(username), betweenDate(startDate, endDate), idEqual(logId));
    }

    private BooleanExpression usernameContains(String username) {
        return isNull(username) ? null : log.member.username.contains(username);
    }

    private BooleanExpression idEqual(Long logId) {
        return isNull(logId) ? null : log.id.eq(logId);
    }

    private BooleanExpression betweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return isNull(startDate) || isNull(endDate)
                ? null
                : log.createdAt.between(startDate, endDate);
    }
}
