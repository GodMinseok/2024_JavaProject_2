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

            // 로직 실행
            //logic(entityManager);

            Member member1 = new Member();
            member1.setId("id1");
            member1.setUsername("username1");
            member1.setAge(1);
            //--- 비영속 상태

            entityManager.persist(member1);
            //--- 영속 상태

            //--- 준영속 상태

            Member findUser = entityManager.find(Member.class, "test11");
            System.out.println(findUser.getUsername());
            findUser.setUsername("이름변경");

            // 1차 캐시에서 가져옴
//            Member findMember = entityManager.find(Member.class, "id1000");
//            System.out.println(findMember.getUsername());



            // entityManager.detach(member1);
            //--- 준영속 상태

//            Member member2 = new Member();
//            member2.setId("id2");
//            member2.setUsername("username2");
//            member2.setAge(2);

//            entityManager.persist(member2);

//            List<Member> members = entityManager.createQuery("select m from Member m").getResultList();
//            System.out.println("Members.size()="+members.size());

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