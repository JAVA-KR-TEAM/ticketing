package kr.team.ticketing.domain.object.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeUtilsTest {
	@DisplayName("날짜 생성")
	@Test
	void createDateTime() {
		LocalDateTime dateTime = DateTimeUtils.createDateTime("2020-03-17 12:00");

		assertThat(dateTime.getYear()).isEqualTo(2020);
		assertThat(dateTime.getMonth().getValue()).isEqualTo(3);
		assertThat(dateTime.getDayOfMonth()).isEqualTo(17);
		assertThat(dateTime.getHour()).isEqualTo(12);
	}
}