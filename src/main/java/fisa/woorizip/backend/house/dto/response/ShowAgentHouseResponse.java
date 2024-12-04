package fisa.woorizip.backend.house.dto.response;

import fisa.woorizip.backend.house.domain.House;

import lombok.Builder;
import lombok.Getter;

import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
public class ShowAgentHouseResponse {
    private final boolean hasNext;
    private final List<HouseResponse> houses;

    public static ShowAgentHouseResponse from(Slice<House> houses) {
        return builder()
                .hasNext(houses.hasNext())
                .houses(houses.getContent().stream().map(HouseResponse::from).toList())
                .build();
    }
}
