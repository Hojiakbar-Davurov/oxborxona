package com.example.appomborxona.repository;

import com.example.appomborxona.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryReposiroty extends JpaRepository<Category, Integer> {


    @Query(value = "SELECT * FROM Category WHERE parent_category_id_id = :id ", nativeQuery = true)
    List<Category> findAllByParentCategoryId(@Param("id") Integer id);

//    @Query(value = "DELETE  FROM Category WHERE parent_category_id_id = :id ", nativeQuery = true)
//    boolean deleteByAllByParentCategoryId(@Param("id") Integer id);


    boolean existsByName(String name);

    boolean existsByNameAndParentCategoryId(String name, Category parentCategoryId);
}
