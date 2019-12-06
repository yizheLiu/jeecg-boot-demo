package org.jeecg.modules.demo.mapper;

import java.util.List;
import org.jeecg.modules.demo.entity.BcLevelCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 等级卡券
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
public interface BcLevelCouponMapper extends BaseMapper<BcLevelCoupon> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<BcLevelCoupon> selectByMainId(@Param("mainId") String mainId);
}
