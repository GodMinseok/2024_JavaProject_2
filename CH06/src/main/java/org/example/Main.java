package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        locker.setMember(member);

        tx.commit();
        System.out.println("Hello World");
    }

    public static void save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Product product = new Product();
        product.setName("커피");
        em.persist(product);


        Member member = new Member();
        member.setName("홍길동");

        member.getProducts().add(product);

        em.persist(member);

        tx.commit();
    }

    public static void find() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member = em.find(Member.class, 2L);
//        member.getProducts().forEach(System.out::println);

        List<Product> productList = member.getProducts();
        for (Product product : productList) {
            System.out.println(product.getName());
        }
    }
}