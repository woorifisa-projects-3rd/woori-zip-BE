package fisa.woorizip.backend.house.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HouseCountResult {
    private String addressName;
    private int count;

    public static HouseCountResult init(String addressName, int count) {
        return new HouseCountResult(addressName, count);
    }
}
