package com.ecommerce.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.converter.product.ProductRequestConverter;
import com.ecommerce.converter.product.ProductResponseConverter;
import com.ecommerce.converter.specs.ProductSpecification;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.ProductCategory;
import com.ecommerce.model.request.ProductRequest;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductResponseConverter productResponseConverter;
	private final ProductRepository productRepository;
	private final ProductSpecification productSpecs;
	private final ProductRequestConverter productRequestConverter;
	private final CategoryRepository categoryRepository;
	
	@Override
	public Product saveProduct(ProductRequest productRequest) {
		
		Product product = productRequestConverter.apply(productRequest);
		ProductCategory productCategory = categoryRepository.findByName(productRequest.getCategoryName())
				.orElseThrow(()->new BadRequestException("Category not found"));
		product.setProductCategory(productCategory);
		
		productRepository.save(product);
		return product;
	}

	@Override
	public List<ProductResponse> getAll() {
		List<Product> list = productRepository.findAll();
		return list.stream()
				.map((p)->productResponseConverter.apply(p))
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponse get(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new BadRequestException("Product not found"));
		return productResponseConverter
				.apply(product);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id)
				.orElseThrow(()-> new BadRequestException("Product not found"));
	}

	@Override
	public Page<Product> findPaginated(int page, int pageSize, String sortField, String sortDirection,String categoryName) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		PageRequest pageRequest;
        if (Objects.nonNull(sortDirection)) {
            Sort sortRequest = sort;
            if (Objects.isNull(sortRequest)) {
                throw new BadRequestException("Invalid sort parameter");
            }
            pageRequest = PageRequest.of(page -1, pageSize, sortRequest);
        } else {
            pageRequest = PageRequest.of(page -1, pageSize);
        }
        
        @SuppressWarnings("static-access")
		Specification<Product> combinations=
                Objects.requireNonNull(Specification.where(productSpecs.withCategory(categoryName)))
                        ;

      //  Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by("onSalePrice").ascending());
       return productRepository.findAll(combinations, pageRequest);
	}
	
	private Sort getSort(String sort) {
        switch (sort) {
            case "lowest":
                return Sort.by(Sort.Direction.ASC, "onSalePrice");
            case "highest":
                return Sort.by(Sort.Direction.DESC, "onSalePrice");
            default:
                return null;
        }
    }

	@Override
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductResponse> findAllByOnSale() {
		return productRepository.findAllWithOnSale()
				.stream()
				.map((product)->productResponseConverter.apply(product))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> findAllByHotSale() {
		return productRepository.findAllWithHotSale()
				.stream()
				.map((product)->productResponseConverter.apply(product))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductResponse> findAllByCategory(String name) {
		return productRepository.findAllByCategoryName(name)
				.stream()
				.map((product)->productResponseConverter.apply(product))
				.collect(Collectors.toList());
	}

	
	
}
