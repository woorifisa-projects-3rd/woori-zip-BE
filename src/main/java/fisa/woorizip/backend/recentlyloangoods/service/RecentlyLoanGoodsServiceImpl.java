package fisa.woorizip.backend.recentlyloangoods.service;

import fisa.woorizip.backend.recentlyloangoods.repository.RecentlyLoanGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecentlyLoanGoodsServiceImpl implements RecentlyLoanGoodsService {
    private final RecentlyLoanGoodsRepository recentlyLoanGoodsRepository;
}
