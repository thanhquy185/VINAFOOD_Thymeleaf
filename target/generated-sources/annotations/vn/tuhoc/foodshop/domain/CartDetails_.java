package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CartDetails.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class CartDetails_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails#quantity
	 **/
	public static volatile SingularAttribute<CartDetails, Long> quantity;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails#price
	 **/
	public static volatile SingularAttribute<CartDetails, Long> price;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails#id
	 **/
	public static volatile SingularAttribute<CartDetails, CartDetailsId> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails
	 **/
	public static volatile EntityType<CartDetails> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails#cart
	 **/
	public static volatile SingularAttribute<CartDetails, Cart> cart;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.CartDetails#food
	 **/
	public static volatile SingularAttribute<CartDetails, Food> food;

	public static final String QUANTITY = "quantity";
	public static final String PRICE = "price";
	public static final String ID = "id";
	public static final String CART = "cart";
	public static final String FOOD = "food";

}

