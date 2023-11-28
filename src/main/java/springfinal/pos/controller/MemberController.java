package springfinal.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfinal.pos.domain.Member;
import springfinal.pos.domain.MemberForm;
import springfinal.pos.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    /** 회원가입 */
    @GetMapping("/members/new")
    public String create(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/memberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setMemberId(form.getMemberId());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setRole(form.getRole());
        memberService.join(member);
        return "redirect:/";
    }
    /** 로그인 */
    @GetMapping("/members/login")
    public String loginForm(){
        return "members/memberLogin";
    }

    @PostMapping(value = "/members/login")
    public String login(@RequestParam(name="memberId") String memberId, @RequestParam(name="password") String password){
        Member loginMember = memberService.login(memberId, password);
        if(loginMember == null){
            return "members/memberLogin";
        }
        return "index";
    }
}
