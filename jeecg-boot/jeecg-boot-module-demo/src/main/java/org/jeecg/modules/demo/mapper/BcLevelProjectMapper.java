package org.jeecg.modules.demo.mapper;

import java.util.List;
import org.jeecg.modules.demo.entity.BcLevelProject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 等级特权项目
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface BcLevelProjectMapper extends BaseMapper<BcLevelProject> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BcLevelProject> selectByMainId(@Param("mainId") String mainId);
}
