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
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(
        @RequestBody ProductDTO dto
    ) {
        ProductDTO product = productService.createProduct(dto);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
        @PathVariable Long id,
        @RequestBody ProductDTO dto
    ) {
        ProductDTO product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(product);
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
    public ResponseEntity<List<ProductDTO>> getLowStockProducts() {
        List<ProductDTO> products = productService.getLowStockProducts();
        return ResponseEntity.ok(products);
    }


}
