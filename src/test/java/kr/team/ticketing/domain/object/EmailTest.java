package kr.team.ticketing.domain.object;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {
	@DisplayName("이메일 확인")
	@Test
	void findEmail() {
		Email email = new Email("chulsu12@naver.com");

		assertThat(email.getId()).isEqualTo("chulsu12");
		assertThat(email.getHost()).isEqualTo("@naver.com");
		assertThat(email.getValue()).isEqualTo("chulsu12@naver.com");
	}
}