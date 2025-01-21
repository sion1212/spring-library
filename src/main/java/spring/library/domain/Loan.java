package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_history_id")
    private BookHistory bookHistory;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private int renewalCount;
    private Boolean isReturned;

    //TODO: feature 이상한 값 들어왔을 떄 처리해주는 코드 필요
    public static Loan from(BookHistory book, Member member) {
        Loan loan = null;
        LocalDate now = LocalDate.now();

        String feature = member.getFeature();
        if(feature.equals("학생")){
            loan = Loan.builder()
                    .member(member)
                    .bookHistory(book)
                    .loanDate(now)
                    .dueDate(now.plusDays(10))
                    .renewalCount(1)
                    .isReturned(false)
                    .build();
        }
        else if(feature.equals("교직원")){
            loan = Loan.builder()
                    .member(member)
                    .bookHistory(book)
                    .loanDate(now)
                    .dueDate(now.plusDays(30))
                    .renewalCount(1)
                    .isReturned(false)
                    .build();
        }
        else{
            loan = Loan.builder()
                    .member(member)
                    .bookHistory(book)
                    .loanDate(now)
                    .dueDate(now.plusDays(110813))
                    .renewalCount(1)
                    .isReturned(false)
                    .build();
        }

        return loan;
    }
}
