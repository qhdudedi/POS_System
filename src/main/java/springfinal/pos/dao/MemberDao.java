package springfinal.pos.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import springfinal.pos.domain.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class MemberDao {
    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member selectByMemberId(String memberId) {
        List<Member> results = jdbcTemplate.query(
                "select * from member where MEMBERID = ?",
                memberRowMapper, memberId
        );
        return results.isEmpty() ? null : results.get(0);
    }
    public Member selectById(Long id) {
        List<Member> results = jdbcTemplate.query(
                "select * from member where ID = ?",
                memberRowMapper, id
        );
        return results.isEmpty() ? null : results.get(0);
    }
    public List<Member> selectAll(){
        return this.jdbcTemplate.query("select * from member", memberRowMapper);
    }
    public void save(final Member member) {
        KeyHolder keyHolder= new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                        PreparedStatement pstmt= con.prepareStatement("insert into MEMBER (NAME, EMAIL, MEMBERID, PASSWORD, ROLE) values (?, ?, ?, ?, ?)",new String[] {"ID"});
                        pstmt.setString(1, member.getName());
                        pstmt.setString(2, member.getEmail());
                        pstmt.setString(3, member.getMemberId());
                        pstmt.setString(4, member.getPassword());
                        pstmt.setString(5, member.getRole());

                        return pstmt;
                    }
                },
                keyHolder);
        Number keyValue= keyHolder.getKey();
        member.setId(keyValue.longValue());
    }
    public void update(Member member) {
        jdbcTemplate.update(
                "update MEMBER set NAME = ?, EMAIL = ?, PASSWORD = ?, ROLE = ? where MEMBERID = ? ",
                member.getName(), member.getEmail(), member.getPassword(), member.getRole(), member.getMemberId()
        );
    }
    private final RowMapper<Member> memberRowMapper = (ResultSet rs, int rowNum) -> {
        Member member = new Member();
        member.setName(rs.getString("NAME"));
        member.setEmail(rs.getString("EMAIL"));
        member.setMemberId(rs.getString("MEMBERID"));
        member.setPassword(rs.getString("PASSWORD"));
        member.setRole(rs.getString("ROLE"));
        return member;
    };
}


