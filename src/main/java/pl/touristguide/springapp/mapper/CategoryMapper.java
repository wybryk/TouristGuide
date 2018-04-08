package pl.touristguide.springapp.mapper;

import pl.touristguide.model.Category;
import pl.touristguide.springapp.dto.CategoryDTO;

public class CategoryMapper {
    public static Category toCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
