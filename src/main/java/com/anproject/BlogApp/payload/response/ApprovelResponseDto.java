package com.anproject.BlogApp.payload.response;

import com.anproject.BlogApp.entity.Approval;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovelResponseDto {
    private Long id;
    private boolean approveInfo;
    private Date approveDate;

    private News news;
    private User user;

    public static ApprovelResponseDto mapEntityToResponseDto(Approval approval){
        return ApprovelResponseDto.builder()
                .id(approval.getId())
                .approveInfo(approval.isApproveInfo())
                .approveDate(approval.getApproveDate())
                .news(approval.getNews())
                .user(approval.getUser())
                .build();
    }

}
