package vn.tuhoc.foodshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.CartDetails;
import vn.tuhoc.foodshop.domain.CartDetailsId;
import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.criteria.FoodCriteria;
import vn.tuhoc.foodshop.domain.criteria.ProductCriteria;
import vn.tuhoc.foodshop.repository.CartDetailsRepository;
import vn.tuhoc.foodshop.repository.CartRepository;
import vn.tuhoc.foodshop.repository.FoodRepository;
import vn.tuhoc.foodshop.service.specfication.FoodSpecification;
import vn.tuhoc.foodshop.service.specfication.ProductSpecification;

@Service
public class FoodService {
    // Properties
    private final FoodRepository foodRepository;
    private final CartRepository cartRepository;
    private final CartDetailsRepository cartDetailsRepository;

    // Constructors
    public FoodService(FoodRepository foodRepository, CartRepository cartRepository, CartDetailsRepository cartDetailsRepository) {
        this.foodRepository = foodRepository;
        this.cartRepository = cartRepository;
        this.cartDetailsRepository = cartDetailsRepository;
    }

    // Methods
    public Food getOneById(Long id) {
        return this.foodRepository.findOneById(id);
    }

    public List<Food> getAll() {
        return this.foodRepository.findAll();
    }

    public Page<Food> getAll(Pageable pageable, FoodCriteria foodCriteria) {
        //
        if (foodCriteria.getId() == null && foodCriteria.getName() == null && foodCriteria.getSort() == null
                && foodCriteria.getCategory() == null
                && foodCriteria.getStatus() == null) {
            return this.foodRepository.findAll(pageable);
        }

        //
        Specification<Food> combinedSpec = Specification.where(null);
        if (foodCriteria.getId() != null && foodCriteria.getId().isPresent()) {
            if (foodCriteria.getId().get().matches("\\d+")) {
                Specification<Food> currentSpec = FoodSpecification.idEqual(foodCriteria.getId().get());
                combinedSpec = combinedSpec.or(currentSpec);
            }
        }
        if (foodCriteria.getName() != null && foodCriteria.getName().isPresent()) {
            Specification<Food> currentSpec = FoodSpecification.nameLike(foodCriteria.getName().get());
            combinedSpec = combinedSpec.or(currentSpec);
        }
        if (foodCriteria.getCategory() != null && foodCriteria.getCategory().isPresent()) {
            Specification<Food> currentSpec = FoodSpecification.categoryEqual(foodCriteria.getCategory().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (foodCriteria.getStatus() != null && foodCriteria.getStatus().isPresent()) {
            Specification<Food> currentSpec = FoodSpecification
                    .statusEqual(foodCriteria.getStatus().get().equals("Đang bán") ? true : false);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        return this.foodRepository.findAll(combinedSpec, pageable);
    }

    public Page<Food> getAll(Pageable pageable, ProductCriteria productCriteria) {
        //
        if (productCriteria.getName() == null && productCriteria.getSort() == null
                && productCriteria.getCategory() == null && productCriteria.getStatus() == null) {
            return this.foodRepository.findAll(pageable);
        }

        //
        Specification<Food> combinedSpec = Specification.where(null);
        if (productCriteria.getName() != null && productCriteria.getName().isPresent()) {
            Specification<Food> currentSpec = ProductSpecification.nameLike(productCriteria.getName().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (productCriteria.getCategory() != null && productCriteria.getCategory().isPresent()) {
            String[] listCategory = (productCriteria.getCategory().get()).split(",");
            Specification<Food> categorySpecs = Specification.where(null);
            for(int i = 0; i < listCategory.length; i++) {
                Specification<Food> currentSpec = ProductSpecification.categoryEqual(listCategory[i]);
                categorySpecs = categorySpecs.or(currentSpec);
            }
            combinedSpec = combinedSpec.and(categorySpecs);
        }
        if (productCriteria.getStatus() != null && productCriteria.getStatus().isPresent()) {
            Specification<Food> currentSpec = ProductSpecification
                    .statusEqual(productCriteria.getStatus().get().equals("Còn hàng") ? true : false);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        return this.foodRepository.findAll(combinedSpec, pageable);
    }

    public Page<Food> getAll(Pageable pageable) {
        return this.foodRepository.findAll(pageable);
    }

    @Transactional

    public Food upsert(Food Food) {
        return this.foodRepository.save(Food);
    }

    public void delete(Long id) {
        this.foodRepository.deleteById(id);
    }

    public void lock(Food Food) {
        this.foodRepository.save(Food);
    }

    public void addToCart(Food food, User user, Long quantity) {
        Cart cart = user.getCart();
        if(cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setTotalPrice(food.getPrice() * quantity);      
            cart = this.cartRepository.save(newCart);

            CartDetails newCartDetails = new CartDetails();
            newCartDetails.setId(new CartDetailsId(cart.getId(), food.getId()));
            newCartDetails.setCart(cart);
            newCartDetails.setFood(food);
            newCartDetails.setPrice(food.getPrice());
            newCartDetails.setQuantity(quantity);
            this.cartDetailsRepository.save(newCartDetails);
        } else {
            cart.setTotalPrice(cart.getTotalPrice() + (food.getPrice() * quantity));
            this.cartRepository.save(cart);

            CartDetails cartDetails = this.cartDetailsRepository.findByCartAndFood(cart, food);
            if(cartDetails != null) {
                cartDetails.setQuantity(cartDetails.getQuantity() + quantity);
                this.cartDetailsRepository.save(cartDetails);
            } else {
                CartDetails newCartDetails = new CartDetails();
                newCartDetails.setId(new CartDetailsId(cart.getId(), food.getId()));
                newCartDetails.setCart(cart);
                newCartDetails.setFood(food);
                newCartDetails.setPrice(food.getPrice());
                newCartDetails.setQuantity(quantity);
                this.cartDetailsRepository.save(newCartDetails);
            }
        }
    }
}
