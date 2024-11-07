package tech.davidmateus.storeApi.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private Long productId;
    @Column(name = "product_name", length = 60, nullable = false)
    private String productName;
    @Column(name = "product_price", length = 60, nullable = false)
    private Double productPrice;
    @Column(name = "product_status", length = 60, nullable = false)
    private String productStatus;

    //Varios produtos pode ser atributo a uma categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = false,updatable = false)
    private Category category;

    //construtor padrao
    public Product() {}

    public Product(Long productId, String productName, Double productPrice, String productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStatus = productStatus;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(productPrice, product.productPrice) && Objects.equals(productStatus, product.productStatus) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, productStatus, category);
    }
}
