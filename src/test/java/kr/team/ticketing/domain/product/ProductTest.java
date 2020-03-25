package kr.team.ticketing.domain.product;

import kr.team.ticketing.domain.DomainTests;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.object.generic.money.Ratio;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.domain.product.detail.ProductType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest extends DomainTests {
	@Autowired
	private ProductRepository productRepository;

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
		em.clear();
	}

	@DisplayName("상품 삭제")
	@Test
	void removeProduct() {
		List<Product> result = productRepository.findAll();
		Product dbProduct = result.get(0);

		dbProduct.getOptions().clear();

		assertThat(dbProduct.getOptions().size()).isEqualTo(0);
	}
}