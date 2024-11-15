package fisa.woorizip.backend.house.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.dto.result.HouseResult;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.QHouse.house;
import static fisa.woorizip.backend.house.dto.HouseAddressType.DONG;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;

@RequiredArgsConstructor
public class HouseRepositoryCustomImpl implements HouseRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final int HOUSE_LIST_COUNT = 15;

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
                createHouseStream(mapFilterRequest).map(HouseContentResult::init).toList());
    }

    @Override
    public ShowMapResponse findHouseMidLevel(MapFilterRequest mapFilterRequest) {
        return ShowMapResponse.of(
                DONG,
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        HouseCountResult.class,
                                        house.dong,
                                        house.count().intValue()))
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
                        .groupBy(house.dong)
                        .orderBy(house.dong.asc())
                        .fetch(),
                createHouseStream(mapFilterRequest).map(HouseContentResult::init).toList());
    }

    @Override
    public ShowMapResponse findHouseLowLevel(MapFilterRequest mapFilterRequest) {
        return ShowMapResponse.of(
                createHouseStream(mapFilterRequest).map(HouseResult::init).toList(),
                createHouseStream(mapFilterRequest).map(HouseContentResult::init).toList());
    }

    private Stream<House> createHouseStream(MapFilterRequest mapFilterRequest) {
        return jpaQueryFactory
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
                                mapFilterRequest.getMinDeposit(), mapFilterRequest.getMaxDeposit()),
                        house.monthlyRentFee.between(
                                mapFilterRequest.getMinMonthlyRentFee(),
                                mapFilterRequest.getMaxMonthlyRentFee()),
                        house.maintenanceFee.between(
                                mapFilterRequest.getMinMaintenanceFee(),
                                mapFilterRequest.getMaxMaintenanceFee()))
                .limit(HOUSE_LIST_COUNT)
                .fetch()
                .stream();
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
