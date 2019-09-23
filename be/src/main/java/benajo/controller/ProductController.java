package benajo.controller;

import benajo.dto.ProductDto;
import benajo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * @return Return a list of all products.
     */
    @RequestMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    /**
     * Get product details for an Amazon product via the ASIN.
     *
     * @param asin Amazon product ASIN
     * @return The fetched product.
     */
    @RequestMapping("fetch-product")
    public ProductDto fetchProduct(@RequestParam(value = "asin") String asin) throws Exception {
        return productService.fetchProduct(asin);
    }
}
