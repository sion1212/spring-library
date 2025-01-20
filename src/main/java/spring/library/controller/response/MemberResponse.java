package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import spring.library.dto.MemberDto;

@Getter
@Builder
public class MemberResponse {
    private Long memberId;
    private String name;
    private int idNumber;
    private String feature;
    private String email;
    private String phoneNumber;

    public static MemberResponse from(MemberDto memberDto) {
        return MemberResponse.builder()
                .memberId(memberDto.getMemberId())
                .idNumber(memberDto.getIdNumber())
                .name(memberDto.getName())
                .feature(memberDto.getFeature())
                .email(memberDto.getEmail())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }
}
