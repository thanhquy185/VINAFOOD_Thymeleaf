package vn.tuhoc.foodshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.Category;
import vn.tuhoc.foodshop.domain.criteria.CategoryCriteria;
import vn.tuhoc.foodshop.repository.CategoryRepository;
import vn.tuhoc.foodshop.service.specfication.CategorySpecification;

@Service
public class CategoryService {
    // Properties
    private final CategoryRepository categoryRepository;

    // Constructors
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Methods
    public Category getOneById(Long id) {
        return this.categoryRepository.findOneById(id);
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Page<Category> getAll(Pageable pageable, CategoryCriteria categoryCriteria) {
        //
        if (categoryCriteria.getId() == null && categoryCriteria.getName() == null && categoryCriteria.getSort() == null
                && categoryCriteria.getStatus() == null) {
            return this.categoryRepository.findAll(pageable);
        }

        //
        Specification<Category> combinedSpec = Specification.where(null);
        if (categoryCriteria.getId() != null && categoryCriteria.getId().isPresent()) {
            if (categoryCriteria.getId().get().matches("\\d+")) {
                Specification<Category> currentSpec = CategorySpecification.idEqual(categoryCriteria.getId().get());
                combinedSpec = combinedSpec.or(currentSpec);
            }
        }
        if (categoryCriteria.getName() != null && categoryCriteria.getName().isPresent()) {
            Specification<Category> currentSpec = CategorySpecification.nameLike(categoryCriteria.getName().get());
            combinedSpec = combinedSpec.or(currentSpec);
        }
        if (categoryCriteria.getStatus() != null && categoryCriteria.getStatus().isPresent()) {
            Specification<Category> currentSpec = CategorySpecification
                    .statusEqual(categoryCriteria.getStatus().get().equals("Hoạt động") ? true : false);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        return this.categoryRepository.findAll(combinedSpec, pageable);
    }

    public Page<Category> getAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    public Category upsert(Category category) {
        return this.categoryRepository.save(category);
    }

    public void delete(Long id) {
        this.categoryRepository.deleteById(id);
    }

    public void lock(Category category) {
        this.categoryRepository.save(category);
    }
}
