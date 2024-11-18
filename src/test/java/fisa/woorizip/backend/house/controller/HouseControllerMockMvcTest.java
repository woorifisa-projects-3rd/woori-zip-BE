package fisa.woorizip.backend.house.controller;

import static fisa.woorizip.backend.house.HouseErrorCode.HOUSE_NOT_FOUND;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fisa.woorizip.backend.common.exception.WooriZipException;
import fisa.woorizip.backend.house.domain.House;
import fisa.woorizip.backend.house.dto.response.HouseDetailResponse;
import fisa.woorizip.backend.house.service.HouseService;
import fisa.woorizip.backend.member.service.auth.JwtTokenProvider;
import fisa.woorizip.backend.support.fixture.HouseFixture;
import fisa.woorizip.backend.support.fixture.MemberFixture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(HouseController.class)
class HouseControllerMockMvcTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private HouseService houseService;

    @MockBean private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("매물 상세 정보를 조회할 수 있다")
    void getHouseDetail() throws Exception {
        House house = HouseFixture.builder().member(MemberFixture.builder().build()).build();
        List<String> imageUrls = List.of("test1.jpg", "test2.jpg");
        HouseDetailResponse response = HouseDetailResponse.of(house, imageUrls);

        given(houseService.getHouseDetail(anyLong())).willReturn(response);

        mockMvc.perform(get("/api/v1/houses/{houseId}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("피사아파트"))
                .andExpect(jsonPath("$.address").value("서울 마포구 월드컵북로 434 피사아파트"))
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls[0]").value("test1.jpg"));
    }

    @Test
    @DisplayName("존재하지 않는 매물 ID로 조회하면 404 응답을 반환한다")
    void getHouseDetail_NotFound() throws Exception {
        given(houseService.getHouseDetail(anyLong()))
                .willThrow(new WooriZipException(HOUSE_NOT_FOUND));

        mockMvc.perform(
                        get("/api/v1/houses/{houseId}", 999L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(HOUSE_NOT_FOUND.getMessage()));
    }
}
