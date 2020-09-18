package com.dayton.changgou.controller;

import com.dayton.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Brand;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author Martin Deng
 * @since 2020-09-16 21:48
 */
@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

	@Resource
	private BrandService brandService;

	/**
	 * 查询所有品牌集合
	 * @return entity.Result<java.util.List<pojo.Brand>>
	 * @author Martin Deng
	 * @since 2020/9/16 22:13
	 */
	@GetMapping("/findAll")
	public Result<List<Brand>> findAll(){
		List<Brand> brands = brandService.findAll();
		return new Result<>(true, StatusCode.OK, "查询品牌集合成功!",
				brands);
	}

	/**
	 * 根据ID查询
	 * @param id	id
	 * @return entity.Result<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/16 22:20
	 */
	@GetMapping("/{id}")
	public Result<Brand> findById(@PathVariable(value = "id") Integer id){
		Brand brand = brandService.findById(id);
		return new Result<>(true, StatusCode.OK, "根据ID查询品牌成功!",
				brand);
	}

	/**
	 * 增加品牌
	 * @param record	品牌
	 * @return entity.Result
	 * @author Martin Deng
	 * @since 2020/9/16 22:38
	 */
	@PutMapping
	public Result add(@RequestBody Brand record){
		int num = brandService.add(record);
		return new Result<>(true, StatusCode.OK, "增加品牌成功!",
				num);
	}

	/**
	 * 根据ID修改品牌
	 * @param id        品牌ID
	 * @param record    品牌
	 * @return entity.Result
	 * @author Martin Deng
	 * @since 2020/9/16 22:46
	 */
	@PutMapping("/{id}")
	public Result update(@PathVariable(value = "id") Integer id,
	                     @RequestBody Brand record){
		record.setId(id);
		int num = brandService.update(record);
		return new Result<>(true, StatusCode.OK, "品牌修改成功!",
				num);
	}

	/**
	 * 根据ID删除品牌
	 * @param id	品牌ID
	 * @return entity.Result
	 * @author Martin Deng
	 * @since 2020/9/16 22:51
	 */
	@DeleteMapping("/{id}")
	public Result deleteById(@PathVariable(value = "id") Integer id){
		int num = brandService.deleteById(id);
		return new Result<>(true, StatusCode.OK, "品牌删除成功!",
				num);
	}

	/**
	 *
	 * 条件搜索查询
	 * @param brand	 条件对象
	 * @return entity.Result<java.util.List<pojo.Brand>>
	 * @author Martin Deng
	 * @since 2020/9/16 23:04
	 */
	@PostMapping("/search")
	public Result<List<Brand>> findList(@RequestBody Brand brand){
		List<Brand> brands = brandService.findList(brand);
		return new Result<>(true, StatusCode.OK, "品牌查询成功!",
				brands);
	}

	/**
	 * 分页查询
	 * @param page	页码
	 * @param size  页数
	 * @return entity.Result<com.github.pagehelper.PageInfo<pojo.Brand>>
	 * @author Martin Deng
	 * @since 2020/9/18 22:08
	 */
	@GetMapping("/search/{page}/{size}")
	public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,
	                                        @PathVariable(value = "size") Integer size){
		PageInfo<Brand> pageInfo = brandService.findPage(page, size);
		return new Result<>(true, StatusCode.OK, "分页查询成功!", pageInfo);
	}

	/**
	 * 条件分页查询实现
	 * @param brand	    前端查询的对象
	 * @param page	    页码
	 * @param size      页数
	 * @return entity.Result<com.github.pagehelper.PageInfo<pojo.Brand>>
	 * @author Martin Deng
	 * @since 2020/9/18 22:10
	 */
	@PostMapping("/search/")
	public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand,
											@PathVariable(value = "page") Integer page,
	                                        @PathVariable(value = "size") Integer size){
		PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
		return new Result<>(true, StatusCode.OK, "条件分页查询成功!", pageInfo);
	}

}
