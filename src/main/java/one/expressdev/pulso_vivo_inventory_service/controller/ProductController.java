package one.expressdev.pulso_vivo_inventory_service.controller;

import java.util.List;
import one.expressdev.pulso_vivo_inventory_service.dto.InventoryUpdateRequest;
import one.expressdev.pulso_vivo_inventory_service.dto.ProductDTO;
import one.expressdev.pulso_vivo_inventory_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(
        @RequestBody ProductDTO dto
    ) {
        return ResponseEntity.ok(productService.createProduct(dto));
    } // <-- This closing brace was moved here

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable Long id,
        @RequestBody ProductDTO dto
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<Void> updateStock(
        @PathVariable Long id,
        @RequestBody InventoryUpdateRequest request
    ) {
        productService.updateStock(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/low-stock")
    public List<ProductDTO> getLowStockProducts() {
        return productService.getLowStockProducts();
    }
}
