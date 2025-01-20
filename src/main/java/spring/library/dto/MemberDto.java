package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Member;

import static org.springframework.data.util.TypeUtils.type;

@Getter
@Setter
@Builder
public class MemberDto {
    private Long memberId;
    private int idNumber;
    private String name;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .idNumber(member.getIdNumber())
                .name(member.getName())
                .feature(member.getFeature())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }

}
