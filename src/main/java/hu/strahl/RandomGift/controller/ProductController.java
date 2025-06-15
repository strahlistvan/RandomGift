package hu.strahl.RandomGift.controller;

import hu.strahl.RandomGift.entity.Product;
import hu.strahl.RandomGift.util.CrawlerUtil;
import hu.strahl.RandomGift.util.DOMParserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/")
    public String index() {
        String itemUrl = CrawlerUtil.findRandomEbayItem();
        Product product = DOMParserUtil.parseEbayItemXML(itemUrl);
        System.out.println(itemUrl);
        return product.getProductName() + " " + product.getPrice();
    }

}
