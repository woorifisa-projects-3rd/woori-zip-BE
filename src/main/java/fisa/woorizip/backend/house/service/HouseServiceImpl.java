package fisa.woorizip.backend.house.service;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.house.dto.MapLevel.HIGH;
import static fisa.woorizip.backend.house.dto.MapLevel.MID;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.repository.HouseImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseImageRepository houseImageRepository;

    @Override
    @Transactional(readOnly = true)
    public HouseDetailResponse getHouseDetail(Long houseId) {
        House house = findHouseById(houseId);
        List<String> imageUrls = houseImageRepository.findImageUrlsByHouseId(houseId);
        return HouseDetailResponse.of(house, imageUrls);
    }

    private House findHouseById(Long houseId) {
        return houseRepository
                .findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public ShowMapResponse showMap(MapFilterRequest mapFilterRequest) {
        if (mapFilterRequest.getGu() != null && mapFilterRequest.getDong() != null)
            return showMapByGuAndDong(mapFilterRequest);
        return mapFilterRequest.getLevel().equals(HIGH)
                ? showMapHighLevel(mapFilterRequest)
                : mapFilterRequest.getLevel().equals(MID)
                        ? showMapMidLevel(mapFilterRequest)
                        : showMapLowLevel(mapFilterRequest);
    }

    private ShowMapResponse showMapHighLevel(MapFilterRequest mapFilterRequest) {
        return mapFilterRequest.getCategory().equals(NONE)
                ? houseRepository.findHouseHighLevel(mapFilterRequest)
                : houseRepository.findHouseHighLevelInCategory(mapFilterRequest);
    }

    private ShowMapResponse showMapMidLevel(MapFilterRequest mapFilterRequest) {
        return mapFilterRequest.getCategory().equals(NONE)
                ? houseRepository.findHouseMidLevel(mapFilterRequest)
                : houseRepository.findHouseMidLevelInCategory(mapFilterRequest);
    }

    private ShowMapResponse showMapLowLevel(MapFilterRequest mapFilterRequest) {
        return mapFilterRequest.getCategory().equals(NONE)
                ? houseRepository.findHouseLowLevel(mapFilterRequest)
                : houseRepository.findHouseLowLevelInCategory(mapFilterRequest);
    }

    private ShowMapResponse showMapByGuAndDong(MapFilterRequest mapFilterRequest) {
        return houseRepository.findHouseByGuAndDongInCategory(mapFilterRequest);
    }
}
