package tech.davidmateus.storeApi.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.davidmateus.storeApi.dtos.ProductDTO;
import tech.davidmateus.storeApi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@Tag(name = "Product", description = "Endpoints Product")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Finds all products", description = "Finds all products",
            // Iss ajuda entneder como cliente pode interagir com api
            tags = {"Product"},
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation =  ProductDTO.class))
                            )

                    }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Finds a product", description = "Finds a product", tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ProductDTO getProductById(@PathVariable(value = "id") Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    @Operation(summary = "Adds a new product", description = "Adds a new product por passing in a json", tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "update a product", tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO updateProduct){
        return productService.updateProduct(id, updateProduct);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a product", description = "delete a product", tags = {"Product"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
