package fisa.woorizip.backend.house.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import lombok.RequiredArgsConstructor;

import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.QHouse.house;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;

@RequiredArgsConstructor
public class HouseRepositoryCustomImpl implements HouseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ShowMapResponse findHouseHighLevel(MapFilterRequest mapFilterRequest) {
        return ShowMapResponse.of(
                GU,
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        HouseCountResult.class, house.gu, house.count().intValue()))
                        .from(house)
                        .where(
                                house.latitude.between(
                                        mapFilterRequest.getSouthWestLatitude(),
                                        mapFilterRequest.getNorthEastLatitude()),
                                house.longitude.between(
                                        mapFilterRequest.getSouthWestLongitude(),
                                        mapFilterRequest.getNorthEastLongitude()),
                                houseTypeEq(mapFilterRequest.getHouseType()),
                                housingExpensesEq(mapFilterRequest.getHousingExpenses()),
                                house.deposit.between(
                                        mapFilterRequest.getMinDeposit(),
                                        mapFilterRequest.getMaxDeposit()),
                                house.monthlyRentFee.between(
                                        mapFilterRequest.getMinMonthlyRentFee(),
                                        mapFilterRequest.getMaxMonthlyRentFee()),
                                house.maintenanceFee.between(
                                        mapFilterRequest.getMinMaintenanceFee(),
                                        mapFilterRequest.getMaxMaintenanceFee()))
                        .groupBy(house.gu)
                        .orderBy(house.gu.asc())
                        .fetch(),
                jpaQueryFactory
                        .select(house)
                        .from(house)
                        .where(
                                house.latitude.between(
                                        mapFilterRequest.getSouthWestLatitude(),
                                        mapFilterRequest.getNorthEastLatitude()),
                                house.longitude.between(
                                        mapFilterRequest.getSouthWestLongitude(),
                                        mapFilterRequest.getNorthEastLongitude()),
                                houseTypeEq(mapFilterRequest.getHouseType()),
                                housingExpensesEq(mapFilterRequest.getHousingExpenses()),
                                house.deposit.between(
                                        mapFilterRequest.getMinDeposit(),
                                        mapFilterRequest.getMaxDeposit()),
                                house.monthlyRentFee.between(
                                        mapFilterRequest.getMinMonthlyRentFee(),
                                        mapFilterRequest.getMaxMonthlyRentFee()),
                                house.maintenanceFee.between(
                                        mapFilterRequest.getMinMaintenanceFee(),
                                        mapFilterRequest.getMaxMaintenanceFee()))
                        .limit(15)
                        .fetch()
                        .stream()
                        .map(HouseContentResult::init)
                        .toList());
    }

    private BooleanExpression houseTypeEq(final HouseType houseType) {
        if (houseType.equals(ALL)) return null;
        return house.houseType.eq(houseType);
    }

    private BooleanExpression housingExpensesEq(final HousingExpenses housingExpenses) {
        if (housingExpenses.equals(HousingExpenses.ANY)) return null;
        return house.housingExpenses.eq(housingExpenses);
    }
}
