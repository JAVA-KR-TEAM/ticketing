package kr.team.ticketing.domain.comment;

import kr.team.ticketing.domain.DomainTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CommentTest extends DomainTests {
	@Autowired
	private CommentRepository commentRepository;

	@BeforeEach
	void setUp() {
		commentRepository.save(Comment.builder()
			.productId(1l)
			.reservationId(1l)
			.comment("매우 좋았던 공연입니다.")
			.score(4.5)
			.build());
		em.clear();
	}

	@Test
	void findComment() {
		Comment dbComment = commentRepository.findById(1l).get();
		assertThat(dbComment.getProductId()).isEqualTo(1l);
	}
}