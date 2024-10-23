package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
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
        save();
        find();
        System.out.println("Hello World");
    }

    public static void save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Product product = new Product();
        product.setName("커피");
        em.persist(product);

        Product product2 = new Product();
        product2.setName("아이스크림");
        em.persist(product2);


        Member member = new Member();
        member.setName("홍길동");

//        member.getProducts().add(product);
//        member.getProducts().add(product2);

        em.persist(member);

        Member member2 = new Member();
        member2.setName("고길동");
//        member2.getProducts().add(product2);
        em.persist(member2);

        Order memberProduct = new Order();
        memberProduct.setMember(member); // 홍길동
        memberProduct.setProduct(product); // 커피
        memberProduct.setOrderAmount(2); // 2잔
        memberProduct.setOrderDate(LocalDateTime.now());
        em.persist(memberProduct);

        Order memberProduct2 = new Order();
        memberProduct2.setMember(member); // 고길동
        memberProduct2.setProduct(product); // 커피
        memberProduct2.setOrderAmount(3); // 3잔
        memberProduct2.setOrderDate(LocalDateTime.now());
        em.persist(memberProduct2);

        Order memberProduct3 = new Order();
        memberProduct3.setMember(member); // 홍길동
        memberProduct3.setProduct(product2); // 아이스크림
        memberProduct3.setOrderAmount(1); // 1개
        memberProduct3.setOrderDate(LocalDateTime.now());
        em.persist(memberProduct3);


        tx.commit();
    }

    public static void find() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();



        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember(2L);
        memberProductId.setProductId(1L);

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);

        Member findMember = memberProduct.getMember();
        Product findProduct = memberProduct.getProduct();

        System.out.println(" member = " + findMember.getName());
        System.out.println(" product = " + findProduct.getName());
        System.out.println(" orderAmount = " + Order.getOrderAmount());


//        member.getProducts().forEach(System.out::println);

//        List<Product> productList = member.getProducts();
//        for (Product product : productList) {
//            System.out.println(product.getName());
//        }
//
//        Product product2 = em.find(Product.class, 2L);
//        List<Member> members = product2.getMemberList();
//        for(Member member1 : members) {
//            System.out.println(member1.getName());
//        }
    }
}