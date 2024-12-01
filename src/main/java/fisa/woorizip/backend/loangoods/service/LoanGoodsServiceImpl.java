package fisa.woorizip.backend.loangoods.service;

import static fisa.woorizip.backend.loangoods.LoanGoodsErrorCode.LOANGOODS_NOT_FOUND;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.domain.LoanType;
import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsDetailResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.dto.response.RateResponse;
import fisa.woorizip.backend.rate.repository.RateRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;
    private final RateRepository rateRepository;

    @Override
    @Transactional(readOnly = true)
    public ShowLoanGoodsDetailResponse getLoanGoodsDetailsById(Long loanGoodsId) {

        LoanGoods loanGoods =
                loanGoodsRepository
                        .findById(loanGoodsId)
                        .orElseThrow(() -> new WooriZipException(LOANGOODS_NOT_FOUND));

        List<RateResponse> rateList = new ArrayList<>();
        if (loanGoods.getLoanType() == LoanType.LOAN_LEASE_DEPOSIT) {
            List<Rate> rates = rateRepository.findByLoanGoodsIdWithFetchJoin(loanGoods);
            rateList = rates.stream().map(RateResponse::from).toList();
        }

        return ShowLoanGoodsDetailResponse.of(loanGoods, rateList);
    }
}
