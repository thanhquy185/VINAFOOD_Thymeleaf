package vn.tuhoc.foodshop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(Order.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Order_ {

	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#receiverAddress
	 **/
	public static volatile SingularAttribute<Order, String> receiverAddress;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#timeCreate
	 **/
	public static volatile SingularAttribute<Order, LocalDateTime> timeCreate;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#orderDetails
	 **/
	public static volatile ListAttribute<Order, OrderDetails> orderDetails;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#receiverPhone
	 **/
	public static volatile SingularAttribute<Order, String> receiverPhone;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#totalPrice
	 **/
	public static volatile SingularAttribute<Order, Long> totalPrice;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#payMethod
	 **/
	public static volatile SingularAttribute<Order, String> payMethod;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#id
	 **/
	public static volatile SingularAttribute<Order, Long> id;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#receiverFullname
	 **/
	public static volatile SingularAttribute<Order, String> receiverFullname;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#employee
	 **/
	public static volatile SingularAttribute<Order, User> employee;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order
	 **/
	public static volatile EntityType<Order> class_;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#customer
	 **/
	public static volatile SingularAttribute<Order, User> customer;
	
	/**
	 * @see vn.tuhoc.foodshop.domain.Order#status
	 **/
	public static volatile SingularAttribute<Order, Integer> status;

	public static final String RECEIVER_ADDRESS = "receiverAddress";
	public static final String TIME_CREATE = "timeCreate";
	public static final String ORDER_DETAILS = "orderDetails";
	public static final String RECEIVER_PHONE = "receiverPhone";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String PAY_METHOD = "payMethod";
	public static final String ID = "id";
	public static final String RECEIVER_FULLNAME = "receiverFullname";
	public static final String EMPLOYEE = "employee";
	public static final String CUSTOMER = "customer";
	public static final String STATUS = "status";

}

