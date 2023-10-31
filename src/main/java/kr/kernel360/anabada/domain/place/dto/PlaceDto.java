package kr.kernel360.anabada.domain.place.dto;


import kr.kernel360.anabada.domain.place.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlaceDto {
	private String state;

	private String city;

	private String address1;

	public static Place toEntity(PlaceDto placeDto) {
		return Place.builder()
			.state(placeDto.state)
			.city(placeDto.city)
			.address1(placeDto.address1)
			.build();
	}
}
