package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.User;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    private List<BookDto> bookDtoList;

    public static UserDto from(User user) {
        return UserDto.builder()
                .idNumber(user.getIdNumber())
                .name(user.getName())
                .feature(user.getFeature())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bookDtoList(user.getBookList().stream().map(BookDto::from).toList())
                .build();
    }
    public static UserDto saveFrom(User user) {
        return UserDto.builder()
                .idNumber(user.getIdNumber())
                .name(user.getName())
                .feature(user.getFeature())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bookDtoList(null)
                .build();
    }


}
