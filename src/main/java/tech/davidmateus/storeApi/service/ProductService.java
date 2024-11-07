package tech.davidmateus.storeApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.davidmateus.storeApi.controllers.ProductController;
import tech.davidmateus.storeApi.dtos.ProductDTO;
import tech.davidmateus.storeApi.exceptions.ResourceNotFoundException;
import tech.davidmateus.storeApi.mapper.DozerMapper;
import tech.davidmateus.storeApi.model.Product;
import tech.davidmateus.storeApi.repository.ProductRepository;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Regras do Negocio
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    @Transactional
    public List<ProductDTO> getAllProduct(){
        List<ProductDTO> productDTOS = DozerMapper.parseListObject(productRepository.findAll(), ProductDTO.class);
        productDTOS.forEach(category ->{
            category.add(linkTo(methodOn(ProductController.class).getProductById(category.getProductId())).withSelfRel());
        });
        return  productDTOS;
    }

    @Transactional
    public ProductDTO getProductById(Long productId){
        var entity = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("not records found for this ID"));
        ProductDTO productDTO = DozerMapper.parseObject(entity, ProductDTO.class);
        productDTO.add(linkTo(methodOn(ProductController.class).getProductById(productId)).withSelfRel());
        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO){

        var entity = DozerMapper.parseObject(productDTO, Product.class);

        var dto =  DozerMapper.parseObject(productRepository.save(entity), ProductDTO.class);
        dto.add(linkTo(methodOn(ProductController.class).getProductById(dto.getProductId())).withSelfRel());
        return dto;
    }

    public ProductDTO updateProduct(Long productId, ProductDTO updateProduct){
        return productRepository.findById(productId)
                .map(product -> {
                    product.setProductName(updateProduct.getProductName());
                    product.setProductPrice(updateProduct.getProductPrice());
                    product.setProductStatus(updateProduct.getProductStatus());

                    var dto =DozerMapper.parseObject(productRepository.save(product), ProductDTO.class);
                    dto.add(linkTo(methodOn(ProductController.class).getProductById(dto.getProductId())).withSelfRel());
                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    @Transactional
    public void deleteProduct(Long productId){
        var entity = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found1"));
        System.out.println("Deleting product: " + entity.getProductName());
        productRepository.delete(entity);
    }
}
