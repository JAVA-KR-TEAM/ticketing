package kr.team.ticketing.domain.comment;

import kr.team.ticketing.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
	@Column
	private Long productId;
	@Column
	private Long reservationId;
	@Column
	private double score;
	@Lob
	private String comment;

	@Builder
	public Comment(Long productId, Long reservationId, double score, String comment) {
		this.productId = productId;
		this.reservationId = reservationId;
		this.score = score;
		this.comment = comment;
	}
}
