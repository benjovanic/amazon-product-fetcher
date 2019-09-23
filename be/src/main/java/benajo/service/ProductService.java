package benajo.service;

import benajo.dto.ProductDto;
import benajo.entity.ProductEntity;
import benajo.helper.ProductHelper;
import benajo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductHelper productHelper;
    private final ProductRepository productRepository;

    /**
     * @return A list of all products from the DB.
     */
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapProductDtoToEntity)
                .collect(Collectors.toList());
    }

    /**
     * Get the Amazon product data using the ProductHelper class, then save it in the DB.
     *
     * @param asin The Amazon product ASIN.
     * @return The fetched product.
     */
    public ProductDto fetchProduct(String asin) throws IOException {
        Document document = productHelper.getDocument(asin);
        String name = productHelper.getProductName(document);
        String dimensions = productHelper.getProductDimensions(document);
        String category = productHelper.getProductCategory(document);
        String rank = productHelper.getProductRank(document);

        productRepository.save(ProductEntity.builder()
                .id(asin)
                .category(category)
                .dimensions(dimensions)
                .rank(rank)
                .name(name)
                .build());

        return ProductDto.builder()
                .id(asin)
                .category(category)
                .dimensions(dimensions)
                .rank(rank)
                .name(name)
                .build();
    }

    /**
     * @param productEntity Entity object to be mapped.
     * @return A ProductDto object mapped from a ProductEntity.
     */
    private ProductDto mapProductDtoToEntity(ProductEntity productEntity) {
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .category(productEntity.getCategory())
                .rank(productEntity.getRank())
                .dimensions(productEntity.getDimensions())
                .build();
    }
}
