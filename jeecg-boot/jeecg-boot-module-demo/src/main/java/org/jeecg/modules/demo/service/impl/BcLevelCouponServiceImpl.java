package org.jeecg.modules.demo.service.impl;

import org.jeecg.modules.demo.entity.BcLevelCoupon;
import org.jeecg.modules.demo.mapper.BcLevelCouponMapper;
import org.jeecg.modules.demo.service.IBcLevelCouponService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 等级卡券
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class BcLevelCouponServiceImpl extends ServiceImpl<BcLevelCouponMapper, BcLevelCoupon> implements IBcLevelCouponService {
	
	@Autowired
	private BcLevelCouponMapper bcLevelCouponMapper;
	
	@Override
	public List<BcLevelCoupon> selectByMainId(String mainId) {
		return bcLevelCouponMapper.selectByMainId(mainId);
	}
}
