package com.dayton.changgou.service;

import pojo.Brand;

import java.util.List;

/**
 * @author Martin Deng
 * @since 2020-09-16 21:45
 */
public interface BrandService {

	/**
	 * 查找所有品牌
	 * @return java.util.List<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/16 22:05
	 */
	List<Brand> findAll();
	
	/**
	 * 根据ID查询品牌
	 * @param  id id
	 * @return pojo.Brand
	 * @author Martin Deng
	 * @since 2020/9/16 22:17
	 */
	Brand findById(Integer id);

	/**
	 * 增加品牌
	 * @param record	 品牌
	 * @return int  成功添加的数量
	 * @author Martin Deng
	 * @since 2020/9/16 22:28
	 */
	int add(Brand record);

	/**
	 * 修改品牌
	 * @param record	品牌
	 * @return int      修改成功的数量
	 * @author Martin Deng
	 * @since 2020/9/16 22:42
	 */
	int update(Brand record);

	/**
	 * 根据ID删除品牌
	 * @param id    ID
	 * @return int  删除成功的数量
	 * @author Martin Deng
	 * @since 2020/9/16 22:50
	 */
	int deleteById(Integer id);

	/**
	 * 条件查询
	 * @param brand	 条件对象
	 * @return java.util.List<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/16 22:58
	 */
	List<Brand> findList(Brand brand);

}
