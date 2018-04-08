package pl.touristguide.springapp.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touristguide.springapp.dto.RouteDTO;
import pl.touristguide.springapp.service.RouteService;

import java.util.List;

@RestController
@Scope("request")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/touristguide/rest/routes")
public class RouteRestController {
    private final RouteService routeService;

    @Autowired
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @RequestMapping(value = "/user/{accountId}", method = RequestMethod.GET)
    private ResponseEntity getRoutes(@PathVariable("accountId") Long accountId){
        try {
            List<RouteDTO> routeDTOList = this.routeService.getRoutes(accountId);
            return new ResponseEntity(routeDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{routeId}", method = RequestMethod.GET)
    private ResponseEntity getRoute(@PathVariable("routeId") Long routeId){
        try {
            RouteDTO routeDTO = this.routeService.getRoute(routeId);
            return new ResponseEntity(routeDTO, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity insertRoute(@RequestBody RouteDTO routeDTO){
        try {
            this.routeService.insertRoute(routeDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{routeId}", method = RequestMethod.PATCH)
    private ResponseEntity updatePlace(@PathVariable("routeId") Long routeId, @RequestBody RouteDTO routeDTO){
        try {
            this.routeService.updateRoute(routeId, routeDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{routeId}", method = RequestMethod.DELETE)
    private ResponseEntity deleteRoute(@PathVariable("routeId") Long routeId){
        try {
            this.routeService.deleteRoute(routeId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
