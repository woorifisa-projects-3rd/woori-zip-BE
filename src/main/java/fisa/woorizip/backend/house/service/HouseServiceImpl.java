package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.repository.HouseImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;

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
        return houseRepository.findById(houseId)
                .orElseThrow(() -> new WooriZipException(HOUSE_NOT_FOUND));
    }
}