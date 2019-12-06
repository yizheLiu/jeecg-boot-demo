package org.jeecg.modules.demo.service.impl;

import org.jeecg.modules.demo.entity.BcLevelProject;
import org.jeecg.modules.demo.mapper.BcLevelProjectMapper;
import org.jeecg.modules.demo.service.IBcLevelProjectService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 等级特权项目
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class BcLevelProjectServiceImpl extends ServiceImpl<BcLevelProjectMapper, BcLevelProject> implements IBcLevelProjectService {
	
	@Autowired
	private BcLevelProjectMapper bcLevelProjectMapper;
	
	@Override
	public List<BcLevelProject> selectByMainId(String mainId) {
		return bcLevelProjectMapper.selectByMainId(mainId);
	}
}
