package spring.library.domain;

import jakarta.persistence.*;
import lombok.*;
import spring.library.controller.request.purchase_requests.PurchaseProcessRequest;
import spring.library.controller.request.purchase_requests.PurchaseRequest;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private int purchaseRequestCount;
    private LocalDate requestDate;
    private LocalDate dateOfProcess;
    private String processResult;

    public static Application from(PurchaseRequest purchaseRequest, Member member){
        return Application.builder()
                .member(member)
                .title(purchaseRequest.getTitle())
                .author(purchaseRequest.getAuthor())
                .publisher(purchaseRequest.getPublisher())
                .publicationYear(purchaseRequest.getPublicationYear())
                .purchaseRequestCount(purchaseRequest.getPurchaseRequestCount())
                .requestDate(LocalDate.now())
                .dateOfProcess(null)
                .processResult("신청")
                .build();
    }

    public void update(PurchaseProcessRequest purchaseProcessRequest){
        dateOfProcess = LocalDate.now();
        processResult = purchaseProcessRequest.getProcessResult();
    }
}
