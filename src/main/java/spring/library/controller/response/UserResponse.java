package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import spring.library.dto.UserDto;

@Getter
@Builder
public class UserResponse {
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static UserResponse from(UserDto userDto) {
        return UserResponse.builder()
                .idNumber(userDto.getIdNumber())
                .name(userDto.getName())
                .feature(userDto.getFeature())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }
}
