package spring.library.controller.request.purchase_requests;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PurchaseProcessRequest {
        private Long memberId;
        private Long bookId;
        private String title;
        private String author;
        private LocalDate requestDate;
        private LocalDate dateOfProcess;
        private String processResult;
}
