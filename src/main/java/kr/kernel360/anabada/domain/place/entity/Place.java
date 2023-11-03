package kr.kernel360.anabada.domain.place.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "place")
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "state", columnDefinition = "varchar(40)")
	private String state;

	@Column(name = "city", columnDefinition = "varchar(40)") // todo : notnull 임시 제거 -> 추후 붙여야함
	private String city;

	@Column(name = "address1", columnDefinition = "varchar(40)") // todo : notnull 임시 제거 -> 추후 붙여야함
	private String address1;

	@Builder
	public Place(String state, String city, String address1) {
		this.state = state;
		this.city = city;
		this.address1 = address1;
	}
}
