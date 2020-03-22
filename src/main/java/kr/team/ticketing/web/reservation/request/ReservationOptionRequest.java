package kr.team.ticketing.web.reservation.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationOptionRequest {
    private Long productId;
    private String name;
    private int price;

    @Builder
    public ReservationOptionRequest(Long productId, String name, int price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }
}
