package tech.davidmateus.storeApi.dtos;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.hateoas.RepresentationModel;
import tech.davidmateus.storeApi.model.Product;

import java.util.Objects;
                                // Adicinando Hateaos
public class ProductDTO extends RepresentationModel<ProductDTO> {

    private Long productId;
    private String productName;
    private Double productPrice;
    private String productStatus;



    private CategoryDTO category;


    public ProductDTO() {}

    public ProductDTO(Long productId, String productName, Double productPrice, String productStatus) {
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
    @JsonBackReference
    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(productId, that.productId) && Objects.equals(productName, that.productName) && Objects.equals(productPrice, that.productPrice) && Objects.equals(productStatus, that.productStatus) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productPrice, productStatus, category);
    }
}
