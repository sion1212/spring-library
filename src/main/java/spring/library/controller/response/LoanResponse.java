package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import spring.library.dto.LoanDto;

import java.time.LocalDate;

@Builder
@Getter
public class LoanResponse {
    private Long bookId;
    private String title;
    private String author;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private int renewalCount;

    public static LoanResponse from(LoanDto loanDto) {
        return LoanResponse.builder()
                .bookId(loanDto.getBookId())
                .title(loanDto.getBookTitle())
                .author(loanDto.getBookAuthor())
                .loanDate(loanDto.getLoanDate())
                .dueDate(loanDto.getDueDate())
                .renewalCount(loanDto.getRenewalCount())
                .build();
    }
}
