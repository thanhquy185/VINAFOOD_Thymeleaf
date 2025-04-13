package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(Role.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Role_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role#dateUpdate
	 **/
	public static volatile SingularAttribute<Role, Date> dateUpdate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role#name
	 **/
	public static volatile SingularAttribute<Role, String> name;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role#id
	 **/
	public static volatile SingularAttribute<Role, Integer> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role
	 **/
	public static volatile EntityType<Role> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role#users
	 **/
	public static volatile ListAttribute<Role, User> users;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Role#status
	 **/
	public static volatile SingularAttribute<Role, Boolean> status;

	public static final String DATE_UPDATE = "dateUpdate";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USERS = "users";
	public static final String STATUS = "status";

}

