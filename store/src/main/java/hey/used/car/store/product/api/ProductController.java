package hey.used.car.store.product.api;

import hey.used.car.store.product.api.dto.AddProductRequest;
import hey.used.car.store.product.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @PostMapping("/products")
    public ResponseEntity<Void> test(@RequestBody AddProductRequest request) {
        service.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
