package org.jeecg.modules.demo.service;

import org.jeecg.modules.demo.entity.BcLevelCoupon;
import org.jeecg.modules.demo.entity.BcLevelProject;
import org.jeecg.modules.demo.entity.BcPatientLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface IBcPatientLevelService extends IService<BcPatientLevel> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(BcPatientLevel bcPatientLevel,List<BcLevelCoupon> bcLevelCouponList,List<BcLevelProject> bcLevelProjectList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BcPatientLevel bcPatientLevel,List<BcLevelCoupon> bcLevelCouponList,List<BcLevelProject> bcLevelProjectList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
