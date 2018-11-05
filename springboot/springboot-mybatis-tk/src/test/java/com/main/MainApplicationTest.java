package com.main;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.main.demo.util.ConvertWrapperUtil;
import com.main.demo.vqo.SysResourceVqo;
import com.main.entity.SysResource;
import com.main.mapper.SysResourceMapper;
import com.main.mapper.SysRoleMapper;
import com.main.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@Slf4j
public class MainApplicationTest {

	@Autowired
	SysResourceService resourceService;
	@Autowired
	SysRoleService roleService;
	@Autowired
	SysUserService userService;
	@Autowired
	SysRoleResourceService roleResourceService;
	@Autowired
	SysUserRoleService userRoleService;

	@Test
	public void test01() throws Exception{

		SysResourceVqo vqo = new SysResourceVqo();
		vqo.setCreateTimeGreater(new Date());
		vqo.setCreateTimeLess(new Date());
		vqo.setOrderNo(100);
		vqo.setResourceDesc("测试");
		vqo.setResourceIcon("fa test");
		vqo.setResourceLevel(1);
		vqo.setResourceName("系统测试");
		vqo.setResourceParent(123456789L);
		vqo.setResourcePath("/test");
		vqo.setField("resource_name");
		vqo.setSort("asc");

		resourceService.listByExample(ConvertWrapperUtil.convert(vqo));
	}


}
