package fisa.woorizip.backend.memberconsumption.dto.response;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;
import fisa.woorizip.backend.memberconsumption.domain.MemberConsumption;

import lombok.Getter;

@Getter
public class ConsumptionResponse {
    private double book;
    private double car;
    private double cloth;
    private double culture;
    private double food;
    private double grocery;

    private ConsumptionResponse(
            double book,
            double car,
            double cloth,
            double culture,
            double food,
            double grocery) {
        this.book = book;
        this.car = car;
        this.cloth = cloth;
        this.culture = culture;
        this.food = food;
        this.grocery = grocery;
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
