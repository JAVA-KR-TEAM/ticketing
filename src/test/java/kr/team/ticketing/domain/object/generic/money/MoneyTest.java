package kr.team.ticketing.domain.object.generic.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Money 클래스")
class MoneyTest {

    @Nested
    @DisplayName("Equals 메서드")
    class Describe_equality{
        @DisplayName("값의 동치성 비교")
        @Test
        void equality() {
            Money excepted = Money.wons(15000);
            Money actual = Money.wons(15000);

            assertThat(excepted).isEqualTo(actual);
        }
    }

    @Nested
    @DisplayName("plus 메서드")
    class Describe_plus{
        @DisplayName("값을 더한 후 새로운 Money 반환")
        @Test
        void plus() {
            Money excepted = Money.wons(15000);
            Money actual = excepted.plus(Money.wons(2000));

            assertThat(actual.toString()).isEqualTo("17000원");
        }
    }

    @Nested
    @DisplayName("minus 메서드")
    class Describe_minus{
        @DisplayName("값을 뺀 후 새로운 Money 반환")
        @Test
        void minus() {
            Money excepted = Money.wons(15000);
            Money actual = excepted.minus(Money.wons(2000));

            assertThat(actual.toString()).isEqualTo("13000원");
        }
    }

    @Nested
    @DisplayName("times 메서드")
    class Describe_times{
        @DisplayName("값을 곱한 후 새로운 Money 반환")
        @Test
        void plus() {
            Money excepted = Money.wons(15000);
            Money actual = excepted.times(0.2);

            assertThat(actual.toString()).isEqualTo("3000원");
        }
    }

    @Nested
    @DisplayName("divide 메서드")
    class Describe_divide{
        @DisplayName("값을 나눈 후 새로운 Money 반환")
        @Test
        void plus() {
            Money excepted = Money.wons(15000);
            Money actual = excepted.divide(0.6);

            assertThat(actual.toString()).isEqualTo("25000원");
        }
    }
}