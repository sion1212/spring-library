package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Loan;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class LoanDto {
    private LocalDate dueDate;
    private LocalDate loanDate;
    private int renewalCount;
    private Boolean isReturned;

    private Long memberId;
    private String memberName;
    private Long bookId;
    private String bookTitle;
    private String bookAuthor;

    public static LoanDto from(Loan loan){
        return LoanDto.builder()
                .loanDate(loan.getLoanDate())
                .dueDate(loan.getDueDate())
                .renewalCount(loan.getRenewalCount())
                .isReturned(loan.getIsReturned())
                .memberId(loan.getMember().getMemberId())
                .memberName(loan.getMember().getName())
                .bookId(loan.getBookHistory().getId())
                .bookTitle(loan.getBookHistory().getTitle())
                .bookAuthor(loan.getBookHistory().getAuthor())
                .build();
    }
}
