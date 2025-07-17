package com.ecommerce.product_service.Service;

import com.ecommerce.product_service.Dto.ProductDTO;
import com.ecommerce.product_service.Entity.Product;
import com.ecommerce.product_service.Mapper.ProductMapper;
import com.ecommerce.product_service.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private ProductMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        return mapper.toDto(productRepository.save(product));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapper.toDto(product);
    }


    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        mapper.updateProductFromDto(productDTO, product);
        Product updatedProduct = productRepository.save(product);
        return mapper.toDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
