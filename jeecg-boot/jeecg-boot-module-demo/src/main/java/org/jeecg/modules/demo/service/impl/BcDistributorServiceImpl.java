package org.jeecg.modules.demo.service.impl;

import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.entity.BcDistributor;
import org.jeecg.modules.demo.mapper.BcDistributorMapper;
import org.jeecg.modules.demo.service.IBcDistributorService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 分销员
 * @Author: jeecg-boot
 * @Date:   2019-12-04
 * @Version: V1.0
 */
@Service
public class BcDistributorServiceImpl extends ServiceImpl<BcDistributorMapper, BcDistributor> implements IBcDistributorService {

	@Override
	public void addBcDistributor(BcDistributor bcDistributor) {
		if(oConvertUtils.isEmpty(bcDistributor.getPid())){
			bcDistributor.setPid(IBcDistributorService.ROOT_PID_VALUE);
		}else{
			//如果当前节点父ID不为空 则设置父节点的hasChildren 为1
			BcDistributor parent = baseMapper.selectById(bcDistributor.getPid());
			if(parent!=null && !"1".equals(parent.getHasChild())){
				parent.setHasChild("1");
				baseMapper.updateById(parent);
			}
		}
		baseMapper.insert(bcDistributor);
	}
	
	@Override
	public void updateBcDistributor(BcDistributor bcDistributor) {
		BcDistributor entity = this.getById(bcDistributor.getId());
		if(entity==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		String old_pid = entity.getPid();
		String new_pid = bcDistributor.getPid();
		if(!old_pid.equals(new_pid)) {
			updateOldParentNode(old_pid);
			if(oConvertUtils.isEmpty(new_pid)){
				bcDistributor.setPid(IBcDistributorService.ROOT_PID_VALUE);
			}
			if(!IBcDistributorService.ROOT_PID_VALUE.equals(bcDistributor.getPid())) {
				baseMapper.updateTreeNodeStatus(bcDistributor.getPid(), IBcDistributorService.HASCHILD);
			}
		}
		baseMapper.updateById(bcDistributor);
	}
	
	@Override
	public void deleteBcDistributor(String id) throws JeecgBootException {
		BcDistributor bcDistributor = this.getById(id);
		if(bcDistributor==null) {
			throw new JeecgBootException("未找到对应实体");
		}
		updateOldParentNode(bcDistributor.getPid());
		baseMapper.deleteById(id);
	}
	
	
	
	/**
	 * 根据所传pid查询旧的父级节点的子节点并修改相应状态值
	 * @param pid
	 */
	private void updateOldParentNode(String pid) {
		if(!IBcDistributorService.ROOT_PID_VALUE.equals(pid)) {
			Integer count = baseMapper.selectCount(new QueryWrapper<BcDistributor>().eq("pid", pid));
			if(count==null || count<=1) {
				baseMapper.updateTreeNodeStatus(pid, IBcDistributorService.NOCHILD);
			}
		}
	}

}
