package pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:Brand构建
 * @Date 2019/6/14 19:13
 *****/
@Data
@FieldNameConstants
@ApiModel(description = "Brand",value = "Brand")
@Table(name="tb_brand")
public class Brand implements Serializable{

	/** 品牌id */
	@ApiModelProperty(value = "品牌id", required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;

	/** 品牌名称 */
	@ApiModelProperty(value = "品牌名称",required = false)
    @Column(name = "name")
	private String name;

	/** 品牌图片地址 */
	@ApiModelProperty(value = "品牌图片地址",required = false)
    @Column(name = "image")
	private String image;

	/** 品牌的首字母 */
	@ApiModelProperty(value = "品牌的首字母",required = false)
    @Column(name = "letter")
	private String letter;

	/** 排序 */
	@ApiModelProperty(value = "排序",required = false)
    @Column(name = "seq")
	private Integer seq;

}
