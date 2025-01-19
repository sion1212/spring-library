package spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import spring.library.service.LoanService;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

//    @PostMapping("/books/{bookId}/checkout")
//    public ResponseEntity<BookResponse> loanBookById(@PathVariable int bookId, @RequestBody BookRequest bookRequest) {
//
//    }
}
