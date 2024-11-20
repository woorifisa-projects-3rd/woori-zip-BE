package fisa.woorizip.backend.house.service;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.house.dto.HouseAddressType.DONG;
import static fisa.woorizip.backend.house.dto.HouseAddressType.GU;
import static fisa.woorizip.backend.house.dto.MapLevel.*;
import static fisa.woorizip.backend.member.domain.Role.MEMBER;
import static java.util.Objects.isNull;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.house.dto.result.HouseContentResult;
import fisa.woorizip.backend.house.dto.result.HouseCountResult;
import fisa.woorizip.backend.house.dto.result.HouseResult;
import fisa.woorizip.backend.house.repository.HouseRepository;
import fisa.woorizip.backend.houseimage.repository.HouseImageRepository;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
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
    public ShowMapResponse showMap(
            MapFilterRequest mapFilterRequest, MemberIdentity memberIdentity) {
        return mapFilterRequest.getLevel() == LOW
                ? ShowMapResponse.of(
                        createHouseResults(mapFilterRequest),
                        createHouseContentResults(mapFilterRequest, memberIdentity))
                : ShowMapResponse.of(
                        mapFilterRequest.getLevel() == HIGH ? GU : DONG,
                        createHouseCountResults(mapFilterRequest),
                        createHouseContentResults(mapFilterRequest, memberIdentity));
    }

    private List<HouseCountResult> createHouseCountResults(MapFilterRequest mapFilterRequest) {
        return mapFilterRequest.getLevel() == HIGH
                ? mapFilterRequest.getCategory() == NONE
                        ? houseRepository.findHouseCountByGu(mapFilterRequest)
                        : houseRepository.findHouseCountByGu(
                                mapFilterRequest,
                                houseRepository.findHouseIdListByCategory(mapFilterRequest))
                : mapFilterRequest.getCategory() == NONE
                        ? houseRepository.findHouseCountByDong(mapFilterRequest)
                        : houseRepository.findHouseCountByDong(
                                mapFilterRequest,
                                houseRepository.findHouseIdListByCategory(mapFilterRequest));
    }

    private List<HouseResult> createHouseResults(MapFilterRequest mapFilterRequest) {
        return isNull(mapFilterRequest.getGu())
                ? mapFilterRequest.getCategory() == NONE
                        ? houseRepository.findHouseLatitudeAndLongitude(mapFilterRequest)
                        : houseRepository.findHouseLatitudeAndLongitude(
                                mapFilterRequest,
                                houseRepository.findHouseIdListByCategory(mapFilterRequest))
                : houseRepository.findHouseLatitudeAndLongitude(
                        mapFilterRequest,
                        houseRepository.findHouseIdListByCategoryAndGuAndDong(mapFilterRequest));
    }

    private List<HouseContentResult> createHouseContentResults(
            MapFilterRequest mapFilterRequest, MemberIdentity memberIdentity) {
        return !isNull(memberIdentity) && memberIdentity.getRole() == MEMBER
                ? houseRepository.findHouseContent(mapFilterRequest, memberIdentity.getId())
                : houseRepository.findHouseContent(mapFilterRequest);
    }
}
