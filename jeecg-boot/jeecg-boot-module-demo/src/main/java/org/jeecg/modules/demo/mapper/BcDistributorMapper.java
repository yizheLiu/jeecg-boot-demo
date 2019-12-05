package org.jeecg.modules.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo.entity.BcDistributor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 分销员
 * @Author: jeecg-boot
 * @Date:   2019-12-04
 * @Version: V1.0
 */
public interface BcDistributorMapper extends BaseMapper<BcDistributor> {

	/**
	 * 编辑节点状态
	 * @param id
	 * @param status
	 */
	void updateTreeNodeStatus(@Param("id") String id,@Param("status") String status);

}
