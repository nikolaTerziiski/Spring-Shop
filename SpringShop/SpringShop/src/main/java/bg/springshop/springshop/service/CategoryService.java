package bg.springshop.springshop.service;

import bg.springshop.springshop.model.entity.Category;
import bg.springshop.springshop.model.entity.enums.CategoryEnum;

public interface CategoryService {
    Category findCategory(CategoryEnum categoryEnum);
}
