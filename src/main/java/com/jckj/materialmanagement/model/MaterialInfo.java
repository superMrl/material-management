package com.jckj.materialmanagement.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 物资表
 * </p>
 *
 * @author 
 * @since 2020-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_material_info")
@ApiModel(value="MaterialInfo对象", description="物资表")
public class MaterialInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("MATERIAL_SERIAL_NO")
    private String materialSerialNo;

    @ApiModelProperty(value = "物资名称")
    @TableField("MATERIAL_NAME")
    private String materialName;

    @ApiModelProperty(value = "品牌")
    @TableField("BRAND")
    private Long brand;

    @ApiModelProperty(value = "物资分类")
    @TableField("CATEGORY")
    private Long category;

    @ApiModelProperty(value = "物资单位")
    @TableField("UNIT")
    private Long unit;

    @ApiModelProperty(value = "单位对应的数量")
    @TableField("UNIT_NUM")
    private Integer unitNum;

    @ApiModelProperty(value = "库位")
    @TableField("LOCATION")
    private Long location;

    @ApiModelProperty(value = "生产厂家")
    @TableField("MANUFACTURE_NAME")
    private String manufactureName;

    @ApiModelProperty(value = "采购价")
    @TableField("PURCHASE_PRICE")
    private BigDecimal purchasePrice;

    @ApiModelProperty(value = "零售价")
    @TableField("RETAIL_PRICE")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "商户id")
    @TableField("COMPANY_ID")
    private Long companyId;


}
