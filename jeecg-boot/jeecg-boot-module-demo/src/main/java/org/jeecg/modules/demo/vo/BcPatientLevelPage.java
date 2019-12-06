package org.jeecg.modules.demo.vo;

import java.util.List;
import org.jeecg.modules.demo.entity.BcPatientLevel;
import org.jeecg.modules.demo.entity.BcLevelCoupon;
import org.jeecg.modules.demo.entity.BcLevelProject;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Data
public class BcPatientLevelPage {
	
	/**id*/
	private java.lang.String id;
	/**等级编号*/
	@Excel(name = "等级编号", width = 15)
	private java.lang.Integer levelId;
	/**等级名称*/
	@Excel(name = "等级名称", width = 15)
	private java.lang.String levelname;
	/**等级限制*/
	@Excel(name = "等级限制", width = 15)
	private java.math.BigDecimal levellimit;
	/**分销等级*/
	@Excel(name = "分销等级", width = 15)
	private java.lang.String distributionlevel;
	/**分享限制*/
	@Excel(name = "分享限制", width = 15)
	private java.lang.Integer sharelimit;
	/**是否当期消费*/
	@Excel(name = "是否当期消费", width = 15)
	private java.lang.String iscurrentconsumption;
	/**生日特权*/
	@Excel(name = "生日特权", width = 15)
	private java.lang.String birthdaydesc;
	/**专车特权*/
	@Excel(name = "专车特权", width = 15)
	private java.lang.String cardesc;
	/**项目特权*/
	@Excel(name = "项目特权", width = 15)
	private java.lang.String projectdesc;
	/**分享特权*/
	@Excel(name = "分享特权", width = 15)
	private java.lang.String sharedesc;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date modifytime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
	private java.lang.String sysOrgCode;
	
	@ExcelCollection(name="等级卡券")
	private List<BcLevelCoupon> bcLevelCouponList;	
	@ExcelCollection(name="等级特权项目")
	private List<BcLevelProject> bcLevelProjectList;	
	
}
