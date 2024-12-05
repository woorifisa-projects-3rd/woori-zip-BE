package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumptionAnalysisResponse {
    private ConsumptionResponse memberConsumption;
    private ConsumptionResponse otherConsumption;
    private CategoryResponse bestCategory;

    private ConsumptionAnalysisResponse(
            ConsumptionResponse memberConsumption,
            ConsumptionResponse otherConsumption,
            CategoryResponse bestCategory) {
        this.memberConsumption = memberConsumption;
        this.otherConsumption = otherConsumption;
        this.bestCategory = bestCategory;
    }

    public static ConsumptionAnalysisResponse of(
            ConsumptionAnalysis consumptionAnalysis,
            MemberConsumption memberConsumption,
            CategoryResponse bestCategory) {
        return new ConsumptionAnalysisResponse(
                ConsumptionResponse.from(memberConsumption),
                ConsumptionResponse.from(consumptionAnalysis),
                bestCategory);
    }
}
