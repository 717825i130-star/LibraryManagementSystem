package model;

public class PremiumMember extends Member {

    public PremiumMember(int memberId, String memberName) {
        super(memberId, memberName);
    }

    @Override
    public int getMaxBooks() {
        return 5;
    }

    @Override
    public int getBorrowDays() {
        return 14;
    }

    @Override
    public void displayMember() {
        System.out.println("Member ID   : " + getMemberId());
        System.out.println("Member Name : " + getMemberName());
        System.out.println("Member Type : Premium");
    }
}