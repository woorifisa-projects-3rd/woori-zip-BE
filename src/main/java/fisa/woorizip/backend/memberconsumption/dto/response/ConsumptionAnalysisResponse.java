package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import lombok.Getter;

import java.util.List;

@Getter
public class ConsumptionAnalysisResponse {
    private CustomerTypeResponse customerType;
    private int customerTypeCount;
    private ConsumptionResponse memberConsumption;
    private ConsumptionResponse otherConsumption;
    private List<Category> memberCategories;
    private Category bestCategory;

    private ConsumptionAnalysisResponse(
            CustomerTypeResponse customerType,
            int customerTypeCount,
            ConsumptionResponse memberConsumption,
            ConsumptionResponse otherConsumption,
            List<Category> memberCategories,
            Category bestCategory) {
        this.customerType = customerType;
        this.customerTypeCount = customerTypeCount;
        this.memberConsumption = memberConsumption;
        this.otherConsumption = otherConsumption;
        this.memberCategories = memberCategories;
        this.bestCategory = bestCategory;
    }

    public static ConsumptionAnalysisResponse of(
            ConsumptionAnalysis consumptionAnalysis,
            MemberConsumption memberConsumption,
            List<String> categories) {
        return new ConsumptionAnalysisResponse(
                CustomerTypeResponse.from(memberConsumption.getMember()),
                consumptionAnalysis.getCustomerCount(),
                ConsumptionResponse.from(memberConsumption),
                ConsumptionResponse.from(consumptionAnalysis),
                categories.stream().map(Category::from).toList(),
                Category.from(categories.get(0)));
    }
}
