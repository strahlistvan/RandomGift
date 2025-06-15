package hu.strahl.RandomGift.util;

import hu.strahl.RandomGift.entity.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DOMParserUtil {
    public static Product parseEbayItemXML(String urlString)  {
        Product product = new Product();

        try {
            Document doc = Jsoup.connect(urlString).get();
            System.out.println(doc.title());

            var node = doc.select(".x-item-title__mainTitle").getFirst();
            String productName = node.child(0).text();
            node = doc.select(".x-price-primary").getFirst();
            String price = node.child(0).text();
            node = doc.select(".ux-image-carousel-item").getFirst();
            String imgSrc = node.child(0).attr("src");
            String productLink = urlString.substring(0, urlString.indexOf("?"));

            product = new Product(productName, price, imgSrc, productLink, doc.title());
            System.out.println(product.getProductName());
        }
        catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
        return product;

    }
}
