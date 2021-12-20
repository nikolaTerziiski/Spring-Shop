package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.entity.Category;
import bg.springshop.springshop.model.entity.enums.CategoryEnum;
import bg.springshop.springshop.repository.CategoryRepository;
import bg.springshop.springshop.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategory(CategoryEnum categoryEnum) {
        return this.categoryRepository.findByName(categoryEnum).orElse(null);
    }
}
