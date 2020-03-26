package kr.team.ticketing.domain.object.generic.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RatioTest {
	@DisplayName("할인가격 확인")
	@Test
	void testDiscountRate() {
		Ratio ratio = Ratio.valueOf(0.2);
		Money won = Money.wons(17000).times(ratio.getRate());

		assertThat(won.toString()).isEqualTo("3400원");
	}
}