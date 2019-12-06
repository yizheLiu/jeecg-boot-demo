package org.jeecg.modules.demo.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 等级特权项目
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Data
@TableName("bc_level_project")
public class BcLevelProject implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	private java.lang.String id;
	/**levelid*/
	private java.lang.String levelid;
	/**特权名称*/
	@Excel(name = "特权名称", width = 15)
	private java.lang.String privilegename;
	/**特权描述*/
	@Excel(name = "特权描述", width = 15)
	private java.lang.String privilegedesc;
	/**特权类型*/
	@Excel(name = "特权类型", width = 15)
	private java.lang.String type;
	/**记录状态*/
	@Excel(name = "记录状态", width = 15)
	private java.lang.String recordstate;
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
}
