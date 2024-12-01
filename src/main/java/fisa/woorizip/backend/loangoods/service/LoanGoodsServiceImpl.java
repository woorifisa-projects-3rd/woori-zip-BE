package fisa.woorizip.backend.loangoods.service;

import fisa.woorizip.backend.loangoods.dto.response.ShowLoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanGoodsServiceImpl implements LoanGoodsService {

    private final LoanGoodsRepository loanGoodsRepository;

    @Override
    public ShowLoanGoodsResponse getLoanGoods(Pageable pageable) {
        return ShowLoanGoodsResponse.from(loanGoodsRepository.findAllBy(pageable));
    }
}
