package kr.team.ticketing.web.admin.product;

import kr.team.ticketing.common.ControllerTests;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.product.display.Display;
import kr.team.ticketing.domain.product.display.DisplayRepository;
import kr.team.ticketing.web.admin.product.request.DisplayRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.stream.IntStream;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DisplayControllerTest extends ControllerTests {
    private static final String DISPLAY_URI = getUri(DisplayController.class);

    @Autowired
    private DisplayRepository displayRepository;

    @DisplayName("전시 정보 추가")
    @Order(1)
    @Test
    void saveDisplay() throws Exception {
        // given
        DisplayRequest param = DisplayRequest.builder()
                .productId(1l)
                .openingHours("2020년 3월 15일부터 2020년 4월 15일까지 진행되는 행사입니다.")
                .place("세종정부청사")
                .placeLot("경기도 세종시 231-31")
                .placeStreet("경기도 세종시 세종로 132번길 34")
                .homePage("www.display.com")
                .tel("010-2331-1233")
                .email("chulsu@naver.com")
                .build();

        // when & then
        mockMvc.perform(post(DISPLAY_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(param)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("display/saveDisplay",
                        requestFields(
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품 번호"),
                                fieldWithPath("openingHours").type(JsonFieldType.STRING).description("오프닝 정보"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("전시 장소"),
                                fieldWithPath("placeLot").type(JsonFieldType.STRING).description("전시 장소 지번 주소"),
                                fieldWithPath("placeStreet").type(JsonFieldType.STRING).description("전시 장소 도로명 주소"),
                                fieldWithPath("homePage").type(JsonFieldType.STRING).description("전시자 홈페이지"),
                                fieldWithPath("tel").type(JsonFieldType.STRING).description("전시자 전화번호"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("전시자 이메일")
                                )
                ));
    }

    @DisplayName("전시 정보 수정")
    @Test
    void updateDisplay() throws Exception {
        // given
        DisplayRequest param = DisplayRequest.builder()
                .productId(2l)
                .openingHours("2020년 3월 15일부터 2020년 4월 15일까지 진행되는 행사입니다.")
                .place("세종정부청사")
                .placeLot("경기도 세종시 231-31")
                .placeStreet("경기도 세종시 세종로 132번길 34")
                .homePage("www.displayTest.com")
                .tel("010-5463-5123")
                .email("chulsu12@naver.com")
                .build();

        // when & then
        mockMvc.perform(put(DISPLAY_URI + "/1")
                        .content(objectMapper.writeValueAsString(param))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("display/updateDisplay"));
    }

    @DisplayName("전시 정보 1건 조회")
    @Test
    void findDisplay() throws Exception {
        // given
        Display display = this.saveDisplay(40);

        // when & then
        mockMvc.perform(get(DISPLAY_URI + "/" + display.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("display/findDisplay"));
    }

    @DisplayName("전시 목록 조회")
    @Test
    void findDisplays() throws Exception {
        // given
        IntStream.range(0, 30).forEach(this::saveDisplay);

        // when & then
        mockMvc.perform(get(DISPLAY_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("display/findDisplays"));
    }

    @DisplayName("전시 정보 삭제")
    @Test
    void deleteDisplay() throws Exception {
        // given
        Display display = this.saveDisplay(50);

        // when & then
        mockMvc.perform(delete(DISPLAY_URI + "/" + display.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("display/deleteDisplay"));
    }

    private Display saveDisplay(int index) {
        Display display = Display.builder()
                .openingHours("2020년 3월 15일 공연입니다." + index)
                .email(new Email("test" + index + "@naver.com"))
                .build();
        return displayRepository.save(display);
    }
}