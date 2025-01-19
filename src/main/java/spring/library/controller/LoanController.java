package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.MemberIdRequest;
import spring.library.controller.response.BookResponse;
import spring.library.controller.response.LoanResponse;
import spring.library.domain.Book;
import spring.library.dto.BookDto;
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

//    @GetMapping("/books/checkout")
//    public ResponseEntity<List<BookResponse>> loadBookListOfMemberId(@RequestParam Long memberId) {
//        List<BookDto> bookDtoList =
//    }
}
