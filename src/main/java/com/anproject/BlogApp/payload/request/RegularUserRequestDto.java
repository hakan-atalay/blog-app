package com.anproject.BlogApp.payload.request;

import com.anproject.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegularUserRequestDto {
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String password;

    public static User mapRequestDtoToEntity(RegularUserRequestDto requestDto){
        return User.builder()
                .firstName(requestDto.firstName)
                .lastName(requestDto.lastName)
                .nickname(requestDto.nickname)
                .email(requestDto.email)
                .password(requestDto.password)
                .build();
    }
}
