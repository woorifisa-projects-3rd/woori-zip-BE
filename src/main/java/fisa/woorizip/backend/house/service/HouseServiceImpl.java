package fisa.woorizip.backend.house.service;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.dto.MapLevel.HIGH;
import static fisa.woorizip.backend.house.dto.MapLevel.MID;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    @Transactional(readOnly = true)
    public ShowMapResponse showMap(MapFilterRequest mapFilterRequest) {
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
}
