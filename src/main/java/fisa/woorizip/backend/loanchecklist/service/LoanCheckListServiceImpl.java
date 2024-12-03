package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.loanchecklist.dto.request.RecommendLoanFilter;
import fisa.woorizip.backend.loanchecklist.dto.request.RecommendMemberInfoFilter;
import fisa.woorizip.backend.loangoods.domain.LoanGoods;
import fisa.woorizip.backend.loangoods.dto.response.LoanGoodsResponse;
import fisa.woorizip.backend.loangoods.repository.LoanGoodsRepository;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.loanchecklist.repository.LoanChecklistRepository;
import fisa.woorizip.backend.rate.domain.Rate;
import fisa.woorizip.backend.rate.dto.response.RateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.member.MemberErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService{

    private final MemberRepository memberRepository;
    private final HouseRepository houseRepository;
    private final LoanChecklistRepository loanCheckListRepository;

    @Override
    public List<LoanGoodsResponse> getLoanGoodsRecommendByMemberIdAndHouseId(Long houseId) {


        House house = getHouse(houseId);
        List<LoanGoods> recommendLoanGoods = loanCheckListRepository.findRecommendLoanGoodsFirstTime(RecommendLoanFilter.of(house));

    return recommendLoanGoods
            .stream()
            .map(LoanGoodsResponse::from)
            .toList();

    }

    private House getHouse(Long houseId) {
        House house = houseRepository
                .findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
        return house;
    }


    @Override
    public List<LoanGoodsResponse> getLoanGoodsRecommend(RecommendMemberInfoFilter recommendMemberInfoFilter, MemberIdentity memberIdentity, Long houseId) {
        House house = getHouse(houseId);
        List<LoanGoods> recommendLoanGoods = loanCheckListRepository.findRecommendLoanGoods(RecommendLoanFilter.of(house),recommendMemberInfoFilter);

        return recommendLoanGoods
                .stream()
                .map(LoanGoodsResponse::from)
                .toList();
    }
}
