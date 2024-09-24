package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        testSave(em);
        deleteRelation(em);
        queryJoin(em);
        tx.commit();

//        Member member1 = new Member("user01", "회원1");
//        Member member2 = new Member("user01", "회원1");
//
//        Team team1 = new Team("team1", "팀1");
//
//        team1.setId("team1");
//        team1.setName("팀1");
//
//        member1.setTeam(team1);
//        member2.setTeam(team1);
//
//        Team team = member1.getTeam();
//        System.out.println("member1의 팀은 = " + team.getName());

    }
    public static void testSave(EntityManager em) {

        Team team1 = new Team();
        team1.setId("team1");
        team1.setName("팀1");
        em.persist(team1);

        Team team2 = new Team();
        team2.setId("team2");
        team2.setName("팀2");
        em.persist(team2);

        Member member1 = new Member("user01", "홍길동1");
        Member member2 = new Member("user02", "홍길동2");

        member1.setTeam(team1);
        member2.setTeam(team2);

        em.persist(member1);
        em.persist(member2);

        Member findMember = em.find(Member.class, member1.getId());
        Team findTeam = findMember.getTeam();
        System.out.println(findTeam.getName());

        member2.setTeam(team2);
    }

    public static void queryJoin(EntityManager em) {
        String jpql = "SELECT m FROM Member m JOIN m.team t WHERE t.name = :teamName";

        List<Member> members = em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();
        for (Member member : members) {
            System.out.println("[queryJoin] member.username = " + member.getUsername());
        }
    }

    public static void deleteRelation(EntityManager em) {
//        Member member = em.find(Member.class, "user02");
//        member.setTeam(null);
        // team2 삭제


    }
}