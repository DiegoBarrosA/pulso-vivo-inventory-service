package one.expressdev.pulso_vivo_inventory_service.controller;

import java.util.List;
import one.expressdev.pulso_vivo_inventory_service.dto.InventoryUpdateRequest;
import one.expressdev.pulso_vivo_inventory_service.dto.ProductDTO;
import one.expressdev.pulso_vivo_inventory_service.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        try {
            logger.info("Getting all products");
            List<ProductDTO> products = productService.getAllProducts();
            logger.info("Found {} products", products.size());
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Error getting all products", e);
            throw e;
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            logger.info("Getting product by id: {}", id);
            ProductDTO product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("Error getting product by id: {}", id, e);
            throw e;
        }
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(
        @RequestBody ProductDTO dto
    ) {
        try {
            logger.info("Creating product: {}", dto.getName());
            ProductDTO product = productService.createProduct(dto);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("Error creating product", e);
            throw e;
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable Long id,
        @RequestBody ProductDTO dto
    ) {
        try {
            logger.info("Updating product with id: {}", id);
            ProductDTO product = productService.updateProduct(id, dto);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            logger.error("Error updating product with id: {}", id, e);
            throw e;
        }
    }

    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<Void> updateStock(
        @PathVariable Long id,
        @RequestBody InventoryUpdateRequest request
    ) {
        try {
            logger.info("Updating stock for product id: {}", id);
            productService.updateStock(id, request);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error updating stock for product id: {}", id, e);
            throw e;
        }
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductDTO>> getLowStockProducts() {
        try {
            logger.info("Getting low stock products");
            List<ProductDTO> products = productService.getLowStockProducts();
            logger.info("Found {} low stock products", products.size());
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Error getting low stock products", e);
            throw e;
        }
    }


}
