package fisa.woorizip.backend.memberconsumption.service;

import static fisa.woorizip.backend.facility.domain.Category.*;
import static fisa.woorizip.backend.member.AuthErrorCode.INSUFFICIENT_PERMISSIONS;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;
import static fisa.woorizip.backend.member.domain.Role.MEMBER;
import static fisa.woorizip.backend.memberconsumption.MemberConsumptionErrorCode.CUSTOMER_TYPE_NOT_FOUND;
import static fisa.woorizip.backend.memberconsumption.MemberConsumptionErrorCode.MEMBER_CONSUMPTION_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.consumptionanalysis.repository.ConsumptionAnalysisRepository;
import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import fisa.woorizip.backend.memberconsumption.dto.Age;
import fisa.woorizip.backend.memberconsumption.dto.response.CategoryResponse;
import fisa.woorizip.backend.memberconsumption.dto.response.ConsumptionAnalysisResponse;
import fisa.woorizip.backend.memberconsumption.repository.MemberConsumptionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class MemberConsumptionServiceImpl implements MemberConsumptionService {

    private final MemberConsumptionRepository memberConsumptionRepository;
    private final MemberRepository memberRepository;
    private final ConsumptionAnalysisRepository consumptionAnalysisRepository;

    @Override
    @Transactional(readOnly = true)
    public ConsumptionAnalysisResponse getConsumptionAnalysis(MemberIdentity memberIdentity) {
        Member member = getMember(memberIdentity);
        ConsumptionAnalysis consumptionAnalysis = getConsumptionAnalysis(member);
        MemberConsumption memberConsumption = getMemberConsumption(member.getId());
        CategoryResponse bestCategory = getBestCategory(consumptionAnalysis, memberConsumption);
        return ConsumptionAnalysisResponse.of(consumptionAnalysis, memberConsumption, bestCategory);
    }

    private Member getMember(MemberIdentity memberIdentity) {
        if (memberIdentity.getRole() != MEMBER)
            throw new WooriZipException(INSUFFICIENT_PERMISSIONS);
        return memberRepository
                .findById(memberIdentity.getId())
                .orElseThrow(() -> new WooriZipException(MEMBER_NOT_FOUND));
    }

    private String getCustomerType(Member member) {
        return Age.from(Period.between(member.getBirthday(), LocalDate.now()).getYears())
                        .getMinAge()
                + "_"
                + member.getGender().getValue()
                + "_"
                + member.getMembership().getValue()
                + "_"
                + member.getLifeStage().toString();
    }

    private ConsumptionAnalysis getConsumptionAnalysis(Member member) {
        return consumptionAnalysisRepository
                .findByCustomerType(getCustomerType(member))
                .orElseThrow(() -> new WooriZipException(CUSTOMER_TYPE_NOT_FOUND));
    }

    private MemberConsumption getMemberConsumption(Long memberId) {
        return memberConsumptionRepository
                .findByMemberId(memberId)
                .orElseThrow(() -> new WooriZipException(MEMBER_CONSUMPTION_NOT_FOUND));
    }

    private CategoryResponse getBestCategory(
            ConsumptionAnalysis consumptionAnalysis, MemberConsumption memberConsumption) {
        CategoryResponse[] categories = getCategorySubtract(consumptionAnalysis, memberConsumption);
        Arrays.sort(
                categories,
                Comparator.comparing(CategoryResponse::getSubtract)
                        .thenComparing(CategoryResponse::getMemberValue));
        return categories[categories.length - 1];
    }

    private CategoryResponse[] getCategorySubtract(
            ConsumptionAnalysis other, MemberConsumption member) {
        return new CategoryResponse[] {
            createCategoryResponse(BOOK, member.getBook(), other.getBook()),
            createCategoryResponse(CAR, member.getCar(), other.getCar()),
            createCategoryResponse(CLOTH, member.getCloth(), other.getCloth()),
            createCategoryResponse(CULTURE, member.getCulture(), other.getCulture()),
            createCategoryResponse(FOOD, member.getFood(), other.getFood()),
            createCategoryResponse(GROCERY, member.getGrocery(), other.getGrocery())
        };
    }

    private CategoryResponse createCategoryResponse(
            Category category, double memberValue, double otherValue) {
        return CategoryResponse.of(
                category,
                BigDecimal.valueOf(memberValue).subtract(BigDecimal.valueOf(otherValue)),
                BigDecimal.valueOf(memberValue));
    }
}
