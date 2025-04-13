package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderDetailsId.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrderDetailsId_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetailsId#orderId
	 **/
	public static volatile SingularAttribute<OrderDetailsId, Long> orderId;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetailsId#foodId
	 **/
	public static volatile SingularAttribute<OrderDetailsId, Long> foodId;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.OrderDetailsId
	 **/
	public static volatile EmbeddableType<OrderDetailsId> class_;

	public static final String ORDER_ID = "orderId";
	public static final String FOOD_ID = "foodId";

}

