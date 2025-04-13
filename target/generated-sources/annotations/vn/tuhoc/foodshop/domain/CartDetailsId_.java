package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EmbeddableType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CartDetailsId.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class CartDetailsId_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetailsId#cartId
	 **/
	public static volatile SingularAttribute<CartDetailsId, Long> cartId;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetailsId#foodId
	 **/
	public static volatile SingularAttribute<CartDetailsId, Long> foodId;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetailsId
	 **/
	public static volatile EmbeddableType<CartDetailsId> class_;

	public static final String CART_ID = "cartId";
	public static final String FOOD_ID = "foodId";

}

