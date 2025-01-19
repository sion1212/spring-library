package spring.library.controller.request;

import lombok.Getter;

@Getter
public class MemberRequest {
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
}
