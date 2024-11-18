package fisa.woorizip.backend.house.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import static fisa.woorizip.backend.facility.domain.QFacility.facility;
import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.QHouse.house;
import static fisa.woorizip.backend.house.dto.HouseAddressType.DONG;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;
import static fisa.woorizip.backend.housefacilityrelation.domain.QHouseFacilityRelation.houseFacilityRelation;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import fisa.woorizip.backend.facility.dto.FacilityResult;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
                createHouseContents(mapFilterRequest));
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
                createHouseContents(mapFilterRequest));
    }

    @Override
    public ShowMapResponse findHouseLowLevel(MapFilterRequest mapFilterRequest) {
        return ShowMapResponse.of(
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
                        .fetch()
                        .stream()
                        .map(HouseResult::init)
                        .toList(),
                createHouseContents(mapFilterRequest));
    }

    @Override
    public ShowMapResponse findHouseHighLevelInCategory(MapFilterRequest mapFilterRequest) {
        List<Long> houseIdList = findHouseIdListInCategory(mapFilterRequest);
        return ShowMapResponse.of(
                GU,
                jpaQueryFactory
                        .select(
                                Projections.constructor(
                                        HouseCountResult.class, house.gu, house.count().intValue()))
                        .from(house)
                        .where(
                                house.id.in(houseIdList),
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
                createHouseContents(mapFilterRequest));
    }

    @Override
    public ShowMapResponse findHouseMidLevelInCategory(MapFilterRequest mapFilterRequest) {
        List<Long> houseIdList = findHouseIdListInCategory(mapFilterRequest);
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
                                house.id.in(houseIdList),
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
                createHouseContents(mapFilterRequest));
    }

    @Override
    public ShowMapResponse findHouseLowLevelInCategory(MapFilterRequest mapFilterRequest) {
        List<Long> houseIdList = findHouseIdListInCategory(mapFilterRequest);
        return ShowMapResponse.of(
                jpaQueryFactory
                        .selectFrom(house)
                        .leftJoin(houseFacilityRelation)
                        .on(houseFacilityRelation.house.id.eq(house.id))
                        .leftJoin(facility)
                        .on(facility.id.eq(houseFacilityRelation.facility.id))
                        .where(
                                house.id.in(houseIdList),
                                facility.category.eq(mapFilterRequest.getCategory()),
                                houseFacilityRelation.walking.loe(mapFilterRequest.getWalking()),
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
                        .transform(
                                groupBy(house.id)
                                        .list(
                                                Projections.constructor(
                                                        HouseResult.class,
                                                        house.id,
                                                        house.latitude,
                                                        house.longitude,
                                                        list(
                                                                Projections.constructor(
                                                                        FacilityResult.class,
                                                                        facility.id,
                                                                        facility.latitude,
                                                                        facility.longitude))))),
                createHouseContents(mapFilterRequest));
    }

    private List<HouseContentResult> createHouseContents(MapFilterRequest mapFilterRequest) {
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
                .stream()
                .map(HouseContentResult::init)
                .toList();
    }

    private List<Long> findHouseIdListInCategory(MapFilterRequest mapFilterRequest) {
        return jpaQueryFactory
                .select(houseFacilityRelation.house.id)
                .from(houseFacilityRelation)
                .join(facility)
                .on(houseFacilityRelation.facility.id.eq(facility.id))
                .where(
                        facility.category.eq(mapFilterRequest.getCategory()),
                        houseFacilityRelation.walking.loe(mapFilterRequest.getWalking()))
                .groupBy(houseFacilityRelation.house.id)
                .having(
                        houseFacilityRelation
                                .house
                                .id
                                .count()
                                .goe(mapFilterRequest.getFacilityCount()))
                .fetch();
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
