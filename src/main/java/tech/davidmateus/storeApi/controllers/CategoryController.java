package tech.davidmateus.storeApi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.davidmateus.storeApi.dtos.CategoryDTO;
import tech.davidmateus.storeApi.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
// tag name do swagger
@Tag(name = "Category", description = "Endpoints Category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Finds all Category", description = "Finds all category",
            tags = {"Category"},
            responses = {@ApiResponse(
                    description = "Success", responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation =  CategoryDTO.class))
                            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Finds a category", description = "Finds a category", tags = {"Category"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public CategoryDTO getCategoryById(@PathVariable(value = "id") Long id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping
    @Operation(summary = "Adds a new category", description = "Adds a new category por passing in a json", tags = {"Category"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a category", description = "update a category", tags = {"Category"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO updateCategory){
        return categoryService.updateCategory(id, updateCategory);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a category", description = "delete a category", tags = {"Category"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),

            })
    public  void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
