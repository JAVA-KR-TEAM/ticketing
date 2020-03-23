package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.detail.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

import static java.util.Arrays.asList;
import static kr.team.ticketing.common.Fixtures.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ReservationValidatorTest {
    private ReservationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new ReservationValidator(mock(ProductRepository.class));
    }

    @DisplayName("예매 항목이 비어 있는 경우")
    @Test
    void emptyReservationLineItems() {
        Reservation reservation = aReservation().lineItems(Collections.emptyList()).build();
        assertThrows(IllegalStateException.class,
                () -> validator.validate(reservation, new HashMap<>()));
    }

    @DisplayName("상품의 정보가 변경된 경우")
    @Test
    void changeProductOptions() {
        Product product = aProduct().description("캣츠 공연입니다.").build();
        Reservation reservation = aReservation().build();

        assertThrows(IllegalStateException.class,
                () -> validator.validate(reservation, new HashMap<Long, Product>() {{ put(1L, product); }})
        );
    }

    @DisplayName("상품의 옵션이 변경된 경우")
    @Test
    void changeProductOption() {
        Reservation reservation = aReservation().build();
        Product product = aProduct().build();
        product.addOptions(asList(
                anOptionAdult().productType(ProductType.PACKAGE).build(),
                anOptionTeenager().productType(ProductType.EARLYBIRD).build(),
                anOptionChildren().build()
        ));

        assertThrows(IllegalStateException.class,
                () -> validator.validate(reservation, new HashMap<Long, Product>() {{ put(1L, product); }})
        );
    }
}