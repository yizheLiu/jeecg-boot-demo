package org.jeecg.modules.demo.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.entity.BcLevelCoupon;
import org.jeecg.modules.demo.entity.BcLevelProject;
import org.jeecg.modules.demo.entity.BcPatientLevel;
import org.jeecg.modules.demo.vo.BcPatientLevelPage;
import org.jeecg.modules.demo.service.IBcPatientLevelService;
import org.jeecg.modules.demo.service.IBcLevelCouponService;
import org.jeecg.modules.demo.service.IBcLevelProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 会员等级
 * @Author: jeecg-boot
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@RestController
@RequestMapping("/demo/bcPatientLevel")
@Slf4j
public class BcPatientLevelController {
	@Autowired
	private IBcPatientLevelService bcPatientLevelService;
	@Autowired
	private IBcLevelCouponService bcLevelCouponService;
	@Autowired
	private IBcLevelProjectService bcLevelProjectService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bcPatientLevel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BcPatientLevel bcPatientLevel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BcPatientLevel> queryWrapper = QueryGenerator.initQueryWrapper(bcPatientLevel, req.getParameterMap());
		Page<BcPatientLevel> page = new Page<BcPatientLevel>(pageNo, pageSize);
		IPage<BcPatientLevel> pageList = bcPatientLevelService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bcPatientLevelPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BcPatientLevelPage bcPatientLevelPage) {
		BcPatientLevel bcPatientLevel = new BcPatientLevel();
		BeanUtils.copyProperties(bcPatientLevelPage, bcPatientLevel);
		bcPatientLevelService.saveMain(bcPatientLevel, bcPatientLevelPage.getBcLevelCouponList(),bcPatientLevelPage.getBcLevelProjectList());
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bcPatientLevelPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody BcPatientLevelPage bcPatientLevelPage) {
		BcPatientLevel bcPatientLevel = new BcPatientLevel();
		BeanUtils.copyProperties(bcPatientLevelPage, bcPatientLevel);
		BcPatientLevel bcPatientLevelEntity = bcPatientLevelService.getById(bcPatientLevel.getId());
		if(bcPatientLevelEntity==null) {
			return Result.error("未找到对应数据");
		}
		bcPatientLevelService.updateMain(bcPatientLevel, bcPatientLevelPage.getBcLevelCouponList(),bcPatientLevelPage.getBcLevelProjectList());
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bcPatientLevelService.delMain(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bcPatientLevelService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BcPatientLevel bcPatientLevel = bcPatientLevelService.getById(id);
		if(bcPatientLevel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(bcPatientLevel);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryBcLevelCouponByMainId")
	public Result<?> queryBcLevelCouponListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BcLevelCoupon> bcLevelCouponList = bcLevelCouponService.selectByMainId(id);
		return Result.ok(bcLevelCouponList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryBcLevelProjectByMainId")
	public Result<?> queryBcLevelProjectListByMainId(@RequestParam(name="id",required=true) String id) {
		List<BcLevelProject> bcLevelProjectList = bcLevelProjectService.selectByMainId(id);
		return Result.ok(bcLevelProjectList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bcPatientLevel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BcPatientLevel bcPatientLevel) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<BcPatientLevel> queryWrapper = QueryGenerator.initQueryWrapper(bcPatientLevel, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<BcPatientLevel> queryList = bcPatientLevelService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<BcPatientLevel> bcPatientLevelList = new ArrayList<BcPatientLevel>();
      if(oConvertUtils.isEmpty(selections)) {
          bcPatientLevelList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          bcPatientLevelList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<BcPatientLevelPage> pageList = new ArrayList<BcPatientLevelPage>();
      for (BcPatientLevel main : bcPatientLevelList) {
          BcPatientLevelPage vo = new BcPatientLevelPage();
          BeanUtils.copyProperties(main, vo);
          List<BcLevelCoupon> bcLevelCouponList = bcLevelCouponService.selectByMainId(main.getId());
          vo.setBcLevelCouponList(bcLevelCouponList);
          List<BcLevelProject> bcLevelProjectList = bcLevelProjectService.selectByMainId(main.getId());
          vo.setBcLevelProjectList(bcLevelProjectList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "会员等级列表");
      mv.addObject(NormalExcelConstants.CLASS, BcPatientLevelPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("会员等级数据", "导出人:"+sysUser.getRealname(), "会员等级"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<BcPatientLevelPage> list = ExcelImportUtil.importExcel(file.getInputStream(), BcPatientLevelPage.class, params);
              for (BcPatientLevelPage page : list) {
                  BcPatientLevel po = new BcPatientLevel();
                  BeanUtils.copyProperties(page, po);
                  bcPatientLevelService.saveMain(po, page.getBcLevelCouponList(),page.getBcLevelProjectList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
    }

}
