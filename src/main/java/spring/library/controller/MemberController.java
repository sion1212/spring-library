package spring.library.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.library.controller.request.MemberRequest;
import spring.library.controller.response.MemberResponse;
import spring.library.dto.MemberDto;
import spring.library.service.MemberService;

import java.util.List;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getMembers() {
        List<MemberResponse> members = memberService.getMembers().stream().map(MemberResponse::from).toList();
        return ResponseEntity.ok().body(members);
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest memberRequest) {
        MemberDto memberDto = memberService.save(memberRequest);
        return ResponseEntity.ok().body(MemberResponse.from(memberDto));
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId, @RequestBody MemberRequest memberRequest) {
        MemberDto memberDto = memberService.update(memberId, memberRequest);
        return ResponseEntity.ok().body(MemberResponse.from(memberDto));
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.ok().build();
    }
}
