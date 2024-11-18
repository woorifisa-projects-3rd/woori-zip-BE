package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.house.dto.request.MapFilterRequest;
import fisa.woorizip.backend.house.dto.response.ShowMapResponse;

public interface HouseService {

    ShowMapResponse showMap(MapFilterRequest mapFilterRequest);
}
