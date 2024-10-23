package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // 엔티티매니저팩토리 생성
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        // 엔티티매니저 생성
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // 트랜잭션 획득
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Album album = new Album();
            album.setName("앨범1");
            album.setPrice(1000);
            album.setArtist("가수명");
            entityManager.persist(album);

            Book book = new Book();
            book.setName("책1");
            book.setPrice(1000);
            book.setAuthor("aaaa");
            book.setIsbn("123123");
            entityManager.persist(book);

            Movie movie = new Movie();
            movie.setName("영화1");
            movie.setPrice(1000);
            movie.setDirector("ddirector");
            movie.setActor("aactor");
            entityManager.persist(book);

            Member member = new Member();
            member.setName("mmmm");
            member.setEmail("mmmm@gmail.com");
            entityManager.persist(member);

            Seller seller = new Seller();
            seller.setName("mmmm");
            seller.setShopName("SSSS");
            entityManager.persist(seller);

            

//            ParentId parentId = new ParentId("myId1", "myId2");
//            Parent parent = new Parent();
//            parent.setName("mmmm");
//            parent.setParentId(parentId);
//            entityManager.persist(parent);
//
//            Parent findParent = entityManager.find(Parent.class, parentId);
//            System.out.println(findParent.getName());

//            Parent parent = new Parent();
//            parent.setId1("myId1");
//            parent.setId2("myId2");
//            entityManager.persist(parent);
//
//            ParentId parentId = new ParentId("myId1", "myId2");
//            Parent findParent = entityManager.find(Parent.class, parent.getId1());
//            System.out.println(findParent.getId1());

            entityTransaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
