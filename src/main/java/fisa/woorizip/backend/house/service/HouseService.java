package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;

public interface HouseService {
    HouseDetailResponse getHouseDetail(Long houseId);
    ShowMapResponse showMap(MapFilterRequest mapFilterRequest);
}
