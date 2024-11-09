package fisa.woorizip.backend.house.service;

import fisa.woorizip.backend.facility.domain.Category;
import fisa.woorizip.backend.house.domain.HouseType;
import fisa.woorizip.backend.house.domain.HousingExpenses;
import fisa.woorizip.backend.house.dto.MapFilterRequest;
import fisa.woorizip.backend.house.dto.ShowMapResponse;
import fisa.woorizip.backend.house.repository.HouseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ShowMapResponse> showMap(MapFilterRequest mapFilterRequest) {

        return null;
    }
}
