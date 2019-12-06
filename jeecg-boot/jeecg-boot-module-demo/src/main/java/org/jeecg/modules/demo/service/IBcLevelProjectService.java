package org.jeecg.modules.demo.service;

import org.jeecg.modules.demo.entity.BcLevelProject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 等级特权项目
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface IBcLevelProjectService extends IService<BcLevelProject> {

	public List<BcLevelProject> selectByMainId(String mainId);
}
