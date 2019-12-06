package org.jeecg.modules.demo.service.impl;

import org.jeecg.modules.demo.entity.BcPatientLevel;
import org.jeecg.modules.demo.entity.BcLevelCoupon;
import org.jeecg.modules.demo.entity.BcLevelProject;
import org.jeecg.modules.demo.mapper.BcLevelCouponMapper;
import org.jeecg.modules.demo.mapper.BcLevelProjectMapper;
import org.jeecg.modules.demo.mapper.BcPatientLevelMapper;
import org.jeecg.modules.demo.service.IBcPatientLevelService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class BcPatientLevelServiceImpl extends ServiceImpl<BcPatientLevelMapper, BcPatientLevel> implements IBcPatientLevelService {

	@Autowired
	private BcPatientLevelMapper bcPatientLevelMapper;
	@Autowired
	private BcLevelCouponMapper bcLevelCouponMapper;
	@Autowired
	private BcLevelProjectMapper bcLevelProjectMapper;
	
	@Override
	@Transactional
	public void saveMain(BcPatientLevel bcPatientLevel, List<BcLevelCoupon> bcLevelCouponList,List<BcLevelProject> bcLevelProjectList) {
		bcPatientLevelMapper.insert(bcPatientLevel);
		if(bcLevelCouponList!=null && bcLevelCouponList.size()>0) {
			for(BcLevelCoupon entity:bcLevelCouponList) {
				//外键设置
				entity.setLevelid(bcPatientLevel.getId());
				bcLevelCouponMapper.insert(entity);
			}
		}
		if(bcLevelProjectList!=null && bcLevelProjectList.size()>0) {
			for(BcLevelProject entity:bcLevelProjectList) {
				//外键设置
				entity.setLevelid(bcPatientLevel.getId());
				bcLevelProjectMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(BcPatientLevel bcPatientLevel,List<BcLevelCoupon> bcLevelCouponList,List<BcLevelProject> bcLevelProjectList) {
		bcPatientLevelMapper.updateById(bcPatientLevel);
		
		//1.先删除子表数据
		bcLevelCouponMapper.deleteByMainId(bcPatientLevel.getId());
		bcLevelProjectMapper.deleteByMainId(bcPatientLevel.getId());
		
		//2.子表数据重新插入
		if(bcLevelCouponList!=null && bcLevelCouponList.size()>0) {
			for(BcLevelCoupon entity:bcLevelCouponList) {
				//外键设置
				entity.setLevelid(bcPatientLevel.getId());
				bcLevelCouponMapper.insert(entity);
			}
		}
		if(bcLevelProjectList!=null && bcLevelProjectList.size()>0) {
			for(BcLevelProject entity:bcLevelProjectList) {
				//外键设置
				entity.setLevelid(bcPatientLevel.getId());
				bcLevelProjectMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		bcLevelCouponMapper.deleteByMainId(id);
		bcLevelProjectMapper.deleteByMainId(id);
		bcPatientLevelMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			bcLevelCouponMapper.deleteByMainId(id.toString());
			bcLevelProjectMapper.deleteByMainId(id.toString());
			bcPatientLevelMapper.deleteById(id);
		}
	}
	
}
