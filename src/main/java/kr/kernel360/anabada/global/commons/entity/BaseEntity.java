package kr.kernel360.anabada.global.commons.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners((AuditingEntityListener.class))
public class BaseEntity {
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createDate;

	private LocalDateTime modifiedDate;
}
