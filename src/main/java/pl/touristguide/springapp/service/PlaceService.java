package pl.touristguide.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.touristguide.common.ImageUtils;
import pl.touristguide.model.Account;
import pl.touristguide.model.Place;
import pl.touristguide.springapp.dao.PlaceDao;
import pl.touristguide.springapp.dto.PlaceDTO;
import pl.touristguide.springapp.mapper.PlaceMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    private final PlaceDao placeDao;
    private final CategoryService categoryService;
    private final UserService userService ;

    @Autowired
    public PlaceService(PlaceDao placeDao, CategoryService categoryService, UserService userService) {
        this.placeDao = placeDao;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public List<PlaceDTO> getPlaces() throws Exception {
        Iterable<Place> placeIterable = this.placeDao.findAll();
        List<PlaceDTO> placeDTOList = new ArrayList<>();
        for(Place tmpPlace : placeIterable){
            placeDTOList.add(PlaceMapper.toPlaceDTO(tmpPlace));
        }

        return placeDTOList;
    }

    public PlaceDTO getPlace(Long placeId) {
        Optional<Place> place = this.placeDao.findById(placeId);
        return PlaceMapper.toPlaceDTO(place.get());
    }

    @Transactional
    public void insertPlace(PlaceDTO placeDTO) throws Exception {
        Place place = PlaceMapper.toPlace(placeDTO);
        Account account = userService.findAccountById(placeDTO.getAccountId());
        place.setImageName(ImageUtils.createImage(placeDTO.getImage(), placeDTO.getName()));
        place.setAccount(account);
        place.setCategory(this.categoryService.getCategory(placeDTO.getCategory().getCategoryId()));
        System.out.println(place.toString());
        this.placeDao.save(place);
    }

    public void updatePlace(Long placeId, PlaceDTO placeDTO) throws Exception {
        placeDTO.setPlaceId(placeId);
        Place place = PlaceMapper.toPlace(placeDTO);
        this.placeDao.save(place);
    }

    public void deletePlace(Long placeId) throws Exception {
        PlaceDTO place = getPlace(placeId);
        ImageUtils.deleteImage(place.getImage());
        this.placeDao.deleteById(placeId);
    }

    public List<PlaceDTO> searchPlaces(PlaceDTO placePattern) throws Exception {
        List<PlaceDTO> placeDTOList = getPlaces();

        if(placePattern.getCategory() == null && (placePattern.getName() == null || placePattern.getName().equals("")))
            return placeDTOList;

        List<PlaceDTO> matchingPlaces = new ArrayList<>();

        for(PlaceDTO tmpPlace : placeDTOList){
            if(selectMatchingPlaces(placePattern, tmpPlace))
                matchingPlaces.add(tmpPlace);
        }

        return matchingPlaces;
    }

    private boolean selectMatchingPlaces(PlaceDTO placePattern, PlaceDTO placeDTO) throws Exception {
        boolean isFit = false;

        if(placePattern.getCategory() != null && placeDTO.getCategory().getName().equals(placePattern.getCategory().getName()))
            isFit =  true;
        if(placePattern.getName() != null && !placePattern.getName().equals("") && placeDTO.getName().matches(placePattern.getName()))
            isFit =  true;

        return isFit;
    }

    public List<PlaceDTO> searchPlacesByCategoryId(Long categoryId) throws Exception {
        return this.categoryService.getCategory(categoryId).getPlaces()
                .stream()
                .map(tmpPlace -> PlaceMapper.toPlaceDTO(tmpPlace))
                .collect(Collectors.toList());
    }

    public List<PlaceDTO> searchPlacesByAccountId(Long accountId) throws Exception {
        return this.userService.findAccountById(accountId).getPlaces()
                .stream()
                .map(tmpPlace -> PlaceMapper.toPlaceDTO(tmpPlace))
                .collect(Collectors.toList());
    }
}
