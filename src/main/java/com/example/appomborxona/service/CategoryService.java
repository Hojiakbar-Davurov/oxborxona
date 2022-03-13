package com.example.appomborxona.service;

import com.example.appomborxona.entity.Category;
import com.example.appomborxona.payload.CategoryDto;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.CategoryReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryReposiroty categoryReposiroty;

    public Result add(CategoryDto categoryDto) {
        if (categoryDto.getParentCategoryId() == null) {

            // CHECK PARENT CATEGORY EXISTS
            boolean existsByName = categoryReposiroty.existsByNameAndParentCategoryId(categoryDto.getName(), null);
            if (existsByName)
                return new Result("This category already exists", false);

            // ADD PARENT CATEGORY
            Category category = new Category();
            category.setName(categoryDto.getName());
            categoryReposiroty.save(category);
            return new Result("Category added", true);
        } else {

            // CHECK CHILD CATEGORY WITH NAME EXISTS
            List<Category> categoryList = categoryReposiroty.findAllByParentCategoryId(categoryDto.getParentCategoryId());
            for (Category c : categoryList) {
                if (c.getName().equals(categoryDto.getName()))
                    return new Result("This category already exists", false);
            }

            // ADD CATEGORY
            Category category = new Category();
            category.setName(categoryDto.getName());

            // CHECK ID OF PARENT CATEGORY
            if (categoryDto.getParentCategoryId() != null) {
                Optional<Category> optionalCategory = categoryReposiroty.findById(categoryDto.getParentCategoryId());
                if (optionalCategory.isPresent())
                    category.setParentCategoryId(optionalCategory.get());
                else
                    return new Result("Id of parent category not found", false);
            }
            categoryReposiroty.save(category);
            return new Result("Category added", true);
        }
    }

    public List<Category> getAll() {
        return categoryReposiroty.findAll();
    }

    public Optional<Category> get(Integer id) {
        return categoryReposiroty.findById(id);
    }

    public Result edit(CategoryDto categoryDto, Integer id) {

        //  CHECK CATEGORY EXISTS
        Optional<Category> optionalCategory = categoryReposiroty.findById(id);
        if (optionalCategory.isEmpty())
            return new Result("Category not found", false);

        //  EDIT CATEGORY
        Category editingCategory = optionalCategory.get();
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optional = categoryReposiroty.findById(categoryDto.getParentCategoryId());
            optional.ifPresent(editingCategory::setParentCategoryId);
        }
        editingCategory.setName(categoryDto.getName());
        categoryReposiroty.save(editingCategory);
        return new Result("Category edited", true);
    }

    public Result delete(Integer id) {

        //  DELETE CHILD CATEGORY
        List<Category> categoryList = categoryReposiroty.findAllByParentCategoryId(id);
        if (!categoryList.isEmpty())
            categoryReposiroty.deleteAll(categoryList);

        //  DELETE PARENT CATEGORY
        categoryReposiroty.deleteById(id);
        return new Result("Category deleted", true);
    }
}
