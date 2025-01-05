package hu.strahl;

import hu.strahl.util.CrawlerUtil;
import hu.strahl.util.DOMParserUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String itemUrl = CrawlerUtil.findRandomEbayItem();
        System.out.println(itemUrl);

        DOMParserUtil.parseEbayItemXML(itemUrl);

    }
}