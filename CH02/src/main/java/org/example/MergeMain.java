package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        Member member = createMember("memberA3", "회원1", 20);
        System.out.println(member.getUsername());
        // 준영속
        member.setUsername("member8");

        mergeMember(member);
    }

    static void mergeMember(Member member) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        // member 는 준영속 상태
        Member mergeMember = em.merge(member);
        // mergeMember 영속 상태
        tx.commit();

        System.out.println("member = "+member.getUsername());
        System.out.println("mergeMember = "+mergeMember.getUsername());

        System.out.println("em contains member = "+ em.contains(member));
        System.out.println("em contains mergeMember = "+ em.contains(mergeMember));

        em.close();
    }

    static Member createMember(String id, String username, Integer age) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);

        em.persist(member);

        tx.commit();

        em.close();

        return member;
    }
}
