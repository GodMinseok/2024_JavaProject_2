package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

            Product product = new Product();
            product.setName("PRODUCT1");
            entityManager.persist(product);

            Order order = new Order();
            order.setMember(member);
            order.setProduct(product);
            entityManager.persist(order);


            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());
            List<Order> orders = findMember.getOrders();

            System.out.println("orders = " + orders.getClass().getName());

//            Member findMember = entityManager.getReference(Member.class, 1L);
//            entityManager.clear();
//            transaction.commit();
//            entityManager.close();
//            System.out.println("회원이름 : " + findMember.getName());

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
        System.out.println("team.class : " + team.getClass().getName());
    }

    public static void removeNoCascade(EntityManager em) {
        // Parent의 Cascade 옵션 제거 후 테스트
        Parent findParent = em.find(Parent.class, 3L);
        Child child1 = em.find(Child.class, 4L);
        Child child2 = em.find(Child.class, 5L);

        em.remove(child1);
        em.remove(child2);
        em.remove(findParent);
    }

    public static void saveWithCascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();
        
    }
    public static void removeWithCascade(EntityManager em) {
        // Parent의 Cascade 옵션 추가 후 테스트 CascadeType.REMOVE
        Parent findParent = em.find(Parent.class, 3L);
        em.remove(findParent);
    }
}