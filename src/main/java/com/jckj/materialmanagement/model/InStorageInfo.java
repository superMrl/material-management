package com.jckj.materialmanagement.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 入库表
 * </p>
 *
 * @author 
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_in_storage_info")
@ApiModel(value="InStorageInfo对象", description="入库表")
public class InStorageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主键(入库单号 RKD+时间戳截)")
    @TableField("IN_PAGE_NO")
    private String inPageNo;

    @ApiModelProperty(value = "物资编码(物资表的主键)")
    @TableField("MATERIAL_SERIAL_NO")
    private String materialSerialNo;

    @ApiModelProperty(value = "物资名称")
    @TableField("MATERIAL_NAME")
    private String materialName;

    @ApiModelProperty(value = "生产厂家")
    @TableField("MANUFACTURE_NAME")
    private String manufactureName;

    @ApiModelProperty(value = "计量单位")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "供应商名称")
    @TableField("SUPPLYER_NAME")
    private String supplyerName;


    @ApiModelProperty(value = "入库价格")
    @TableField("INSTORE_PRICE")
    private BigDecimal instorePrice;

    @ApiModelProperty(value = "入库数量")
    @TableField("IN_AMOUNT")
    private BigDecimal inAmount;

    @ApiModelProperty(value = "入库时间")
    @TableField("INSERT_TIME")
    private String insertTime;

    @ApiModelProperty(value = "商户id")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty(value = "入库用户名称")
    @TableField("USER_NAME")
    private String userName;
}
