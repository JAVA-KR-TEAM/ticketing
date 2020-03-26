package kr.team.ticketing.domain;

import kr.team.ticketing.config.JpaConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
@Import(JpaConfig.class)
public class DomainTests {
	@Autowired
	protected EntityManager em;
}
