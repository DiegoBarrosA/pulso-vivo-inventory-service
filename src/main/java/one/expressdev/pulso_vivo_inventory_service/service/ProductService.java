package one.expressdev.pulso_vivo_inventory_service.service;

import java.util.List;
import java.util.stream.Collectors;
import one.expressdev.pulso_vivo_inventory_service.dto.InventoryUpdateRequest;
import one.expressdev.pulso_vivo_inventory_service.dto.ProductDTO;
import one.expressdev.pulso_vivo_inventory_service.model.Product;
import one.expressdev.pulso_vivo_inventory_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository
            .findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id).map(this::toDTO).orElse(null);
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    // You can add this method after your createProduct method

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        // Find the existing product or throw an error if it's not found
        Product productToUpdate = productRepository
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException("Product not found with id: " + id)
            );

        // Update the product's fields from the DTO
        productToUpdate.setName(dto.getName());

        productToUpdate.setDescription(dto.getDescription());
        productToUpdate.setCategory(dto.getCategory());
        productToUpdate.setActive(dto.isActive());
        // Note: Quantity is managed by updateStock, so we don't update it here unless required.

        // Save the updated product to the database
        Product savedProduct = productRepository.save(productToUpdate);

        // Return the updated product as a DTO
        return toDTO(savedProduct);
    }

    public void updateStock(Long id, InventoryUpdateRequest request) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setQuantity(
            product.getQuantity() + request.getQuantityChanged()
        );
        productRepository.save(product);
    }

    public List<ProductDTO> getLowStockProducts() {
        return productRepository
            .findByQuantityLessThan(10)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    private ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setQuantity(p.getQuantity());
        dto.setCategory(p.getCategory());
        dto.setActive(p.isActive());
        return dto;
    }

    private Product toEntity(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setQuantity(dto.getQuantity());
        p.setCategory(dto.getCategory());
        p.setActive(dto.isActive());
        return p;
    }
}
