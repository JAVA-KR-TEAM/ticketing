package kr.team.ticketing.web.admin.product;

import kr.team.ticketing.common.ControllerTests;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.object.generic.money.Ratio;
import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.category.Category;
import kr.team.ticketing.domain.product.category.CategoryRepository;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.domain.product.detail.ProductType;
import kr.team.ticketing.web.admin.product.request.OptionParam;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ControllerTests {
    private static final String PRODUCT_URI = getUri(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.save(new Category("공연"));
    }

    @DisplayName("상품 추가")
    @Order(1)
    @Test
    void saveProduct() throws Exception {
        // given
        Product product = Product.builder()
                .categoryId(1l)
                .description("뮬란 공연입니다.")
                .content("뮬란 공연은 중국 남북조시대 작자 미상의 화목란의 이야기를 바탕으로 개편된 공연입니다.")
                .event("")
                .build();

        // when & then
        mockMvc.perform(post(PRODUCT_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("product/save",
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NULL).description("상품 ID"),
                                fieldWithPath("createdDate").type(JsonFieldType.NULL).description("상품 생성일"),
                                fieldWithPath("modifiedDate").type(JsonFieldType.NULL).description("상품 수정일"),
                                fieldWithPath("options").type(JsonFieldType.ARRAY).description("상품 옵션"),
                                fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 ID"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("상품 간략 설명"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("상품 상세 설명"),
                                fieldWithPath("event").type(JsonFieldType.STRING).description("상품 이벤트")
                        )
                ));
    }

    @DisplayName("상품 수정")
    @Test
    void updateProduct() throws Exception {
        // given
        Product product = Product.builder()
                .categoryId(2l)
                .description("캣츠 뮤지컬입니다.")
                .content("캣츠 뮤지컬은 매우 흥미롭고 재미있습니다.")
                .event("2020년 3월 20일 부터 2020년 4월 20일까지 특별 이벤트!!")
                .build();

        // when & then
        mockMvc.perform(put(PRODUCT_URI + "/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/save"));
    }

    @DisplayName("상품 한건 조회")
    @Test
    void findProduct() throws Exception{
        // given
        Product product = this.saveProduct(40);

        // when & then
        mockMvc.perform(get(PRODUCT_URI + "/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description").hasJsonPath())
                .andDo(document("product/findProduct"));
    }

    @DisplayName("상품 조회")
    @Test
    void findProducts() throws Exception{
        // given
        IntStream.range(0, 30).forEach(this::saveProduct);

        // when & then
        mockMvc.perform(get(PRODUCT_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("product/findProducts"));
    }

    private Product saveProduct(int index) {
        Product product = Product.builder()
                .categoryId(1l)
                .description("Product" + index)
                .build();
        return this.productRepository.save(product);
    }

    @DisplayName("상품 여러 옵션 추가")
    @Test
    void saveOptions() throws Exception {
        // given
        List<OptionParam> params = asList(
                OptionParam.builder()
                        .productType("ADULT")
                        .discountRate(0.2)
                        .price(17000)
                        .build(),
                OptionParam.builder()
                        .productType("TEENAGER")
                        .discountRate(0.25)
                        .price(15000)
                        .build(),
                OptionParam.builder()
                        .productType("CHILDREN")
                        .discountRate(0.3)
                        .price(13000)
                        .build()
        );

        // when & then
        mockMvc.perform(post(PRODUCT_URI + "/1" + "/options")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(params)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("product/saveOptions",
                        requestFields(
                                fieldWithPath("[].productType").type(JsonFieldType.STRING).description("옵션 타입"),
                                fieldWithPath("[].discountRate").type(JsonFieldType.NUMBER).description("옵션 할인율"),
                                fieldWithPath("[].price").type(JsonFieldType.NUMBER).description("옵션 가격")
                        )
                ));
    }

    @DisplayName("상품 옵션 삭제")
    @Test
    void deleteOption() throws Exception{
        // given
        Product product = Product.builder()
                .categoryId(1l)
                .description("캣츠 뮤지컬")
                .content("캣츠 뮤지컬은 매우 재미있습니다.")
                .event("")
                .build();

        Option option = Option.builder()
                .productType(ProductType.ADULT)
                .discountRate(Ratio.valueOf(0.2))
                .price(Money.wons(17000))
                .build();
        product.addOption(option);
        productRepository.save(product);

        // when & then
        mockMvc.perform(delete(PRODUCT_URI + "/" + product.getId() + "/options/1"))
                .andExpect(status().isNoContent())
                .andDo(document("product/deleteOption"));
    }
}