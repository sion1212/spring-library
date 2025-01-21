package spring.library.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.library.domain.Application;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ApplicationDto {
    private Long bookId;
    private Long memberId;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private int purchaseRequestCount;
    private LocalDate requestDate;
    private LocalDate dateOfProcess;
    private String processResult;

    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
                .bookId(application.getBookId())
                .memberId(application.getMember().getMemberId())
                .title(application.getTitle())
                .author(application.getAuthor())
                .publisher(application.getPublisher())
                .publicationYear(application.getPublicationYear())
                .purchaseRequestCount(application.getPurchaseRequestCount())
                .requestDate(application.getRequestDate())
                .dateOfProcess(application.getDateOfProcess())
                .processResult(application.getProcessResult())
                .build();
    }
}
