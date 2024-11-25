package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.facility.domain.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoryResponse {
    private Category category;
    private BigDecimal subtract;

    private CategoryResponse(Category category, BigDecimal subtract) {
        this.category = category;
        this.subtract = subtract;
    }

    public static CategoryResponse of(Category category, BigDecimal subtract) {
        return new CategoryResponse(category, subtract);
    }
}
