package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.dto.LoanDto;

import java.time.LocalDate;

@Builder
@Getter
public class LoanResponse {
    private LocalDate dueDate;
    private LocalDate loanDate;
    private int renewalCount;
    private Boolean isReturned;

    private Long memberId;
    private String memberName;
    private Long bookId;
    private String bookTitle;

    public static LoanResponse from(LoanDto loanDto) {
        return LoanResponse.builder()
                .dueDate(loanDto.getDueDate())
                .loanDate(loanDto.getLoanDate())
                .renewalCount(loanDto.getRenewalCount())
                .isReturned(loanDto.getIsReturned())
                .memberId(loanDto.getMemberId())
                .memberName(loanDto.getMemberName())
                .bookId(loanDto.getBookId())
                .bookTitle(loanDto.getBookTitle())
                .build();
    }
}
