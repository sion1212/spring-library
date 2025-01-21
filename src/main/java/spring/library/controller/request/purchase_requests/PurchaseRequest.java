package spring.library.controller.request.purchase_requests;

import lombok.Getter;

@Getter
public class PurchaseRequest {
        private Long memberId;
        private String title;
        private String author;
        private String publisher;
        private int publicationYear;
        private int purchaseRequestCount;
}
