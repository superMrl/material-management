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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 出库表
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_out_storage_info")
@ApiModel(value="OutStorageInfo对象", description="出库表")
public class OutStorageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "主键(出库单号 CKD+时间戳截)")
    @TableField("OUT_PAGE_NO")
    private String outPageNo;

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

    @ApiModelProperty(value = "出库价格")
    @TableField("OUT_PRICE")
    private BigDecimal outPrice;

    @ApiModelProperty(value = "出库数量")
    @TableField("OUT_AMOUNT")
    private BigDecimal outAmount;

    @ApiModelProperty(value = "出库时间")
    @TableField("OUT_TIME")
    private String outTime;

    @ApiModelProperty(value = "记录减少库存的入库单号")
    @TableField("RESOURSE_NO")
    private String resourseNo;

    @ApiModelProperty(value = "商户id")
    @TableField("COMPANY_ID")
    private Long companyId;

    @ApiModelProperty(value = "出库人名称")
    @TableField("USER_NAME")
    private String userName;


}
