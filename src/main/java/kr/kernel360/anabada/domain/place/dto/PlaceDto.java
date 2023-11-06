package kr.kernel360.anabada.domain.place.dto;


import kr.kernel360.anabada.domain.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public static PlaceDto of(PlaceResponse placeResponse) {
		return PlaceDto.builder()
			.state(placeResponse.getRegion1depthName())
			.city(placeResponse.getRegion2depthName())
			.address1(placeResponse.getRegion3depthName())
			.build();
	}
}
