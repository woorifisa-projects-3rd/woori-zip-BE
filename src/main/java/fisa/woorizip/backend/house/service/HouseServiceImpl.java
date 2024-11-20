package fisa.woorizip.backend.house.service;

import static fisa.woorizip.backend.facility.domain.Category.NONE;
import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;
import static fisa.woorizip.backend.house.HouseErrorCode.MAP_LEVEL_NOT_FOUND;
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
        if (mapFilterRequest.getLevel() == HIGH) {
            if (mapFilterRequest.getCategory() == NONE) {
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            GU,
                            houseRepository.findHouseCountByGu(mapFilterRequest),
                            houseRepository.findHouseContent(mapFilterRequest));
                } else {
                    return ShowMapResponse.of(
                            GU,
                            houseRepository.findHouseCountByGu(mapFilterRequest),
                            houseRepository.findHouseContent(
                                    mapFilterRequest, memberIdentity.getId()));
                }
            } else {
                List<Long> houseIdList =
                        houseRepository.findHouseIdListByCategory(mapFilterRequest);
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            GU,
                            houseRepository.findHouseCountByGu(mapFilterRequest, houseIdList),
                            houseRepository.findHouseContent(mapFilterRequest, houseIdList));
                } else {
                    return ShowMapResponse.of(
                            GU,
                            houseRepository.findHouseCountByGu(mapFilterRequest, houseIdList),
                            houseRepository.findHouseContent(
                                    mapFilterRequest, houseIdList, memberIdentity.getId()));
                }
            }
        } else if (mapFilterRequest.getLevel() == MID) {
            if (mapFilterRequest.getCategory() == NONE) {
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            DONG,
                            houseRepository.findHouseCountByDong(mapFilterRequest),
                            houseRepository.findHouseContent(mapFilterRequest));
                } else {
                    return ShowMapResponse.of(
                            DONG,
                            houseRepository.findHouseCountByDong(mapFilterRequest),
                            houseRepository.findHouseContent(
                                    mapFilterRequest, memberIdentity.getId()));
                }
            } else {
                List<Long> houseIdList =
                        houseRepository.findHouseIdListByCategory(mapFilterRequest);
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            DONG,
                            houseRepository.findHouseCountByDong(mapFilterRequest, houseIdList),
                            houseRepository.findHouseContent(mapFilterRequest, houseIdList));
                } else {
                    return ShowMapResponse.of(
                            DONG,
                            houseRepository.findHouseCountByDong(mapFilterRequest, houseIdList),
                            houseRepository.findHouseContent(
                                    mapFilterRequest, houseIdList, memberIdentity.getId()));
                }
            }
        } else if (mapFilterRequest.getLevel() == LOW) {
            if (mapFilterRequest.getCategory() == NONE) {
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            houseRepository.findHouseLatitudeAndLongitude(mapFilterRequest),
                            houseRepository.findHouseContent(mapFilterRequest));
                } else {
                    return ShowMapResponse.of(
                            houseRepository.findHouseLatitudeAndLongitude(mapFilterRequest),
                            houseRepository.findHouseContent(
                                    mapFilterRequest, memberIdentity.getId()));
                }
            } else {
                List<Long> houseIdList =
                        houseRepository.findHouseIdListByCategory(mapFilterRequest);
                if (isNull(memberIdentity) || memberIdentity.getRole() != MEMBER) {
                    return ShowMapResponse.of(
                            houseRepository.findHouseLatitudeAndLongitude(
                                    mapFilterRequest, houseIdList),
                            houseRepository.findHouseContent(mapFilterRequest, houseIdList));
                } else {
                    if (isNull(mapFilterRequest.getGu())) {
                        return ShowMapResponse.of(
                                houseRepository.findHouseLatitudeAndLongitude(
                                        mapFilterRequest, houseIdList),
                                houseRepository.findHouseContent(
                                        mapFilterRequest, houseIdList, memberIdentity.getId()));
                    } else {
                        List<Long> houseIdListByGuAndDong =
                                houseRepository.findHouseIdListByCategoryAndGuAndDong(
                                        mapFilterRequest);
                        return ShowMapResponse.of(
                                houseRepository.findHouseLatitudeAndLongitude(
                                        mapFilterRequest, houseIdListByGuAndDong),
                                houseRepository.findHouseContent(
                                        mapFilterRequest,
                                        houseIdListByGuAndDong,
                                        memberIdentity.getId()));
                    }
                }
            }
        } else {
            throw new WooriZipException(MAP_LEVEL_NOT_FOUND);
        }
    }
}
