package pl.touristguide.springapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touristguide.springapp.dto.PlaceDTO;
import pl.touristguide.springapp.service.PlaceService;

import java.util.List;

@RestController
@Scope("request")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/touristguide/rest/places")
public class PlaceRestController {
    private final PlaceService placeService;

    @Autowired
    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity getPlaces(){
        try {
            List<PlaceDTO> placeDTOList = this.placeService.getPlaces();
            return new ResponseEntity(placeDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{placeId}", method = RequestMethod.GET)
    private ResponseEntity getPlace(@PathVariable("placeId") Long placeId){
        try {
            PlaceDTO placeDTO = this.placeService.getPlace(placeId);
            return new ResponseEntity(placeDTO, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity insertPlace(@RequestBody PlaceDTO placeDTO){
        try {
            this.placeService.insertPlace(placeDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{placeId}", method = RequestMethod.PATCH)
    private ResponseEntity updatePlace(@PathVariable("placeId") Long placeId, @RequestBody PlaceDTO placeDTO){
        try {
            this.placeService.updatePlace(placeId, placeDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{placeId}", method = RequestMethod.DELETE)
    private ResponseEntity deletePlace(@PathVariable("placeId") Long placeId){
        try {
            this.placeService.deletePlace(placeId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    private ResponseEntity searchPlaces(@RequestBody PlaceDTO placeDTO){
        try {
            List<PlaceDTO> placeDTOList = this.placeService.searchPlaces(placeDTO);
            return new ResponseEntity(placeDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/search/category/{categoryId}", method = RequestMethod.GET)
    private ResponseEntity searchPlacesByCategoryId(@PathVariable("categoryId") Long categoryId){
        try {
            List<PlaceDTO> placeDTOList = this.placeService.searchPlacesByCategoryId(categoryId);
            return new ResponseEntity(placeDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
