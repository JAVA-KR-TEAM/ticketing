package kr.team.ticketing.domain.product.display;

import kr.team.ticketing.domain.DomainTests;
import kr.team.ticketing.domain.object.Email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DisplayTest extends DomainTests {
	@Autowired
	private DisplayRepository displayRepository;

	@BeforeEach
	void setup() {
		Display display = Display.builder()
			.productId(1l)
			.email(new Email("chulsu@naver.com"))
			.homePage("www.chulsu.com")
			.address(Address.builder()
				.place("예술의 전당 한가람 미술관")
				.placeLot("서울특별시 서초구 서초동 700")
				.placeStreet("서울특별시 서초구 남부순환로 2406")
				.build())
			.openingHours("2020년 3월 1일부터 2020년 3월 31일까지 열리는 대박 공연")
			.tel("010-2331-4123")
			.build();

		displayRepository.save(display);
		em.clear();
	}

	@DisplayName("전시 내용 확인")
	@Test
	void findAll() {
		List<Display> result = displayRepository.findAll();
		Display dbDisplay = result.get(0);

		assertThat(dbDisplay.getAddress().getPlace()).isEqualTo("예술의 전당 한가람 미술관");
		assertThat(dbDisplay.getProductId()).isEqualTo(1l);
	}
}