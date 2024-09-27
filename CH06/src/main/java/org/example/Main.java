package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        tx.begin();

        Member member = new Member();
        member.setName("mmmm");
        em.persist(member);

        // 다대1 선호 1대다는 그냥 존재하지 않는 걸로 생각해라
        Team team1 = new Team();
        team1.getMembers().add(member);
        em.persist(team1);

        Locker locker = new Locker();
        locker.setName("locker1");
        em.persist(locker);

        member.setLocker(locker);

        tx.commit();
        System.out.println("Hello World");
    }
}