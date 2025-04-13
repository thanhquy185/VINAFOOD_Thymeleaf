package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.time.LocalDateTime;

@StaticMetamodel(User.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class User_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#birthday
	 **/
	public static volatile SingularAttribute<User, LocalDate> birthday;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#image
	 **/
	public static volatile SingularAttribute<User, String> image;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#role
	 **/
	public static volatile SingularAttribute<User, Role> role;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#address
	 **/
	public static volatile SingularAttribute<User, String> address;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#gender
	 **/
	public static volatile SingularAttribute<User, Boolean> gender;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#timeUpdate
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> timeUpdate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#ordersFromEmployee
	 **/
	public static volatile ListAttribute<User, Order> ordersFromEmployee;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#cart
	 **/
	public static volatile SingularAttribute<User, Cart> cart;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#timeCreate
	 **/
	public static volatile SingularAttribute<User, LocalDateTime> timeCreate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#password
	 **/
	public static volatile SingularAttribute<User, String> password;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#phone
	 **/
	public static volatile SingularAttribute<User, String> phone;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#id
	 **/
	public static volatile SingularAttribute<User, Long> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#fullname
	 **/
	public static volatile SingularAttribute<User, String> fullname;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#email
	 **/
	public static volatile SingularAttribute<User, String> email;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#ordersFromCustomer
	 **/
	public static volatile ListAttribute<User, Order> ordersFromCustomer;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#username
	 **/
	public static volatile SingularAttribute<User, String> username;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.User#status
	 **/
	public static volatile SingularAttribute<User, Boolean> status;

	public static final String BIRTHDAY = "birthday";
	public static final String IMAGE = "image";
	public static final String ROLE = "role";
	public static final String ADDRESS = "address";
	public static final String GENDER = "gender";
	public static final String TIME_UPDATE = "timeUpdate";
	public static final String ORDERS_FROM_EMPLOYEE = "ordersFromEmployee";
	public static final String CART = "cart";
	public static final String TIME_CREATE = "timeCreate";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String ID = "id";
	public static final String FULLNAME = "fullname";
	public static final String EMAIL = "email";
	public static final String ORDERS_FROM_CUSTOMER = "ordersFromCustomer";
	public static final String USERNAME = "username";
	public static final String STATUS = "status";

}

