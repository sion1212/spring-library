package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();

    public static User from(UserRequest userRequest) {
        return User.builder()
                .idNumber(userRequest.getIdNumber())
                .name(userRequest.getName())
                .feature(userRequest.getFeature())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
    }

    public void update(User user) {
        idNumber = user.getIdNumber();
        name = user.getName();
        feature = user.getFeature();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
    }
}
