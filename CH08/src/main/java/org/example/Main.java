package org.example;

import org.hibernate.tuple.entity.EntityMetamodel;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main  {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            Team team = new Team();
            team.setName("TEAM1");
            entityManager.persist(team);

            for(int i=0; i<20; i++) {
                Member member = new Member();
                member.setName("MEMBER"+i);
                member.setAge((int) (Math.random()*90) + 1);
                member.setTeam(team);
                entityManager.persist(member);
            }

            String jpql = "select m from Member AS m where m.team.name = :teamName";
            TypedQuery typedQuery = entityManager.createQuery(jpql, Member.class);
            typedQuery.setParameter("teamName", team.getName());
            List<Member> members = typedQuery.getResultList();
//            List<Member> members = entityManager.createQuery(jpql, Member.class).getResultList();
            System.out.println("Named Parameter = " +members.size());

            String jpql2 = "select m from Member as m where m.team.name = ?1 ";
            TypedQuery typedQuery2 = entityManager.createQuery(jpql2, Member.class);
            typedQuery2.setParameter(1, team.getName());
            List<Member> members2 = typedQuery2.getResultList();
            System.out.println("Positional Parameter = "+members.size());

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> select = query.select(m).where(cb.equal(m.get("team").get("name"), "TEAM1"));
            List<Member> results = entityManager.createQuery(select).getResultList();
            System.out.println("CriteriaQuery Result Size = " + results.size());

            List<String> usernames = entityManager
                    .createQuery("SELECT m.name FROM Member m", String.class).getResultList();

            Double orderAmountAvg = entityManager
                    .createQuery("SELECT AVG(o.price) FROM Order o", Double.class).getSingleResult();

            Query query1 = entityManager.createQuery("SELECT m.name, m.age FROM Member m");
//            List resultList = query1.getResultList();

//            Iterator iterator = resultList.iterator();
//            while (iterator.hasNext()) {
//                Object[] row = (Object[]) iterator.next();
//                String name = row[0].toString();
//                int age = Integer.parseInt(row[1].toString());
//                System.out.println(name+"/"+age);
//            }
            List<Object[]> resultList = query1.getResultList();
            List<MemberDTO> memberDTOS = new ArrayList<>();
            for(Object[] row : resultList) {
                MemberDTO memberDTO = new MemberDTO();
                memberDTO.setName((String) row[0]);
                memberDTO.setAge((Integer) row[1]);
                memberDTOS.add(memberDTO);
//                String username = (String) row[0];
//                Integer age = (Integer) row[1];
//                System.out.println(username +"/"+ age);
            }

            // NEW 명령어 사용
            TypedQuery<MemberDTO> query2 =
                    entityManager.createQuery(
                            "SELECT new org.example.MemberDTO(m.name, m.age) FROM Member m",
                            MemberDTO.class
                    );
            List<MemberDTO> resultUserDTO = query2.getResultList();
            System.out.println(resultUserDTO.size());

            TypedQuery query3 = entityManager
                    .createQuery("SELECT m FROM Member m ORDER BY m.name DESC", Member.class);
            query3.setFirstResult(0);
            query3.setMaxResults(10);
            System.out.println("Paging Result = "+query3.getResultList().size());

            // 회원수,나이합, 평균나이, 최대나이, 최소나이
            Query query4 = entityManager
                    .createQuery("SELECT COUNT(m), SUM(m.age), AVG(m.age), MAX(m.age), MIN(m.age) FROM Member m");
            List<Object[]> result = query4.getResultList();
            for(Object[] row : result) {
                System.out.println("COUNT = "+row[0]);
                System.out.println("SUM = "+row[1]);
                System.out.println("AVG = "+row[2]);
                System.out.println("MAX = "+row[3]);
                System.out.println("MIN = "+row[4]);
            }

            String joinQuery = "SELECT m.name, t.name FROM Member m JOIN m.team t "
                    + " WHERE t.name = 'TEAM1' "
                    + " ORDER BY m.age DESC";
            List<Object[]> joinResult = entityManager.createQuery(joinQuery).getResultList();
            for(Object[] row : joinResult) {
                System.out.println("name = "+ row[0]);
                System.out.println("team name = "+ row[1]);
            }
//            Product product = new Product();
//            product.setName("PRODUCT1");
//            entityManager.persist(product);
//
//            Order order = new Order();
//            order.setMember(member);
//            order.setProduct(product);
//            entityManager.persist(order);
//
//            entityManager.flush();
//            entityManager.clear();
//
//            Member findMember = entityManager.find(Member.class, member.getId());
//            List<Order> orders = findMember.getOrders();
//
//            System.out.println(orders.getClass().getName());
//            System.out.println("orders = "+ orders.get(0).getProduct().getName());

            //saveWithCascade(entityManager);
            //saveNoCascade(entityManager);
//            removeNoCascade(entityManager);
            //removeWithCascade(entityManager);

//            saveAndRemoveWithCascade(entityManager);

//            Member findMember = entityManager.getReference(Member.class, 1L);
//            entityManager.clear();
//            transaction.commit();
//            entityManager.close();
//            System.out.println("회원이름 : "+  findMember.getName());

//            Team findTeam = entityManager.getReference(Team.class, team.getId());
//            System.out.println(findTeam.getName());

            //printUserAndTeam(entityManager, 1L);

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

        System.out.println("회원이름 : "+  findMember.getName());
//        System.out.println("소속팀 : "+ team.getName());

        System.out.println("member.class :" + findMember.getClass().getName());
        System.out.println("team.class :" + team.getClass().getName());

    }

    public static void saveNoCascade(EntityManager em) {
        Parent parent = new Parent();
        em.persist(parent);

        Child child = new Child();
        child.setParent(parent);
        em.persist(child);

        Child child2 = new Child();
        child2.setParent(parent);
        em.persist(child2);
    }

    public static void saveWithCascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        em.persist(parent);
    }

    public static void removeNoCascade(EntityManager em) {
        Parent findParent = em.find(Parent.class, 1L);
        Child child1 = em.find(Child.class, 2L);
        Child child2 = em.find(Child.class, 3L);

        em.remove(child1);
        em.remove(child2);
        em.remove(findParent);
    }

    public static void removeWithCascade(EntityManager em) {
        Parent findParent = em.find(Parent.class, 1L);
        em.remove(findParent);
    }

    public static void saveAndRemoveWithCascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);
        em.persist(parent);

        Parent findParent = em.find(Parent.class, parent.getId());
        findParent.getChildren().remove(0);

    }
}
