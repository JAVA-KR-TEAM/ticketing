package kr.team.ticketing.domain.reservation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.generic.money.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationOption extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    private ReservationLineItem lineItem;
    @Column
    private String name;
    @Column
    private Money price;
}
