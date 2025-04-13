package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderDetails.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrderDetails_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails#quantity
	 **/
	public static volatile SingularAttribute<OrderDetails, Long> quantity;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails#price
	 **/
	public static volatile SingularAttribute<OrderDetails, Long> price;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails#id
	 **/
	public static volatile SingularAttribute<OrderDetails, OrderDetailsId> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails
	 **/
	public static volatile EntityType<OrderDetails> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails#food
	 **/
	public static volatile SingularAttribute<OrderDetails, Food> food;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetails#order
	 **/
	public static volatile SingularAttribute<OrderDetails, Order> order;

	public static final String QUANTITY = "quantity";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String FOOD = "food";
	public static final String ORDER = "order";

}

