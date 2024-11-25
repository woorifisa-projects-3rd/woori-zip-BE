package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;
import lombok.Getter;

@Getter
public class ConsumptionResponse {
    private double bookConsumption;
    private double carConsumption;
    private double clothConsumption;
    private double cultureConsumption;
    private double foodConsumption;
    private double groceryConsumption;

    private ConsumptionResponse(
            double bookConsumption,
            double carConsumption,
            double clothConsumption,
            double cultureConsumption,
            double foodConsumption,
            double groceryConsumption) {
        this.bookConsumption = bookConsumption;
        this.carConsumption = carConsumption;
        this.clothConsumption = clothConsumption;
        this.cultureConsumption = cultureConsumption;
        this.foodConsumption = foodConsumption;
        this.groceryConsumption = groceryConsumption;
    }

    public static ConsumptionResponse from(ConsumptionAnalysis consumptionAnalysis) {
        return new ConsumptionResponse(
                consumptionAnalysis.getBook(),
                consumptionAnalysis.getCar(),
                consumptionAnalysis.getCloth(),
                consumptionAnalysis.getCulture(),
                consumptionAnalysis.getFood(),
                consumptionAnalysis.getGrocery());
    }

    public static ConsumptionResponse from(MemberConsumption memberConsumption) {
        return new ConsumptionResponse(
                memberConsumption.getBook(),
                memberConsumption.getCar(),
                memberConsumption.getCloth(),
                memberConsumption.getCulture(),
                memberConsumption.getFood(),
                memberConsumption.getGrocery());
    }
}
