package benajo.service;

import benajo.entity.ProductEntity;
import benajo.helper.ProductHelper;
import benajo.dto.ProductDto;
import benajo.repository.ProductRepository;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductHelper productHelper;
    @Mock
    private ProductRepository productRepository;

    private ProductService classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ProductService(productHelper, productRepository);
    }

    @Test
    public void getProducts() {

        String asin = "123";
        String name = "toy set";
        String category = "toys";
        String rank = "55";
        String dimensions = "2x55x3cm";

        ProductEntity productEntity = ProductEntity.builder()
                .id(asin)
                .name(name)
                .category(category)
                .rank(rank)
                .dimensions(dimensions)
                .build();

        List<ProductEntity> products = new ArrayList<>();
        products.add(productEntity);

        ProductDto productDto = ProductDto.builder()
                .id(asin)
                .name(name)
                .category(category)
                .rank(rank)
                .dimensions(dimensions)
                .build();

        List<ProductDto> expected = new ArrayList<>();
        expected.add(productDto);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDto> actual = classUnderTest.getProducts();

        assertEquals(expected, actual);
    }

    @Test
    public void fetchProduct() throws IOException {
        String asin = "123";
        String name = "toy set";
        String category = "toys";
        String rank = "55";
        String dimensions = "2x55x3cm";

        ProductDto expected = ProductDto.builder()
                .id(asin)
                .name(name)
                .category(category)
                .rank(rank)
                .dimensions(dimensions)
                .build();

        when(productHelper.getDocument(asin)).thenReturn(mock(Document.class));
        when(productHelper.getProductName(any(Document.class))).thenReturn(name);
        when(productHelper.getProductCategory(any(Document.class))).thenReturn(category);
        when(productHelper.getProductRank(any(Document.class))).thenReturn(rank);
        when(productHelper.getProductDimensions(any(Document.class))).thenReturn(dimensions);

        ProductDto actual = classUnderTest.fetchProduct(asin);

        assertEquals(expected, actual);
    }
}
