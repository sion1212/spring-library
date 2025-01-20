package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.MemberRequest;

import java.util.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member { // memberId -> Post.member.id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Loan> loanList = new ArrayList<>();

    public static Member from(MemberRequest memberRequest) {
        return Member.builder()
                .idNumber(memberRequest.getIdNumber())
                .name(memberRequest.getName())
                .feature(memberRequest.getFeature())
                .email(memberRequest.getEmail())
                .phoneNumber(memberRequest.getPhoneNumber())
                .build();
    }

    public void update(Member member) {
        idNumber = member.getIdNumber();
        name = member.getName();
        feature = member.getFeature();
        email = member.getEmail();
        phoneNumber = member.getPhoneNumber();
    }
}
