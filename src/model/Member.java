package model;

public class Member {

    private int memberId;
    private String memberName;

    public Member(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMaxBooks() {
        return 2;
    }

    public int getBorrowDays() {
        return 7;
    }

    public void displayMember() {
        System.out.println("Member ID   : " + memberId);
        System.out.println("Member Name : " + memberName);
        System.out.println("Member Type : Regular");
    }
}
