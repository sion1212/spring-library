package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private int renewalCount = 1;
    private Boolean isReturned = false;

    public static Loan from(Book book, Member member) {
        LocalDate now = LocalDate.now();
        LocalDate afterFiveDays = now.plusDays(5);

        return Loan.builder()
                .member(member)
                .book(book)
                .loanDate(now)
                .dueDate(afterFiveDays)
                .renewalCount(1)
                .isReturned(false)
                .build();
    }
}
