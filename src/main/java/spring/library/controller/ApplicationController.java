package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.purchase_requests.PurchaseProcessRequest;
import spring.library.controller.request.purchase_requests.PurchaseRequest;
import spring.library.controller.response.PurchaseResponse;
import spring.library.dto.ApplicationDto;
import spring.library.service.ApplicationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/books/purchase-requests")
    public ResponseEntity<Void> applyPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest) {
        applicationService.apply(purchaseRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/books/purchase-requests")
    public ResponseEntity<List<PurchaseResponse>> getPurchaseRequests(@RequestParam Long memberId) {
        System.out.println(memberId);
        List<ApplicationDto> applicationDtoList = applicationService.getList(memberId);
        return ResponseEntity.ok().body(applicationDtoList.stream().map(PurchaseResponse::from).toList());
    }

    @PutMapping("/books/purchase-requests")
    public ResponseEntity<Void> processPurchaseRequest(@RequestBody PurchaseProcessRequest purchaseProcessRequest) {
        applicationService.process(purchaseProcessRequest);
        return ResponseEntity.ok().build();
    }
}
