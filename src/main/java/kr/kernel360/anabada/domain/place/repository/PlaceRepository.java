package kr.kernel360.anabada.domain.place.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.place.entity.Place;

public interface PlaceRepository extends JpaRepository<Place,Long> {
	Optional<Place> findByStateAndCityAndAddress1(String state, String city, String address1);

	Boolean existsByStateAndCityAndAddress1(String state, String city, String address1);
}
