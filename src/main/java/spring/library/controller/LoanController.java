package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.MemberIdRequest;
import spring.library.controller.response.LoanHistoryResponse;
import spring.library.controller.response.LoanResponse;
import spring.library.dto.LoanDto;
import spring.library.service.LoanService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/books/{bookId}/checkout")
    public ResponseEntity<LoanResponse> loanBookById(@PathVariable Long bookId, @RequestBody MemberIdRequest memberIdRequest) {
        LoanDto loanDto = loanService.loanBook(bookId, memberIdRequest);
        return ResponseEntity.ok().body(LoanResponse.from(loanDto));
    }

    @GetMapping("/books/checkout")
    public ResponseEntity<List<LoanResponse>> loadBookListOfMemberId(@RequestParam Long memberId) {
        List<LoanDto> loanDtoList = loanService.loanedBookList(memberId);
        List<LoanResponse> loanResponseList = loanDtoList.stream().map(LoanResponse::from).toList();
        return ResponseEntity.ok().body(loanResponseList);
    }

    @GetMapping("/books/history")
    public ResponseEntity<List<LoanHistoryResponse>> loanHistoryForMemberId(@RequestParam Long memberId) {
        List<LoanDto> loanDtoList = loanService.loanHistory(memberId);
        List<LoanHistoryResponse> loanHistoryResponseList = loanDtoList.stream().map(LoanHistoryResponse::from).toList();
        return ResponseEntity.ok().body(loanHistoryResponseList);
    }

    @PutMapping("/books/{bookId}/return")
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId, @RequestBody MemberIdRequest memberIdRequest) {
        loanService.returnBook(bookId, memberIdRequest);
        return ResponseEntity.ok().build();
    }
}
