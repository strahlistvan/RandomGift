package hu.strahl.RandomGift.controller;

import hu.strahl.RandomGift.entity.Product;
import hu.strahl.RandomGift.util.CrawlerUtil;
import hu.strahl.RandomGift.util.DOMParserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping("/")
    public String index(Model model) {
        String itemUrl = CrawlerUtil.findRandomEbayItem();
        Product product = DOMParserUtil.parseEbayItemXML(itemUrl);
        model.addAttribute("giftName", product.getProductName());
        model.addAttribute("giftPrice", product.getPrice());
        model.addAttribute("giftImg", product.getImgSrc());
        return "index.html";
    }


}
