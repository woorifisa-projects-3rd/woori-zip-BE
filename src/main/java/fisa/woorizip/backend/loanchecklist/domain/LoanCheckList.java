package fisa.woorizip.backend.loanchecklist.domain;

import fisa.woorizip.backend.loangoods.domain.LoanGoodsType;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @Column(name = "content", nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "loan_goods_type", nullable = false)
    private LoanGoodsType loanGoodsType;
}
