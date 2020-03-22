package kr.team.ticketing.domain.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.team.ticketing.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationLineItem extends BaseEntity {
    @Column
    private Long productId;
    @JsonIgnore
    @ManyToOne
    private Reservation reservation;
    @Column
    private int count;
    @OneToMany(mappedBy = "lineItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationOption> reserveOptions = new ArrayList<>();

    public ReservationLineItem(Long productId) {
        this.productId = productId;
        this.count = reservationCount();
    }

    private int reservationCount() {
        return reserveOptions.size();
    }

    public void addReservationOption(ReservationOption reservationOption) {
        reservationOption.setLineItem(this);
        this.reserveOptions.add(reservationOption);
    }

    public void addReservationOptions(List<ReservationOption> reserveOptions) {
        reserveOptions.forEach(o -> addReservationOption(o));
    }

    public void setReservation(Reservation reservation) {
        if(this.reservation != null) {
            this.reservation.getLineItems().remove(this);
        }
        this.reservation = reservation;
    }
}
