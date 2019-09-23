package benajo.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductHelper {

    private static final String NOT_FOUND = "NOT_FOUND";

    // important to overwrite the default agent when using Jsoup, otherwise Amazon returns different data
    // for the Jsoup scraper
    @Value("${USER_AGENT}")
    private String USER_AGENT;

    @Value("${AMAZON_BASE_URL}")
    private String AMAZON_BASE_URL;

    /**
     * Use the Jsoup library to get the raw HTML for the Amazon product page.
     *
     * @param asin The Product ASIN to attach to the Amazon product page URL.
     * @return A Document object containing the product page data.
     */
    public Document getDocument(String asin) throws IOException {
        return Jsoup.connect(String.format("%s%s", AMAZON_BASE_URL, asin))
                .userAgent(USER_AGENT)
                .maxBodySize(0)
                .get();
    }

    /**
     * @param document The Document to parse.
     * @return The product name from the Document.
     */
    public String getProductName(Document document) {
        return document.select("#productTitle").html();
    }

    /**
     * This method caters for multiple different use cases where the product dimensions are not
     * always in the same place within the page. This method is extensible.
     *
     * @param document The Document to parse.
     * @return The product dimensions from the Document, otherwise the string NOT_FOUND.
     */
    public String getProductDimensions(Document document) {

        List<String> selectors = new ArrayList<>();
        selectors.add("#productDetails_detailBullets_sections1 > tbody > tr:nth-child(6) > td");
        selectors.add("#prodDetails > div.wrapper.USlocale > div.column.col1 > div > div.content.pdClearfix > div > div > table > tbody > tr:nth-child(2) > td.value");
        selectors.add("#detailBullets_feature_div > ul > li:nth-child(1) > span > span:nth-child(2)");

        for (String selector : selectors) {
            String dimensions = document.select(selector).html();
            if (dimensions.length() > 0) {
                return dimensions;
            }
        }

        return NOT_FOUND;
    }

    /**
     * The query retrieves the category from the first anchor tag in the page breadcrumbs.
     *
     * @param document The Document to parse.
     * @return The product category from the Document.
     */
    public String getProductCategory(Document document) {
        return document.select("#wayfinding-breadcrumbs_feature_div > ul > li:nth-child(1) > span > a").html();
    }

    /**
     * This method caters for multiple different use cases where the product rank is not
     * always in the same place within the page. This method is extensible.
     *
     * This method further parses the return from the Document by using regex to pick
     * out the rank integer value.
     *
     * @param document The Document to parse.
     * @return The product rank from the Document, otherwise the string NOT_FOUND.
     */
    public String getProductRank(Document document) {

        Pattern p = Pattern.compile("(.*)#([0-9,]+)(.*)");

        List<String> selectors = new ArrayList<>();
        selectors.add("#SalesRank > td.value");
        selectors.add("#productDetails_detailBullets_sections1 > tbody > tr:nth-child(4) > td > span > span:nth-child(1)");
        selectors.add("#SalesRank");

        for (String selector : selectors) {
            String rank = document.select(selector).html();
            if (rank.length() > 0) {
                Matcher m = p.matcher(rank);
                if (m.find()) {
                    return m.group(2);
                }
            }
        }

        return NOT_FOUND;
    }
}
