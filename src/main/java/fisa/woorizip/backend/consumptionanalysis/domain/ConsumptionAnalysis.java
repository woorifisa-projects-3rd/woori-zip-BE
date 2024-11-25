package fisa.woorizip.backend.consumptionanalysis.domain;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConsumptionAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @Column(name = "book", nullable = false)
    private double book;

    @Column(name = "car", nullable = false)
    private double car;

    @Column(name = "cloth", nullable = false)
    private double cloth;

    @Column(name = "culture", nullable = false)
    private double culture;

    @Column(name = "food", nullable = false)
    private double food;

    @Column(name = "grocery", nullable = false)
    private double grocery;

    @Column(name = "customer_count", nullable = false)
    private int customerCount;
}
