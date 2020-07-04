package com.jckj.materialmanagement.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author 
 * @since 2020-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_dict")
@ApiModel(value="Dict对象", description="字典表")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "类型(分类:CATEGORY   计量单位:UNIT)")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "商户id")
    @TableField("COMPANY_ID")
    private Long companyId;


}
