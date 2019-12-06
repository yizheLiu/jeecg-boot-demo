package org.jeecg.modules.demo.service;

import org.jeecg.modules.demo.entity.BcLevelCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 等级卡券
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface IBcLevelCouponService extends IService<BcLevelCoupon> {

	public List<BcLevelCoupon> selectByMainId(String mainId);
}
