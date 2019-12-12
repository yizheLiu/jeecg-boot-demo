package org.jeecg.modules.demo.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Data
@TableName("bc_patient_level")
public class BcPatientLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**等级编号*/
    private java.lang.Integer levelId;
	/**等级名称*/
    private java.lang.String levelname;
	/**等级限制*/
    private java.math.BigDecimal levellimit;
	/**分销等级*/
    private java.lang.String distributionlevel;
	/**分享限制*/
    private java.lang.Integer sharelimit;
	/**是否当期消费*/
    private transient java.lang.String iscurrentconsumptionString;

    private byte[] iscurrentconsumption;

    public byte[] getIscurrentconsumption(){
        if(iscurrentconsumptionString==null){
            return null;
        }
        try {
            return iscurrentconsumptionString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getIscurrentconsumptionString(){
        if(iscurrentconsumption==null || iscurrentconsumption.length==0){
            return "";
        }
        try {
            return new String(iscurrentconsumption,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**生日特权*/
    private java.lang.String birthdaydesc;
	/**专车特权*/
    private java.lang.String cardesc;
	/**项目特权*/
    private java.lang.String projectdesc;
	/**分享特权*/
    private java.lang.String sharedesc;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date modifytime;
	/**创建人*/
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date createTime;
	/**更新人*/
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date updateTime;
	/**所属部门*/
    private java.lang.String sysOrgCode;
}
