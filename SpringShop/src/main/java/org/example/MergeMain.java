package org.example;

import org.example.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MergeMain {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    public static void main(String[] args) {
        User user = createUser("userA3", "회원1", "서울", "테헤란로", 20);
        System.out.println(user.getName());
        // 준영속
        user.setName("member8");

        mergeUser (user);
    }

    static void mergeUser(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        // member 는 준영속 상태
        User mergeUser = em.merge(user);
        // mergeMember 영속 상태
        tx.commit();

        System.out.println("user = "+user.getName());
        System.out.println("mergeUser = "+mergeUser.getName());

        System.out.println("em contains user = "+ em.contains(user));
        System.out.println("em contains mergeUser = "+ em.contains(mergeUser));

        em.close();
    }

    static User createUser(String id, String username, String city, String street, Integer zipcode) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        User user = new User();
        user.setId(id);
        user.setName(username);
        user.setCity(city);
        user.setStreet(street);
        user.setZipcode(zipcode);

        em.persist(user);

        tx.commit();

        em.close();

        return user;
    }
}
