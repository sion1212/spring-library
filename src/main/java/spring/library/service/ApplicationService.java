package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.library.controller.request.purchase_requests.PurchaseProcessRequest;
import spring.library.controller.request.purchase_requests.PurchaseRequest;
import spring.library.domain.Application;
import spring.library.domain.Book;
import spring.library.domain.Member;
import spring.library.dto.ApplicationDto;
import spring.library.repository.ApplicationRepository;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    //TODO: 같은 책이 추가된다면 purchaseRequestCount 만 +1 해주는 기능 필요
    public void apply(PurchaseRequest purchaseRequest) {
        String title = purchaseRequest.getTitle();
        String author = purchaseRequest.getAuthor();
        String publisher = purchaseRequest.getPublisher();
        int publicationYear = purchaseRequest.getPublicationYear();

        Book book = bookRepository.findBookByTitleAndAuthorAndPublisherAndPublicationYear(title, author, publisher, publicationYear);

        if(book == null){
            Member member = memberRepository.findById(purchaseRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("There is no member with this id"));
            Application application = Application.from(purchaseRequest, member);
            applicationRepository.save(application);
            return;
        }

        throw new IllegalArgumentException("This book is duplicated");
    }

    public List<ApplicationDto> getList(Long memberId){
        List<Application> applicationList = null;

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("There is no member with this memberId"));
        String feature = member.getFeature();

        if(feature.equals("관리자")){
            applicationList = applicationRepository.findAll();
        }
        else{
            applicationList = applicationRepository.findApplicationByMemberMemberId(memberId);
        }
        return applicationList.stream().map(ApplicationDto::from).toList();
    }

    public void process(PurchaseProcessRequest purchaseProcessRequest){
        Member member = memberRepository.findById(purchaseProcessRequest.getMemberId()).orElseThrow(() -> new IllegalArgumentException("There is no member with this id"));
        Book book = bookRepository.findById(purchaseProcessRequest.getBookId()).orElseThrow(() -> new IllegalArgumentException("There is no book with this id"));

        if(!member.getFeature().equals("관리자")){
            throw new IllegalArgumentException("You are not allowed to process this purchase process : Only 관리자");
        }

        String title = purchaseProcessRequest.getTitle();
        String author = purchaseProcessRequest.getAuthor();
        LocalDate requestDate = purchaseProcessRequest.getRequestDate();

        Application application = applicationRepository.findApplicationByTitleAndAuthorAndRequestDate(title, author, requestDate);
        application.update(purchaseProcessRequest);
        applicationRepository.save(application);
    }
}
