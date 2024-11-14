package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;

public interface HouseService {
    HouseDetailResponse getHouseDetail(Long houseId);
}
