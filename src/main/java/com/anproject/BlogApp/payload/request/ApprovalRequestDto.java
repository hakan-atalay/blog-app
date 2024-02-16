package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.Approval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalRequestDto {
    private boolean approveInfo;
    private Date approveDate;

    public static Approval mapRequestDtoToEntity(ApprovalRequestDto requestDto){
        return Approval.builder()
                .approveInfo(requestDto.isApproveInfo())
                .approveDate(requestDto.getApproveDate())
                .build();
    }

}
