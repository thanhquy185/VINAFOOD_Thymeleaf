package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cart.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Cart_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.Cart#totalPrice
	 **/
	public static volatile SingularAttribute<Cart, Long> totalPrice;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Cart#cartDetails
	 **/
	public static volatile ListAttribute<Cart, CartDetails> cartDetails;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Cart#id
	 **/
	public static volatile SingularAttribute<Cart, Long> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Cart
	 **/
	public static volatile EntityType<Cart> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Cart#user
	 **/
	public static volatile SingularAttribute<Cart, User> user;

	public static final String TOTAL_PRICE = "totalPrice";
	public static final String CART_DETAILS = "cartDetails";
	public static final String ID = "id";
	public static final String USER = "user";

}

