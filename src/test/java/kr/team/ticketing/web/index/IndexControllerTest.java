package kr.team.ticketing.web.index;

import kr.team.ticketing.common.ControllerTests;

import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.object.generic.money.Ratio;
import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.domain.product.detail.ProductType;
import kr.team.ticketing.domain.product.display.Address;
import kr.team.ticketing.domain.product.display.Display;
import kr.team.ticketing.domain.product.display.DisplayRepository;
import kr.team.ticketing.web.index.request.SearchCondition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class IndexControllerTest extends ControllerTests {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DisplayRepository displayRepository;

    @BeforeEach
    void setup() {
        Product product = Product.builder()
                .categoryId(1l)
                .description("뮤즈 공연입니다.")
                .content("뮤즈 공연은 ~~~입니다.")
                .event("")
                .build();

        List<Option> options =
                asList(
                        Option.builder().productType(ProductType.ADULT)
                                .discountRate(Ratio.valueOf(0.1))
                                .price(Money.wons(23000)).build(),
                        Option.builder().productType(ProductType.TEENAGER)
                                .discountRate(Ratio.valueOf(0.2))
                                .price(Money.wons(19000)).build(),
                        Option.builder().productType(ProductType.CHILDREN)
                                .discountRate(Ratio.valueOf(0.25))
                                .price(Money.wons(17000)).build()
                );

        product.addOptions(options);
        productRepository.save(product);

        Display display = Display.builder()
                .productId(1l)
                .email(new Email("jpa@naver.com"))
                .homePage("www.chulsu.com")
                .address(Address.builder()
                        .place("예술의 전당 한가람 미술관")
                        .placeLot("서울특별시 서초구 서초동 700")
                        .placeStreet("서울특별시 서초구 남부순환로 2406")
                        .locationCode("SE")
                        .build())
                .openingHours("2020년 3월 1일부터 2020년 3월 31일까지 열리는 대박 공연")
                .startDisplayDate(LocalDateTime.of(2020, 3, 1, 10, 00, 00))
                .endDisplayDate(LocalDateTime.of(2020, 3, 31, 17, 00, 00))
                .tel("010-2331-4123")
                .build();

        displayRepository.save(display);
    }

    @AfterEach
    void cleanUp() {
        displayRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("메인페이지 조회 테스트")
    public void searchByDynamicCondition() throws Exception {

        //given
        SearchCondition searchCondition = SearchCondition.builder()
                .categoryId(1l)
                .locationCode("SE")
                .month(3)
                .build();

        //when & then
        this.mockMvc.perform(get("/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchCondition)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("index/mainview"));
    }
}