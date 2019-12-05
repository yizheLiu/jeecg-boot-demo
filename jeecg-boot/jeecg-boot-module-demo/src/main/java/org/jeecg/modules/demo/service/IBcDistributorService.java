package org.jeecg.modules.demo.service;

import org.jeecg.modules.demo.entity.BcDistributor;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.common.exception.JeecgBootException;

/**
 * @Description: 分销员
 * @Author: jeecg-boot
 * @Date:   2019-12-04
 * @Version: V1.0
 */
public interface IBcDistributorService extends IService<BcDistributor> {

	/**根节点父ID的值*/
	public static final String ROOT_PID_VALUE = "0";
	
	/**树节点有子节点状态值*/
	public static final String HASCHILD = "1";
	
	/**树节点无子节点状态值*/
	public static final String NOCHILD = "0";

	/**新增节点*/
	void addBcDistributor(BcDistributor bcDistributor);
	
	/**修改节点*/
	void updateBcDistributor(BcDistributor bcDistributor) throws JeecgBootException;
	
	/**删除节点*/
	void deleteBcDistributor(String id) throws JeecgBootException;

}
