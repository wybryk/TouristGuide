package pl.touristguide.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.touristguide.common.FillDataBase;
import pl.touristguide.model.Category;
import pl.touristguide.springapp.dao.CategoryDao;
import pl.touristguide.springapp.dto.CategoryDTO;
import pl.touristguide.springapp.mapper.CategoryMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<CategoryDTO> getCategories() throws Exception {
        Iterable<Category> categoryIterable = this.categoryDao.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for(Category tmpCategory : categoryIterable){
            categoryDTOList.add(CategoryMapper.toCategoryDTO(tmpCategory));
        }

        return categoryDTOList;
    }

    public Category getCategory(Long categoryId) throws Exception {
        return categoryDao.findById(categoryId).get();
    }

    public void insertCategory(CategoryDTO categoryDTO) throws Exception {
        this.categoryDao.save(CategoryMapper.toCategory(categoryDTO));
    }

    public void updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(categoryId);
        this.categoryDao.save(CategoryMapper.toCategory(categoryDTO));
    }

    public void deleteCategory(Long categoryId) {
        this.categoryDao.deleteById(categoryId);
    }
}
