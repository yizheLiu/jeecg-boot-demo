package org.jeecg.modules.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 分销员
 * @Author: jeecg-boot
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Data
@TableName("bc_distributor")
public class BcDistributor implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	/**父级节点*/
	@Excel(name = "父级节点", width = 15)
	private java.lang.String pid;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
	private java.lang.String mobilePhone;
	/**是否有子节点*/
	@Excel(name = "是否有子节点", width = 15)
	private java.lang.String hasChild;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	private java.lang.String distributorName;
	/**是否绑定小程序*/
	@Excel(name = "是否绑定小程序", width = 15)
	private java.lang.String bindingSmallapp;
	/**客户数*/
	@Excel(name = "客户数", width = 15)
	private java.lang.Integer customerCount;
	/**账户余额*/
	@Excel(name = "账户余额", width = 15)
	private java.lang.Double balance;
	/**来源*/
	@Excel(name = "来源", width = 15)
	private java.lang.String distributorFrom;
}
