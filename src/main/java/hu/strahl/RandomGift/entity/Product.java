package hu.strahl.RandomGift.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

// @Entity
@Getter @Setter @NoArgsConstructor
public class Product implements Serializable {
    private String productName;
    private String price;
    private String imgSrc;
    private String productLink;
    private String pageTitle;

    public Product(String productName, String price, String imgSrc, String productLink, String pageTitle) {
        this.productName = productName;
        this.price = price;
        this.imgSrc = imgSrc;
        this.productLink = productLink;
        this.pageTitle = pageTitle;
    }

}