package fisa.woorizip.backend.member.service.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpendingHistoryResponse {

    private double culture;
    private double book;
    private double grocery;
    private double cloth;
    private double food;
    private double car;

}
