package pers.tom.docwarehouse.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import pers.tom.docwarehouse.annotation.ApiAuthentication;
import pers.tom.docwarehouse.annotation.PackagingResponse;
import pers.tom.docwarehouse.model.dto.CategoryDto;
import pers.tom.docwarehouse.model.param.CategoryParam;
import pers.tom.docwarehouse.model.query.CategoryQuery;
import pers.tom.docwarehouse.model.supports.BaseResult;
import pers.tom.docwarehouse.model.supports.PageParam;
import pers.tom.docwarehouse.model.supports.PageResult;
import pers.tom.docwarehouse.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author tom
 * @description 分类controller
 * @date 2021/2/3 22:16
 */
@RestController
@RequestMapping("/api/categories")
@Api(tags = "分类接口")
@ApiAuthentication
@PackagingResponse
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ApiOperation("创建分类")
    public BaseResult<Long> createCategory(@RequestBody @Valid CategoryParam param){

        return BaseResult.ok(categoryService.createCategory(param).getCategoryId());
    }

    @GetMapping("/listBy")
    @ApiOperation("条件查询分类")
    public List<CategoryDto> listBy(CategoryQuery categoryQuery){

        return categoryService.listBy(categoryQuery);
    }

    @GetMapping("/pageBy")
    @ApiOperation("分页查询分类")
    public PageResult<CategoryDto> pageBy(CategoryQuery categoryQuery,
                                          @Valid PageParam pageParam){

        return categoryService.pageBy(categoryQuery, pageParam);
    }

}