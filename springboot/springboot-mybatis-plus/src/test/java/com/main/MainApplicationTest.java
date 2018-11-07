package com.main;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.main.entity.*;
import com.main.service.*;
import com.main.demo.vqo.SysResourceVqo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.main.demo.util.ConvertWrapperUtil.convert;

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

	/**
	 * 添加单个(自动生成ID,如果不设置值,默认是数据库的Default值)
	 */
	@Test
	public void testSave(){
		SysResource sr = new SysResource();
		sr.setOrderNo(100);
		sr.setResourceName("系统管理");
		sr.setResourceDesc("系统基础基础功能");
		sr.setResourceIcon("fa system");
		sr.setResourceLevel(1);

		sr.setResourcePath("#");
		boolean result = resourceService.save(sr);
	}

	/**
	 * 添加多个
	 */
	@Test
	public void testSaveList(){
		testSave();
		//资源表数据
		List<SysResource> list = new ArrayList();

		SysResource sr1 = new SysResource();
		sr1.setOrderNo(1000);
		sr1.setResourceName("资源菜单管理");
		sr1.setResourceDesc("系统菜单资源设置");
		sr1.setResourceIcon("fa resource");
		sr1.setResourceLevel(2);
		sr1.setResourcePath("sysResource");
		sr1.setResourceParent(getParentId());
		list.add(sr1);

		SysResource sr2 = new SysResource();
		sr2.setOrderNo(2000);
		sr2.setResourceName("角色管理");
		sr2.setResourceDesc("角色控制");
		sr2.setResourceIcon("fa role");
		sr2.setResourceLevel(2);
		sr2.setResourcePath("sysRole");
		sr2.setResourceParent(getParentId());
		list.add(sr2);

		SysResource sr3 = new SysResource();
		sr3.setOrderNo(3000);
		sr3.setResourceName("用户管理");
		sr3.setResourceDesc("用户管理");
		sr3.setResourceIcon("fa user");
		sr3.setResourceLevel(2);
		sr3.setResourcePath("sysUser");
		sr3.setResourceParent(getParentId());
		list.add(sr3);

		boolean result = resourceService.saveBatch(list);

		//权限表数据
		SysRole so1 = new SysRole();
		so1.setRoleAuth("ROLE_TEST");
		so1.setRoleDesc("测试");
		so1.setRoleName("测试用户");
		roleService.save(so1);

		SysRole so2 = new SysRole();
		so2.setRoleAuth("ROLE_ADMIN");
		so2.setRoleDesc("超级管理员");
		so2.setRoleName("管理员");
		roleService.save(so2);

		//用户表
		SysUser user1 = new SysUser();
		user1.setLastLoginTime(new Date());
		user1.setLoginIp("192.168.31.12");
		user1.setOutLoginTime(new Date());
		user1.setEmail("1198127035@qq.com");
		user1.setName("梁金龙");
		user1.setUsername("15277578023");
		user1.setPassword("$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6");
		user1.setAccountNonExpired(1);
		user1.setAccountNonLocked(1);
		user1.setCredentialsNonExpired(1);
		user1.setCreateUserName("admin");
		userService.save(user1);

		SysUser user2 = new SysUser();
		user2.setLastLoginTime(new Date());
		user2.setLoginIp("10.26.66.125");
		user2.setOutLoginTime(new Date());
		user2.setEmail("657603467@qq.com");
		user2.setName("周杰伦");
		user2.setUsername("13978561915");
		user2.setPassword("$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6");
		user2.setAccountNonExpired(1);
		user2.setAccountNonLocked(1);
		user2.setCredentialsNonExpired(1);
		user2.setCreateUserName("admin");
		userService.save(user2);

		SysUser user3 = new SysUser();
		user3.setLastLoginTime(new Date());
		user3.setLoginIp("115.66.40.125");
		user3.setOutLoginTime(new Date());
		user3.setEmail("1037331293@qq.com");
		user3.setName("吴彦祖");
		user3.setUsername("13978561241");
		user3.setPassword("$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6");
		user3.setAccountNonExpired(1);
		user3.setAccountNonLocked(1);
		user3.setCredentialsNonExpired(1);
		user3.setCreateUserName("admin");
		userService.save(user3);

		//权限对应资源
		SysRoleResource srr1 = new SysRoleResource();
		srr1.setMethods(15);
		srr1.setRoleId(so1.getId());
		srr1.setResourceId(sr1.getId());
		srr1.setCreateUserName("admin");
		roleResourceService.save(srr1);

		SysRoleResource srr2 = new SysRoleResource();
		srr2.setMethods(15);
		srr2.setRoleId(so1.getId());
		srr2.setResourceId(sr2.getId());
		srr2.setCreateUserName("admin");
		roleResourceService.save(srr2);

		SysRoleResource srr3 = new SysRoleResource();
		srr3.setMethods(15);
		srr3.setRoleId(so1.getId());
		srr3.setResourceId(sr3.getId());
		srr3.setCreateUserName("admin");
		roleResourceService.save(srr3);

		SysRoleResource srr4 = new SysRoleResource();
		srr4.setMethods(15);
		srr4.setRoleId(so1.getId());
		srr4.setResourceId(getParentId());
		srr4.setCreateUserName("admin");
		roleResourceService.save(srr4);

		//用户对应权限
		SysUserRole sur1 = new SysUserRole();
		sur1.setUserId(user1.getId());
		sur1.setRoleId(so1.getId());
		sur1.setCreateUserName("admin");
		userRoleService.save(sur1);

		SysUserRole sur2 = new SysUserRole();
		sur2.setUserId(user2.getId());
		sur2.setRoleId(so2.getId());
		sur2.setCreateUserName("admin");
		userRoleService.save(sur2);

		SysUserRole sur3 = new SysUserRole();
		sur3.setUserId(user3.getId());
		sur3.setRoleId(so2.getId());
		sur3.setCreateUserName("admin");
		userRoleService.save(sur3);


	}
	


	/**
	 * 根据Wrapper删除
	 */
	@Test
	public void testRemoveByWrapper(){
		boolean result = resourceService.remove(new QueryWrapper<SysResource>().eq("resource_name","系统管理"));
	}

	/**
	 * 根据ID删除
	 */
	@Test
	public void testRemoveById(){
		resourceService.removeById(getParentId());
	}


	/**
	 * 根据ID更新
	 */
	@Test
	public void testUpdateById(){
		SysResource sr = new SysResource();
		sr.setId(getParentId());
		sr.setResourceName("系统管理-修改");
		boolean result = resourceService.updateById(sr);
	}

	/**
	 * 根据Wrapper更新(对查询出来的结果进行更新)
	 */
	@Test
	public void testUpdateByWrapper(){
		SysResource sr = new SysResource();
		sr.setResourceParent(6661918L);
		QueryWrapper<SysResource> query = new QueryWrapper<>();
		query.eq("resource_name","系统管理");
		boolean result = resourceService.update(sr,query);
	}

	/**
	 * 保存或者更新(根据主键或者其他唯一字段进行判断)
	 */
	@Test
	public void testSaveOrUpdate(){
		SysResource sr = new SysResource();
		sr.setResourceName("系统管理");
		boolean result = resourceService.saveOrUpdate(sr);
	}

	/**
	 * 根据ID查询
	 */
	@Test
	public void testGetById(){
		SysResource result = resourceService.getById(getParentId());
		System.out.println(result);
	}

	/**
	 * 根据条件获取一个(重载的一个方法可以使返回多个的时候抛出异常)
	 */
	@Test
	public void testGetByWrapper(){
		SysResource result = resourceService.getOne(new QueryWrapper<SysResource>().eq("resource_name","系统管理"));
		System.out.println(result);
	}

	/**
	 * 根据条件查询
	 */
	@Test
	public void testListByWrapper(){
		QueryWrapper<SysResource> query = new QueryWrapper<>();
		query.eq("status",1);
		List<SysResource> result = resourceService.list(query);
		System.out.println(result);
	}

	/**
	 * 分页查询
	 */
	@Test
	public void testPageList(){
		resourceService.page(new Page<>(1,10),new QueryWrapper<>());
	}

	private Long getParentId(){
		Long parentId = resourceService.getOne(new QueryWrapper<SysResource>().eq("resource_name","系统管理")).getId();
		if(parentId == null){
			return 0L;
		}
		return parentId;
	}


	@Test
	public void testVqoConvert() throws Exception{
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
		vqo.setResourceLevel(2);
		vqo.setSort("desc");
		vqo.setField("resource_name");

		IPage<SysResource> page  = resourceService.page(new Page<SysResource>(1,2),convert(vqo));

		System.out.println(page);

	}


}
