package spring.library.controller.response;

import lombok.Builder;
import lombok.Getter;
import spring.library.dto.ApplicationDto;

import java.time.LocalDate;

@Getter
@Builder
public class PurchaseResponse {
      private Long bookId;
      private String title;
      private String author;
      private LocalDate requestDate;
      private LocalDate dateOfProcess;
      private String processResult;

      //TODO: dateOfProcess가 null일 때 null 이 아니라 ""로 표시되도록 해야함
      public static PurchaseResponse from(ApplicationDto applicationDto){
            return PurchaseResponse.builder()
                  .bookId(applicationDto.getBookId())
                  .title(applicationDto.getTitle())
                  .author(applicationDto.getAuthor())
                  .requestDate(applicationDto.getRequestDate())
                  .dateOfProcess(applicationDto.getDateOfProcess())
                  .processResult(applicationDto.getProcessResult())
                  .build();
      }
}
