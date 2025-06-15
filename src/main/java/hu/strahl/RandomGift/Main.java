package hu.strahl.RandomGift;

import hu.strahl.RandomGift.util.CrawlerUtil;
import hu.strahl.RandomGift.util.DOMParserUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		//String itemUrl = CrawlerUtil.findRandomEbayItem();
		//System.out.println(itemUrl);
		SpringApplication.run(Main.class, args);
	}

}
