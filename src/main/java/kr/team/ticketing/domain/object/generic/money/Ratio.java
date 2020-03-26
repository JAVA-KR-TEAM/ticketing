package kr.team.ticketing.domain.object.generic.money;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ratio {
	private double rate;

	public static Ratio valueOf(double rate) {
		return new Ratio(rate);
	}

	Ratio(double rate) {
		this.rate = rate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ratio ratio = (Ratio)o;
		return Double.compare(ratio.rate, rate) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rate);
	}
}
