package org.example;

import org.example.domain.User;

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

            User member1 = new User();
            member1.setId("id1");
            member1.setName("username1");
            member1.setCity("city1");
            member1.setStreet("street1");
            member1.setZipcode(1);
            //--- 비영속 상태

            entityManager.persist(member1);
            //--- 영속 상태

            //entityManager.detach(member1);
            //--- 준영속 상태

            User findUser = entityManager.find(User.class, "test11");
            System.out.println(findUser.getName());
            findUser.setName("이름변경");

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
        User member = new User();
        member.setId(id);
        member.setName("testName2");
        member.setCity("city2");
        member.setStreet("street2");
        member.setZipcode(2);

        // 등록
        em.persist(member);

        member.setZipcode(20);

        User findMember = em.find(User.class, id);
        System.out.println("findMember = " + findMember.getName() +"/ zipcode = " + findMember.getZipcode());

        List<User> members = em.createQuery("select m from User m").getResultList();
        System.out.println("Members.size()="+members.size());

    }

    }