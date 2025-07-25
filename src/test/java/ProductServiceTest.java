import com.ecommerce.product_service.Dto.ProductDTO;
import com.ecommerce.product_service.Entity.Product;
import com.ecommerce.product_service.Mapper.ProductMapper;
import com.ecommerce.product_service.Repository.ProductRepository;
import com.ecommerce.product_service.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    private Product getSampleProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("A sample product");
        product.setPrice(99.99);
        product.setCategory("Electronics");
        product.setBrand("BrandX");
        product.setAvailableQuantity(10);
        product.setStatus("ACTIVE");
        return product;
    }

    private ProductDTO getSampleProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setId(1L);
        dto.setName("Test Product");
        dto.setDescription("A sample product");
        dto.setPrice(99.99);
        dto.setCategory("Electronics");
        dto.setBrand("BrandX");
        dto.setAvailableQuantity(10);
        dto.setStatus("ACTIVE");
        return dto;
    }

    @Test
    void testGetProductById() {
        Product product = getSampleProduct();
        ProductDTO dto = getSampleProductDTO();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(dto);

        ProductDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test Product", result.getName());
        assertEquals("A sample product", result.getDescription());
        assertEquals(99.99, result.getPrice());
        assertEquals("Electronics", result.getCategory());
        assertEquals("BrandX", result.getBrand());
        assertEquals(10, result.getAvailableQuantity());
        assertEquals("ACTIVE", result.getStatus());
        verify(productRepository).findById(1L);
    }
}