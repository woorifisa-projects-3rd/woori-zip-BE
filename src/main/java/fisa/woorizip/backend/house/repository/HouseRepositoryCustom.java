package fisa.woorizip.backend.house.repository;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;

import java.util.List;

public interface HouseRepositoryCustom {
    ShowMapResponse findHouseHighLevel(MapFilterRequest mapFilterRequest);
    ShowMapResponse findHouseMidLevel(MapFilterRequest mapFilterRequest);
    ShowMapResponse findHouseLowLevel(MapFilterRequest mapFilterRequest);
    ShowMapResponse findHouseHighLevelInCategory(MapFilterRequest mapFilterRequest);
    ShowMapResponse findHouseMidLevelInCategory(MapFilterRequest mapFilterRequest);
    ShowMapResponse findHouseLowLevelInCategory(MapFilterRequest mapFilterRequest);
}
