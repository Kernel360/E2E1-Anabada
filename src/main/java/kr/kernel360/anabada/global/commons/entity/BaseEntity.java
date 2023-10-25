package kr.kernel360.anabada.global.commons.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate
	@Column(nullable = false, name = "create_date", columnDefinition = "datetime", updatable = false)
	private LocalDateTime createDate;

	@LastModifiedDate
	@Column(name = "modified_date", columnDefinition = "datetime")
	private LocalDateTime modifiedDate;
}
