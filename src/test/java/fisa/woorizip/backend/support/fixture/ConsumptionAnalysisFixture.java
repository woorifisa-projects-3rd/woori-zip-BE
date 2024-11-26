package fisa.woorizip.backend.support.fixture;

import fisa.woorizip.backend.consumptionanalysis.domain.ConsumptionAnalysis;

public class ConsumptionAnalysisFixture {
    private Long id;
    private String customerType = "20_1_23_NEW_JOB";
    private double book = 1.1741682974559686;
    private double car = 5.786972323175845;
    private double cloth = 6.402012859938496;
    private double culture = 3.075202683813252;
    private double food = 70.03075202683813;
    private double grocery = 13.530891808778303;
    private long customerCount = 661;

    public static ConsumptionAnalysisFixture builder() {
        return new ConsumptionAnalysisFixture();
    }

    public ConsumptionAnalysisFixture id(Long id) {
        this.id = id;
        return this;
    }

    public ConsumptionAnalysisFixture customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public ConsumptionAnalysisFixture book(double book) {
        this.book = book;
        return this;
    }

    public ConsumptionAnalysisFixture car(double car) {
        this.car = car;
        return this;
    }

    public ConsumptionAnalysisFixture cloth(double cloth) {
        this.cloth = cloth;
        return this;
    }

    public ConsumptionAnalysisFixture culture(double culture) {
        this.culture = culture;
        return this;
    }

    public ConsumptionAnalysisFixture food(double food) {
        this.food = food;
        return this;
    }

    public ConsumptionAnalysisFixture grocery(double grocery) {
        this.grocery = grocery;
        return this;
    }

    public ConsumptionAnalysisFixture customerCount(long customerCount) {
        this.customerCount = customerCount;
        return this;
    }

    public ConsumptionAnalysis build() {
        return ConsumptionAnalysis.builder()
                .id(id)
                .customerType(customerType)
                .book(book)
                .car(car)
                .cloth(cloth)
                .culture(culture)
                .food(food)
                .grocery(grocery)
                .customerCount(customerCount)
                .build();
    }
}
