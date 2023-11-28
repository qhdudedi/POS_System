package springfinal.pos.service;

import springfinal.pos.dao.MemberDao;
import springfinal.pos.domain.Member;

import java.util.List;

public class MemberService {
    private MemberDao memberDao;

    public MemberService (MemberDao memberDao){
        this.memberDao = memberDao;
    }

    /** 회원가입 */
    public void join(Member member){

        memberDao.save(member);
    }

    /** 회원 조회*/
    public Member selectByMemberId(String memberId){
        return memberDao.selectByMemberId(memberId);
    }
    public Member findbyId(Long id){
        return memberDao.selectById(id);
    }
    public List<Member> findAll(){
        return memberDao.selectAll();
    }

    /**로그인*/
    public Member login(String memberId, String password){
        Member member = memberDao.selectByMemberId(memberId);
        if(member == null) return null;
        if(member.getPassword().equals(password)) {
            return member;}
        else return null;
    }

}
