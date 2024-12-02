package fisa.woorizip.backend.house.dto.response;

import fisa.woorizip.backend.house.domain.House;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HouseResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String gu;
    private final String dong;
    private final long deposit;
    private final long monthlyRentFee;
    private final String imageUrl;

    public static HouseResponse from(House house) {
        return builder()
                .id(house.getId())
                .name(house.getName())
                .address(house.getAddress())
                .gu(house.getGu())
                .dong(house.getDong())
                .deposit(house.getDeposit())
                .monthlyRentFee(house.getMonthlyRentFee())
                .imageUrl(house.getRepresentativeImage())
                .build();
    }
}
