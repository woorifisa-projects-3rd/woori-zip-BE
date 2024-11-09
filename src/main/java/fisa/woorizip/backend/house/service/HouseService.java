package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapFilterRequest;
import fisa.woorizip.backend.house.dto.MapLevel;
import fisa.woorizip.backend.house.dto.ShowMapResponse;

import java.math.BigDecimal;
import java.util.List;

public interface HouseService {

    List<ShowMapResponse> showMap(MapFilterRequest mapFilterRequest);
}
