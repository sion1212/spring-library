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
    private List<BookDto> bookDtoList;
//    private List<Book> bookDtoList;
    // todo: 이후 BookDto 변경 필요 -> 순환참조를 끊어주거나.. findby~~ 불러오는?

    public static UserDto from(User user) {
        return UserDto.builder()
                .idNumber(user.getIdNumber())
                .name(user.getName())
                .feature(user.getFeature())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .bookDtoList(user.getBookList()!=null ? user.getBookList().stream().map(BookDto::from).toList() : null)
                .build();
    }

}
