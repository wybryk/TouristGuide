package pl.touristguide.springapp.mapper;

import pl.touristguide.model.Place;
import pl.touristguide.springapp.dto.PlaceDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class PlaceMapper {

    public static List<Place> toPlaceList(Queue<PlaceDTO> placeDTOQueue){
        return placeDTOQueue.stream()
                .map(tmpPlace -> toPlace(tmpPlace))
                .collect(Collectors.toList());
    }

    public static Place toPlace(PlaceDTO placeDTO) {
        Place place = new Place();
        place.setPlaceId(placeDTO.getPlaceId());
        place.setName(placeDTO.getName());
        place.setDescription(placeDTO.getDescription());
        place.setLatitude(placeDTO.getLat());
        place.setLongitude(placeDTO.getLng());
        place.setWebsiteLink(placeDTO.getWebsiteLink());
        place.setCategory(CategoryMapper.toCategory(placeDTO.getCategory()));
        return place;
    }

    public static Queue<PlaceDTO> toPlaceDTOQueue(List<Place> places) {
        Queue<PlaceDTO> placeDTOS = new LinkedList<>();
        for(Place tmpPlace : places)
            placeDTOS.offer(toPlaceDTO(tmpPlace));
        return placeDTOS;
    }

    public static PlaceDTO toPlaceDTO(Place place) {
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setPlaceId(place.getPlaceId());
        placeDTO.setName(place.getName());
        placeDTO.setDescription(place.getDescription());
        placeDTO.setImage(place.getImageName());
        placeDTO.setLat(place.getLatitude());
        placeDTO.setLng(place.getLongitude());
        placeDTO.setWebsiteLink(place.getWebsiteLink());
        placeDTO.setCategory(CategoryMapper.toCategoryDTO(place.getCategory()));
        placeDTO.setAccountId(place.getAccount().getAccountId());
        return placeDTO;
    }
}
