package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.facility.domain.Category;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoryResponse {
    private Category category;
    private BigDecimal subtract;
    private BigDecimal memberValue;

    private CategoryResponse(Category category, BigDecimal subtract, BigDecimal memberValue) {
        this.category = category;
        this.subtract = subtract;
        this.memberValue = memberValue;
    }

    public static CategoryResponse of(
            Category category, BigDecimal subtract, BigDecimal memberValue) {
        return new CategoryResponse(category, subtract, memberValue);
    }
}
