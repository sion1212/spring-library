package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Book;
import spring.library.domain.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.util.TypeUtils.type;

@Getter
@Setter
@Builder
public class UserDto {
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static UserDto from(User user) {
        return UserDto.builder()
                .idNumber(user.getIdNumber())
                .name(user.getName())
                .feature(user.getFeature())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
