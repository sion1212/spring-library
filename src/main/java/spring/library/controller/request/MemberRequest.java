package spring.library.controller.request;

import lombok.Getter;

@Getter
public class MemberRequest {
    private String name;
    private int idNumber;
    private String feature;
    private String email;
    private String phoneNumber;
}
