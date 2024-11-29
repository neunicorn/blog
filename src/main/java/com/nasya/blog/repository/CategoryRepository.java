package com.nasya.blog.repository;

import com.nasya.blog.entity.Category;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByIsDeleted(boolean isDeleted, PageRequest page);

    Optional<Category> findFirstById(Integer id);
}
