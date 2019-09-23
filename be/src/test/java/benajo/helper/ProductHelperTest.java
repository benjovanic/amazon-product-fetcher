package benajo.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ProductHelperTest {

    private static final String FILE_PREFIX = "src/test/resources/product-helper-files/";

    private ProductHelper classUnderTest = new ProductHelper();

    @Test
    public void getProductName() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithTitle.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "Spider-Man";
        String actual = classUnderTest.getProductName(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductDimensionsNotFound() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentEmpty.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "NOT_FOUND";
        String actual = classUnderTest.getProductDimensions(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductDimensions1() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithDimensions1.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "1x2x3 cm";
        String actual = classUnderTest.getProductDimensions(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductDimensions2() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithDimensions2.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "4x5x6 cm";
        String actual = classUnderTest.getProductDimensions(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductDimensions3() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithDimensions3.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "7x8x9 cm";
        String actual = classUnderTest.getProductDimensions(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductCategory() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithCategory.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "Video Games";
        String actual = classUnderTest.getProductCategory(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductRankNotFound() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentEmpty.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "NOT_FOUND";
        String actual = classUnderTest.getProductRank(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductRank1() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithRank1.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "111";
        String actual = classUnderTest.getProductRank(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductRank2() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithRank2.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "222";
        String actual = classUnderTest.getProductRank(document);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductRank3() throws IOException {
        File input = new File(FILE_PREFIX + "DocumentWithRank3.html");
        Document document = Jsoup.parse(input, "UTF-8", "");

        String expected = "333";
        String actual = classUnderTest.getProductRank(document);

        assertEquals(expected, actual);
    }
}
