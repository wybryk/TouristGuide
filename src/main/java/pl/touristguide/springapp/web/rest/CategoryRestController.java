package pl.touristguide.springapp.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.touristguide.springapp.dto.CategoryDTO;
import pl.touristguide.springapp.service.CategoryService;

import java.util.List;

@RestController
@Scope("request")
@CrossOrigin(origins = "*")
@RequestMapping(value = "/touristguide/rest/categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity getCategories(){
        try {
            List<CategoryDTO> categoryDTOList = this.categoryService.getCategories();
            return new ResponseEntity(categoryDTOList, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity insertCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            this.categoryService.insertCategory(categoryDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{categoryId}", method = RequestMethod.PATCH)
    private ResponseEntity updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO){
        try {
            this.categoryService.updateCategory(categoryId, categoryDTO);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/{categoryId}", method = RequestMethod.DELETE)
    private ResponseEntity deleteCategory(@PathVariable Long categoryId){
        try {
            this.categoryService.deleteCategory(categoryId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
