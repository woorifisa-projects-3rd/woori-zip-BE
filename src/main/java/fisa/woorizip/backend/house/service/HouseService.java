package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.dto.response.ShowAgentHouseResponse;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;
import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import org.springframework.data.domain.Pageable;

public interface HouseService {
    HouseDetailResponse getHouseDetail(Long houseId);

    ShowMapResponse showMap(MapFilterRequest mapFilterRequest, MemberIdentity memberIdentity);

    ShowAgentHouseResponse getAgentHouse(MemberIdentity memberIdentity, Pageable pageable);
}
