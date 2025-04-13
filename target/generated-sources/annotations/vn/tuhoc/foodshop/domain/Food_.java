package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Food.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Food_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#image
	 **/
	public static volatile SingularAttribute<Food, String> image;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#orderDetails
	 **/
	public static volatile ListAttribute<Food, OrderDetails> orderDetails;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#timeUpdate
	 **/
	public static volatile SingularAttribute<Food, LocalDateTime> timeUpdate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#price
	 **/
	public static volatile SingularAttribute<Food, Long> price;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#name
	 **/
	public static volatile SingularAttribute<Food, String> name;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#description
	 **/
	public static volatile SingularAttribute<Food, String> description;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#cartDetails
	 **/
	public static volatile ListAttribute<Food, CartDetails> cartDetails;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#id
	 **/
	public static volatile SingularAttribute<Food, Long> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#category
	 **/
	public static volatile SingularAttribute<Food, Category> category;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#inventory
	 **/
	public static volatile SingularAttribute<Food, Long> inventory;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food
	 **/
	public static volatile EntityType<Food> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Food#status
	 **/
	public static volatile SingularAttribute<Food, Boolean> status;

	public static final String IMAGE = "image";
	public static final String ORDER_DETAILS = "orderDetails";
	public static final String TIME_UPDATE = "timeUpdate";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CART_DETAILS = "cartDetails";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String INVENTORY = "inventory";
	public static final String STATUS = "status";

}

