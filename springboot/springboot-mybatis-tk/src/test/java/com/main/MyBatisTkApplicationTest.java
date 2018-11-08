package com.main;

import com.main.demo.util.ConvertWrapperUtil;
import com.main.demo.vqo.SysResourceVqo;
import com.main.entity.SysPerson;
import com.main.entity.SysResource;
import com.main.manager.SysPersonManager;
import com.main.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyBatisTkApplication.class)
@Slf4j
public class MyBatisTkApplicationTest {

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

	@Autowired
	SysPersonManager manager;

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

		List<SysResource> list = resourceService.listByExample(ConvertWrapperUtil.convertExample(vqo));
		System.out.println(list);
	}

	@Test
	public void testSlave(){
		SysPerson person = new SysPerson();
		person.setId(1234567890L);
		person.setUsername("梁金龙");
		person.setPassword("1397856722");
		person.setAge(21);
		person.setSex(1);
		Integer num = manager.insert(person);
		System.out.println(num);
	}



}
