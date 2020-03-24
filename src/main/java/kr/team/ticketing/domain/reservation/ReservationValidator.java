package kr.team.ticketing.domain.reservation;

import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.detail.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class ReservationValidator {
    private final ProductRepository productRepository;

    public void validate(Reservation reservation) {
        validate(reservation, getProduct(reservation));
    }

    void validate(Reservation reservation, Map<Long, Product> products){
        if(reservation.getLineItems().isEmpty()) {
            throw new IllegalStateException("예매 항목이 비어 있습니다.");
        }

        for(ReservationLineItem lineItem : reservation.getLineItems()) {
            validateReservationLineItem(lineItem, products.get(lineItem.getProductId()));
        }
    }

    private void validateReservationLineItem(ReservationLineItem lineItem, Product product) {
        if(!product.getDescription().equals(lineItem.getDescription())) {
            throw new IllegalStateException("상품의 정보가 변경되었습니다.");
        }

        for(ReservationOption option : lineItem.getReserveOptions()) {
            validateReservationOption(option, product);
        }
    }

    private void validateReservationOption(ReservationOption reservationOption, Product product) {
        for(Option option : product.getOptions()) {
            if(option.isSatisfiedBy(reservationOption.convertToOption())) {
                return;
            }
        }

        throw new IllegalStateException("상품의 옵션이 변경되었습니다.");
    }

    private Map<Long, Product> getProduct(Reservation reservation) {
        return productRepository.findAllById(reservation.getProductIds())
                .stream().collect(toMap(Product::getId, identity()));
    }
}
