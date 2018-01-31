package com.cy.example.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cy.example.carrier.PageCa;
import com.cy.example.controller.BaseController;
import com.cy.example.entity.system.SysPermissionEntity;
import com.cy.example.service.IPermissionService;

@Controller
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(@ModelAttribute("per") SysPermissionEntity per) {
		boolean flag = permissionService.insert(per);
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag) {
			map.put("flag", flag);
			map.put("msg", "添加成功！");
		} else {
			map.put("flag", flag);
			map.put("msg", "添加失败！");
		}
		return map;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(@ModelAttribute("per") SysPermissionEntity per) {
		boolean flag = permissionService.updateById(per);
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag) {
			map.put("flag", flag);
			map.put("msg", "更新成功！");
		} else {
			map.put("flag", flag);
			map.put("msg", "更新失败！");
		}
		return map;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@ModelAttribute("per") SysPermissionEntity per) {
		boolean flag = permissionService.deleteById(per.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		if (flag) {
			map.put("flag", flag);
			map.put("msg", "删除成功！");
		} else {
			map.put("flag", flag);
			map.put("msg", "删除失败！");
		}
		return map;
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public Map<String, Object> findAll(int page, int rows) {
		Page<SysPermissionEntity> list = permissionService.selectPage(new Page<SysPermissionEntity>(page, rows)
				, new EntityWrapper<SysPermissionEntity>());
		Map<String, Object> map = new HashMap<String, Object>();
		int sum = permissionService.selectCount(new EntityWrapper<SysPermissionEntity>());
		/*for(SysPermissionEntity entity:data){
			entity.setRoles(new ArrayList<SysRoleEntity>());
		}*/
		map.put("rows", list.getRecords());
		map.put("total", sum);
		return map;
	}

	@RequestMapping("/searchData")
	@ResponseBody
	public Map<String, Object> searchData(
			@ModelAttribute("per") SysPermissionEntity per,
			@ModelAttribute("page") PageCa page) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysPermissionEntity> list = permissionService.searchAll(
				per, page);
		int sum = permissionService.searchAllCount(per);
		/*for(SysPermissionEntity entity:list){
			entity.setRoles(new ArrayList<SysRoleEntity>());
		}*/
		map.put("rows", list);
		map.put("total", sum);
		return map;
	}

}
