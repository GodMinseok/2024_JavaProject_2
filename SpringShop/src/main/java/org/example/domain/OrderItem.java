package org.example.domain;

import javax.persistence.*;

@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

//    @Column(name = "order_id")
//    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @Column(name = "item_id")
//    private Long itemId;

    @ManyToOne // 다대 1 조인컬럼 자동지정
    @JoinColumn(name = "item_id")
    private Item item;


    @Column(name = "order_price")
    private Double orderPrice;

    private int count;
}
