package pl.touristguide.springapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touristguide.springapp.dto.AccountDTO;
import pl.touristguide.springapp.dto.UserDetailDTO;
import pl.touristguide.springapp.service.UserService;

@RestController
@Scope("request")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/touristguide/rest/user")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity registration(@RequestBody UserDetailDTO userDetailDTO){
        try {
            UserDetailDTO userDetailResult = userService.registration(userDetailDTO);
            return new ResponseEntity(userDetailResult, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity login(@RequestBody AccountDTO accountDTO){
        try {
            UserDetailDTO userDetailDTO = userService.login(accountDTO);
            return new ResponseEntity(userDetailDTO, HttpStatus.OK);
        }
        /*catch(InvalidPasswordException ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }*/
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{userDetailId}", method = RequestMethod.PATCH)
    private ResponseEntity updateUserAccount(@PathVariable("userDetailId") Long userDetailId, @RequestBody UserDetailDTO userDetailDTO){
        try {
            this.userService.updateUserAccount(userDetailId, userDetailDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{userDetailId}", method = RequestMethod.DELETE)
    private ResponseEntity deleteUserAccount(@PathVariable("userDetailId") Long userDetailId){
        try {
            this.userService.deleteUserAccount(userDetailId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
