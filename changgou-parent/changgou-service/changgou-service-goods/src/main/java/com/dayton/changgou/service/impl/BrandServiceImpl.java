package com.dayton.changgou.service.impl;

import com.dayton.changgou.dao.BrandMapper;
import com.dayton.changgou.service.BrandService;
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
		// 自定义条件搜索对象 Example
		Example example = new Example(Brand.class);
		Example.Criteria criteria = example.createCriteria();
		if (null != brand){
			if (StringUtils.isNotBlank(brand.getName())){
				criteria.andLike("name", "%" + brand.getName() + "%");
			}
			// 首字母搜索
			if (StringUtils.isNotBlank(brand.getLetter())){
				criteria.andEqualTo("letter", brand.getLetter());
			}
		}
		return brandMapper.selectByExample(example);
	}

}
