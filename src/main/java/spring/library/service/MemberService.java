package spring.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.library.controller.request.MemberRequest;
import spring.library.domain.Book;
import spring.library.domain.Loan;
import spring.library.domain.Member;
import spring.library.dto.MemberDto;
import spring.library.repository.BookRepository;
import spring.library.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public MemberDto save(MemberRequest memberRequest){
        Member member = Member.from(memberRequest);
        memberRepository.save(member);
        MemberDto memberDto = MemberDto.from(member);
        return memberDto;
    }

    public List<MemberDto> getMembers(){
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtos = members.stream().map(MemberDto::from).toList();
        return memberDtos;
    }

    @Transactional
    public MemberDto update(Long memberId, MemberRequest memberRequest){
        Member memberOfIdNumber = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
        Member memberForUpdate = Member.from(memberRequest);
        memberOfIdNumber.update(memberForUpdate);
        return MemberDto.from(memberOfIdNumber);
    }

    public void delete(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member Not Found"));
        List<Loan> loanList = member.getLoanList();
        for(Loan loan : loanList){
            if(isThereABook(loan)){
                loan.getBookHistory().setStatus("대출가능");
                Book book = bookRepository.findById(loan.getBookHistory().getId()).orElseThrow(() -> new IllegalArgumentException("Book Not Found"));
                book.setStatus("대출가능");
            }
        }
        memberRepository.save(member);
        memberRepository.deleteById(memberId);
    }

    public boolean isThereABook(Loan loan){
        try{
            Book book = bookRepository.findById(loan.getBookHistory().getId()).orElseThrow(() -> new IllegalArgumentException("Book Not Found"));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
