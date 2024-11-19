package fisa.woorizip.backend.house.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import static fisa.woorizip.backend.bookmark.domain.QBookmark.bookmark;
import static fisa.woorizip.backend.facility.domain.QFacility.facility;
import static fisa.woorizip.backend.house.domain.HouseType.ALL;
import static fisa.woorizip.backend.house.domain.HousingExpenses.ANY;
import static fisa.woorizip.backend.house.domain.QHouse.house;
import static fisa.woorizip.backend.housefacilityrelation.domain.QHouseFacilityRelation.houseFacilityRelation;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import fisa.woorizip.backend.facility.dto.FacilityResult;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class HouseCustomRepositoryImpl implements HouseCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<HouseCountResult> findHouseCountByGu(MapFilterRequest mapFilterRequest) {
        return createConditionJPAQuery(mapFilterRequest)
                .groupBy(house.gu)
                .orderBy(house.gu.asc())
                .transform(
                        groupBy(house.gu)
                                .list(
                                        Projections.constructor(
                                                HouseCountResult.class,
                                                house.gu,
                                                house.count().intValue())));
    }

    @Override
    public List<HouseCountResult> findHouseCountByGu(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList) {
        return createConditionJPAQuery(mapFilterRequest)
                .where(house.id.in(houseIdList))
                .groupBy(house.gu)
                .orderBy(house.gu.asc())
                .transform(
                        groupBy(house.gu)
                                .list(
                                        Projections.constructor(
                                                HouseCountResult.class,
                                                house.gu,
                                                house.count().intValue())));
    }

    @Override
    public List<HouseCountResult> findHouseCountByDong(MapFilterRequest mapFilterRequest) {
        return createConditionJPAQuery(mapFilterRequest)
                .groupBy(house.dong)
                .orderBy(house.dong.asc())
                .transform(
                        groupBy(house.dong)
                                .list(
                                        Projections.constructor(
                                                HouseCountResult.class,
                                                house.dong,
                                                house.count().intValue())));
    }

    @Override
    public List<HouseCountResult> findHouseCountByDong(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList) {
        return createConditionJPAQuery(mapFilterRequest)
                .where(house.id.in(houseIdList))
                .groupBy(house.dong)
                .orderBy(house.dong.asc())
                .transform(
                        groupBy(house.dong)
                                .list(
                                        Projections.constructor(
                                                HouseCountResult.class,
                                                house.dong,
                                                house.count().intValue())));
    }

    @Override
    public List<HouseResult> findHouseLatitudeAndLongitude(MapFilterRequest mapFilterRequest) {
        return createConditionJPAQuery(mapFilterRequest).fetch().stream()
                .map(HouseResult::init)
                .toList();
    }

    @Override
    public List<HouseResult> findHouseLatitudeAndLongitude(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList) {
        return jpaQueryFactory
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
                                mapFilterRequest.getMinDeposit(), mapFilterRequest.getMaxDeposit()),
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
                                                                facility.longitude)))));
    }

    @Override
    public List<HouseContentResult> findHouseContent(MapFilterRequest mapFilterRequest) {
        return createConditionJPAQuery(mapFilterRequest).limit(15).fetch().stream()
                .map(HouseContentResult::init)
                .toList();
    }

    @Override
    public List<HouseContentResult> findHouseContent(MapFilterRequest mapFilterRequest, Long memberId) {
        return createConditionJPAQuery(mapFilterRequest)
                .leftJoin(bookmark)
                .on(bookmark.house.id.eq(house.id))
                .transform(
                        groupBy(house.id)
                                .list(
                                        Projections.constructor(
                                                HouseContentResult.class,
                                                house.id,
                                                house.housingExpenses.stringValue(),
                                                house.deposit,
                                                house.monthlyRentFee,
                                                house.houseType.stringValue(),
                                                house.gu,
                                                house.dong,
                                                house.maintenanceFee,
                                                house.comment,
                                                house.representativeImage,
                                                house.latitude,
                                                house.longitude,
                                                new CaseBuilder()
                                                        .when(bookmark.member.id.eq(memberId))
                                                        .then(true)
                                                        .otherwise(false))));
    }

    @Override
    public List<Long> findHouseIdListByCategory(MapFilterRequest mapFilterRequest) {
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

    @Override
    public List<Long> findHouseIdListByCategoryAndGuAndDong(MapFilterRequest mapFilterRequest) {
        return jpaQueryFactory
                .select(houseFacilityRelation.house.id)
                .from(houseFacilityRelation)
                .join(facility)
                .on(houseFacilityRelation.facility.id.eq(facility.id))
                .join(house)
                .on(house.id.eq(houseFacilityRelation.house.id))
                .where(
                        facility.category.eq(mapFilterRequest.getCategory()),
                        houseFacilityRelation.walking.loe(mapFilterRequest.getWalking()),
                        house.gu.eq(mapFilterRequest.getGu()),
                        house.dong.eq(mapFilterRequest.getDong()))
                .groupBy(houseFacilityRelation.house.id)
                .having(
                        houseFacilityRelation
                                .house
                                .id
                                .count()
                                .goe(mapFilterRequest.getFacilityCount()))
                .fetch();
    }

    private JPAQuery<House> createConditionJPAQuery(MapFilterRequest mapFilterRequest) {
        return jpaQueryFactory
                .selectFrom(house)
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
                                mapFilterRequest.getMaxMaintenanceFee()));
    }

    private BooleanExpression houseTypeEq(final HouseType houseType) {
        if (houseType == ALL) return null;
        return house.houseType.eq(houseType);
    }

    private BooleanExpression housingExpensesEq(final HousingExpenses housingExpenses) {
        if (housingExpenses == ANY) return null;
        return house.housingExpenses.eq(housingExpenses);
    }
}
