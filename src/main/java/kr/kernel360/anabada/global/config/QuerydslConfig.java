package kr.kernel360.anabada.global.config;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Component
public class QuerydslConfig {

	@Bean
	private JPAQueryFactory jpaQueryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}
}
