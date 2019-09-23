package benajo.controller;

import benajo.dto.ProductDto;
import benajo.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductsShouldReturnProductsList() throws Exception {

        ProductDto product = ProductDto.builder()
                .id("123")
                .build();
        ProductDto product2 = ProductDto.builder()
                .id("456")
                .build();
        ProductDto product3 = ProductDto.builder()
                .id("789")
                .build();

        when(productService.getProducts()).thenReturn(Arrays.asList(product, product2, product3));

        String expected = "[{\"id\":\"123\",\"name\":null,\"dimensions\":null,\"category\":null,\"rank\":null},{\"id\":\"456\",\"name\":null,\"dimensions\":null,\"category\":null,\"rank\":null},{\"id\":\"789\",\"name\":null,\"dimensions\":null,\"category\":null,\"rank\":null}]";

        this.mockMvc
                .perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void fetchProduct() throws Exception {

        ProductDto product = ProductDto.builder()
                .id("123")
                .build();

        when(productService.fetchProduct("123")).thenReturn(product);

        String expected = "{\"id\":\"123\",\"name\":null,\"dimensions\":null,\"category\":null,\"rank\":null}";

        this.mockMvc
                .perform(get("/products/fetch-product").param("asin", "123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}
