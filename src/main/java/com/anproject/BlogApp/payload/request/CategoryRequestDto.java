package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDto {
    private Long id;
    private String categoryName;

    public static Category mapRequestDtoToEntity(CategoryRequestDto requestDto){
        return Category.builder()
                .id(requestDto.getId())
                .categoryName(requestDto.getCategoryName())
                .build();
    }

}
