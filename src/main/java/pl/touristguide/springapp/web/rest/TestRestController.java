package pl.touristguide.springapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.touristguide.common.FillDataBase;
import pl.touristguide.model.Category;
import pl.touristguide.model.Place;
import pl.touristguide.model.UserDetail;
import pl.touristguide.springapp.dao.AccountDao;
import pl.touristguide.springapp.dao.CategoryDao;
import pl.touristguide.springapp.dao.PlaceDao;
import pl.touristguide.springapp.dao.UserDetailDao;

import java.util.ArrayList;
import java.util.List;

@RestController
@Scope("request")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/touristguide/rest/test/")
public class TestRestController {
    private final CategoryDao categoryDao;
    private final PlaceDao placeDao;
    private final UserDetailDao userDetailDao;
    private final AccountDao accountDao;

    @Autowired
    public TestRestController(CategoryDao categoryDao, PlaceDao placeDao, UserDetailDao userDetailDao, AccountDao accountDao) {
        this.categoryDao = categoryDao;
        this.placeDao = placeDao;
        this.userDetailDao = userDetailDao;
        this.accountDao = accountDao;
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    private ResponseEntity insertCategoriesTestValues() {
        try {
            List<Category> categories = FillDataBase.categoryTestData();
            this.categoryDao.saveAll(categories);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "account", method = RequestMethod.GET)
    private ResponseEntity insertAccountTestValues() {
        try {
            UserDetail userDetail = this.userDetailDao.save(FillDataBase.userDetailTestData());
            this.accountDao.save(FillDataBase.accountTestData(userDetail));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "places", method = RequestMethod.GET)
    private ResponseEntity insertPlacesTestValues() {
        try {
            Iterable<Category> categories = categoryDao.findAll();
            List<Category> categoryDTOList = new ArrayList<>();
            for(Category tmpCategory : categories){
                categoryDTOList.add(tmpCategory);
            }

            List<Place> places = FillDataBase.placeTestData(categoryDTOList, accountDao.findAll().iterator().next());

            this.placeDao.saveAll(places);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
