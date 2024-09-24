package org.example;

public class Main {
    public static void main(String[] args) {

        Member member1 = new Member("user01", "회원1");
        Member member2 = new Member("user01", "회원1");

        Team team1 = new Team("team1", "팀1");

        team1.setId("team1");
        team1.setName("팀1");

        member1.setTeam(team1);
        member2.setTeam(team1);

        Team team = member1.getTeam();
        System.out.println("member1의 팀은 = " + team.getName());

    }
}