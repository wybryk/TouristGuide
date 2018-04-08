package pl.touristguide.springapp.mapper;

import pl.touristguide.model.Place;
import pl.touristguide.springapp.dto.PlaceDTO;

public class PlaceMapper {
    public static Place toPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setPlaceId(placeDTO.getPlaceId());
        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLatitude(placeDTO.getLat());
        place.setLongitude(placeDTO.getLng());
        place.setCategory(CategoryMapper.toCategory(placeDTO.getCategory()));
        return place;
    }

    public static PlaceDTO toPlaceDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setPlaceId(place.getPlaceId());
        placeDTO.setName(place.getName());
        placeDTO.setDescription(place.getDescription());
        placeDTO.setImage(place.getImageName());
        placeDTO.setLat(place.getLatitude());
        placeDTO.setLng(place.getLongitude());
        placeDTO.setCategory(CategoryMapper.toCategoryDTO(place.getCategory()));
        return placeDTO;
    }
}
