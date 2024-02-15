package com.anproject.BlogApp.payload.response;

import com.anproject.BlogApp.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String categoryName;

    public static CategoryResponseDto mapEntitytoResponseDto(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build();
    }

}
