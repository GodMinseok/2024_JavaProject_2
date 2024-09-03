package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        // 엔티티 매니저 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();

            // 로직 생성
            logic(entityManager);

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

        private static void logic(EntityManager em){
            String id = "test2";
            Member member = new Member();
            member.setId(id);
            member.setUsername("testName2");
            member.setAge(2);

            // 등록
            em.persist(member);

            member.setAge(20);

            Member findMember = em.find(Member.class, id);
            System.out.println("findMember = " + findMember.getUsername() +"/ age = " + findMember.getAge());

            List<Member> members = em.createQuery("select m from Member m").getResultList();
            System.out.println("Members.size()="+members.size());

        }
}