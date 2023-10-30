package kr.kernel360.anabada.domain.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE category SET activated = 0 WHERE id = ?")
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "name", columnDefinition = "varchar(50)")
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "deleted_status", columnDefinition = "varchar(20)")
	private DeletedStatus deletedStatus;

	@Builder
	public Category(String name, DeletedStatus deletedStatus) {
		this.name = name;
		this.deletedStatus = deletedStatus;
	}

	public void update(String name) {
		this.name = name;
	}
}
