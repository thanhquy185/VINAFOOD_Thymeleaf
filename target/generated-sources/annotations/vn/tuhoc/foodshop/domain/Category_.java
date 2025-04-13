package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Category.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Category_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category#foods
	 **/
	public static volatile ListAttribute<Category, Food> foods;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category#timeUpdate
	 **/
	public static volatile SingularAttribute<Category, LocalDateTime> timeUpdate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category#name
	 **/
	public static volatile SingularAttribute<Category, String> name;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category#id
	 **/
	public static volatile SingularAttribute<Category, Long> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category
	 **/
	public static volatile EntityType<Category> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Category#status
	 **/
	public static volatile SingularAttribute<Category, Boolean> status;

	public static final String FOODS = "foods";
	public static final String TIME_UPDATE = "timeUpdate";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

