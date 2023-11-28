package springfinal.pos.domain;

public class MemberForm {
    private String name;
    private String memberId;
    private String email;
    private String password;
    private String role;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}

    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}
