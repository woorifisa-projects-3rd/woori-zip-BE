package fisa.woorizip.backend.house.repository;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;

import java.util.List;

public interface HouseCustomRepository {
    List<HouseCountResult> findHouseCountByGu(MapFilterRequest mapFilterRequest);

    List<HouseCountResult> findHouseCountByGu(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList);

    List<HouseCountResult> findHouseCountByDong(MapFilterRequest mapFilterRequest);

    List<HouseCountResult> findHouseCountByDong(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList);

    List<HouseResult> findHouseLatitudeAndLongitude(MapFilterRequest mapFilterRequest);

    List<HouseResult> findHouseLatitudeAndLongitude(
            MapFilterRequest mapFilterRequest, List<Long> houseIdList);

    List<HouseContentResult> findHouseContent(MapFilterRequest mapFilterRequest);

    List<Long> findHouseIdListByCategory(MapFilterRequest mapFilterRequest);

    List<Long> findHouseIdListByCategoryAndGuAndDong(MapFilterRequest mapFilterRequest);

    List<HouseContentResult> findHouseContentByMember(
            MapFilterRequest mapFilterRequest, Long memberId);
}
