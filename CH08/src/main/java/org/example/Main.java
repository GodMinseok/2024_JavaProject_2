package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();


            Team team = new Team();
            team.setName("TEAM1");
            entityManager.persist(team);


            Member member = new Member();
            member.setName("MEMBER1");
            member.setTeam(team);
            entityManager.persist(member);

//            entityManager.flush();
//            entityManager.clear();

            Member findMember = entityManager.getReference(Member.class, 1L);
            entityManager.clear();
            transaction.commit();
            entityManager.close();
            System.out.println("회원이름 : " + findMember.getName());

//            printUserAndTeam(entityManager, 1L);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    public static void printUserAndTeam(EntityManager em, Long id) {
        Member findMember = em.getReference(Member.class, id);
        Team team = findMember.getTeam();

        em.close();
        System.out.println("화원이름 : " + findMember.getName());
        System.out.println("소속 팀 : " + team.getName());

        System.out.println("member.class : " + findMember.getClass().getName());
    }
}