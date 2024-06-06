package webly.meyzieu_gym.back.membermanagement;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    private final MemberRepository  memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @PostMapping("/new") 
    public Member createMember(@RequestBody Member member){
        // Member newMember = new Member();
        // newMember.setFirstName(Member.getFirstName());
        // newMember.setLastName(Member.getLastName());
        // newMember.setBirthDate(Member.getBirthDate());
        // newMember.setGender(Member.getGender());
        // newMember.setSchool(Member.getSchool());
        // newMember.setFirstName(Member.getFirstName());

        memberRepository.save(member);
        return member;
    }
}
