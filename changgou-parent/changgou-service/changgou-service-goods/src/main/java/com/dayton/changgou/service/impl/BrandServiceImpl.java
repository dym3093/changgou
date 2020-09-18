package com.dayton.changgou.service.impl;

import com.dayton.changgou.dao.BrandMapper;
import com.dayton.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import pojo.Brand;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Martin Deng
 * @since 2020-09-16 21:46
 */
@Service
public class BrandServiceImpl implements BrandService{

	@Resource
	private BrandMapper brandMapper;

	/**
	 * 查找所有
	 * @return java.util.List<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/16 22:05
	 */
	@Override
	public List<Brand> findAll() {
		return brandMapper.selectAll();
	}

	/**
	 * 根据ID查询所有品牌
	 * @param id    id
	 * @return pojo.Brand
	 * @author Martin Deng
	 * @since 2020/9/16 22:18
	 */
	@Override
	public Brand findById(Integer id) {
		return brandMapper.selectByPrimaryKey(id);
	}

	/**
	 * 增加品牌
	 * @param record    品牌
	 * @return void
	 * @author Martin Deng
	 * @since 2020/9/16 22:29
	 */
	@Override
	public int add(Brand record) {
		return brandMapper.insertSelective(record);
	}

	/**
	 * 修改品牌
	 * @param record	品牌
	 * @return int      修改成功的数量
	 * @author Martin Deng
	 * @since 2020/9/16 22:42
	 */
	@Override
	public int update(Brand record) {
		return brandMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据ID删除品牌
	 * @param id    ID
	 * @return int  删除成功的数量
	 * @author Martin Deng
	 * @since 2020/9/16 22:51
	 */
	@Override
	public int deleteById(Integer id) {
		return brandMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 条件查询
	 * @param brand	 条件对象
	 * @return java.util.List<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/16 22:58
	 */
	@Override
	public List<Brand> findList(Brand brand) {
		Example example = createExample(brand);
		return brandMapper.selectByExample(example);
	}

	/**
	 * 生成查询条件
	 * @param brand	    品牌对象
	 * @return tk.mybatis.mapper.entity.Example
	 * @author Martin Deng
	 * @since 2020/9/18 22:03
	 */
	public Example createExample(Brand brand){
		// 自定义条件搜索对象 Example
		Example example = new Example(Brand.class);
		// 条件构造器
		Example.Criteria criteria = example.createCriteria();
		if (null != brand){
			// id 精确搜索
			if (StringUtils.isNotBlank(brand.getImage())){
				criteria.andEqualTo(Brand.Fields.id, brand.getId());
			}
			// 名字模糊搜索
			if (StringUtils.isNotBlank(brand.getName())){
				criteria.andLike(Brand.Fields.name, "%" + brand.getName() + "%");
			}
			// 首字母搜索
			if (StringUtils.isNotBlank(brand.getLetter())){
				criteria.andEqualTo(Brand.Fields.letter, brand.getLetter());
			}
		}
		return example;
	}

	/**
	 * 分页搜索
	 * @param page  页码
	 * @param size  页数
	 * @return com.github.pagehelper.PageInfo<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/18 21:59
	 */
	@Override
	public PageInfo<Brand> findPage(Integer page, Integer size) {
		// 分页
		PageHelper.startPage(page, size);
		List<Brand> list = brandMapper.selectAll();
		return new PageInfo<>(list);
	}

	/**
	 * 条件分页搜索
	 * @param brand	    条件对象
	 * @param page	    页码
	 * @param size	    页数
	 * @return java.util.List<pojo.Brand>
	 * @author Martin Deng
	 * @since 2020/9/18 21:46
	 */
	@Override
	public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
		// 分页
		PageHelper.startPage(page, size);
		// 搜索数据 name不为空，则根据名字模糊搜索，letter不为空，则根据letter搜索
		Example example = createExample(brand);
		List<Brand> list = brandMapper.selectByExample(example);
		// 封装
		return new PageInfo<>(list);
	}

}
