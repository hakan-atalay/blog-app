package com.anproject.BlogApp.service;

import com.anproject.BlogApp.entity.Category;
import com.anproject.BlogApp.payload.request.CategoryRequestDto;
import com.anproject.BlogApp.payload.response.CategoryResponseDto;
import com.anproject.BlogApp.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseDto getById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kategori bulunamadı."));
        CategoryResponseDto categoryResponseDto = CategoryResponseDto.mapEntitytoResponseDto(category);
        return categoryResponseDto;
    }

    public void save(CategoryRequestDto categoryRequestDto){
        Category category = CategoryRequestDto.mapRequestDtoToEntity(categoryRequestDto);
        categoryRepository.save(category);
    }

    public List<CategoryResponseDto> getAllCategories(){
        List<Category> categoryList = this.categoryRepository.findAllByIdAsc();
        List<CategoryResponseDto> categoryResponseDtoList = categoryList.stream()
                .map(category -> CategoryResponseDto.mapEntitytoResponseDto(category))
                .collect(Collectors.toList());
        return categoryResponseDtoList;
    }

    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

    public Page<CategoryResponseDto> findByNameContains(Pageable pageable){
        Page category = categoryRepository.findAllCategoriesWithOptional(pageable);
        return category;
    }

}
