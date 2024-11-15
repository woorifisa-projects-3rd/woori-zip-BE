package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;

    @Override
    public LoanGoods showLoanGoodsDetailsById(Long id) {

        return loanGoodsRepository.findById(id).orElse(null);

    }
}
