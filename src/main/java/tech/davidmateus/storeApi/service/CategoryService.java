package tech.davidmateus.storeApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.davidmateus.storeApi.controllers.CategoryController;
import tech.davidmateus.storeApi.dtos.CategoryDTO;
import tech.davidmateus.storeApi.exceptions.ResourceNotFoundException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import tech.davidmateus.storeApi.mapper.DozerMapper;
import tech.davidmateus.storeApi.model.Category;
import tech.davidmateus.storeApi.repository.CategoryRepository;

import java.util.List;
//Regras do Negocio
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryDTO> getAllCategory(){
        List<CategoryDTO> categoryDTOS = DozerMapper.parseListObject(categoryRepository.findAll(), CategoryDTO.class);
        categoryDTOS.forEach(category ->{
            category.add(linkTo(methodOn(CategoryController.class).getCategoryById(category.getCategoryId())).withSelfRel());
        });
        return  categoryDTOS;
    }

    @Transactional
    public CategoryDTO getCategoryById(Long categoryId){
        var entity = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("not records found for this ID"));
        CategoryDTO categoryDTO = DozerMapper.parseObject(entity, CategoryDTO.class);
        categoryDTO.add(linkTo(methodOn(CategoryController.class).getCategoryById(categoryId)).withSelfRel());
        return categoryDTO;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO){

        var entity = DozerMapper.parseObject(categoryDTO, Category.class);

        var dto =  DozerMapper.parseObject(categoryRepository.save(entity), CategoryDTO.class);
        dto.add(linkTo(methodOn(CategoryController.class).getCategoryById(dto.getCategoryId())).withSelfRel());
        return dto;
    }
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO updateCategory){
        return categoryRepository.findById(categoryId)
                .map(category -> {
                    category.setCategoryName(updateCategory.getCategoryName());
                    category.setCategoryDescription(updateCategory.getCategoryDescription());
                    category.setCategoryStatus(updateCategory.getCategoryStatus());

                    var dto =DozerMapper.parseObject(categoryRepository.save(category), CategoryDTO.class);
                    dto.add(linkTo(methodOn(CategoryController.class).getCategoryById(dto.getCategoryId())).withSelfRel());
                    return dto;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
    public void deleteCategory(Long categoryId){
        var entity = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category nao found1"));
        categoryRepository.delete(entity);
    }
}
