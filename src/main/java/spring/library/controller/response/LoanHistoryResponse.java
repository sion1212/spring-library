package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import spring.library.dto.LoanDto;

import java.time.LocalDate;

@Getter
@Builder
public class LoanHistoryResponse {
    private Long bookId;
    private String title;
    private String author;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private int renewalCount;
    private Boolean isReturned;

    public static LoanHistoryResponse from(LoanDto loanDto){
        return LoanHistoryResponse.builder()
                .bookId(loanDto.getBookId())
                .title(loanDto.getBookTitle())
                .author(loanDto.getBookAuthor())
                .loanDate(loanDto.getLoanDate())
                .dueDate(loanDto.getDueDate())
                .renewalCount(loanDto.getRenewalCount())
                .isReturned(loanDto.getIsReturned())
                .build();
    }
}
