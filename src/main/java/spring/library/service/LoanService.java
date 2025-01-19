package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import spring.library.repository.LoanRepository;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;


}
