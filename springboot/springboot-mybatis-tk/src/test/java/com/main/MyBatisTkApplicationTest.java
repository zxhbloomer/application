package com.main;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.main.common.util.SnowFlakeIdGenerator;
import com.main.demo.RandomChinese;
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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

	private static final Random r = new Random();
	private static final SnowFlakeIdGenerator s = new SnowFlakeIdGenerator(0,0);

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
		person.setUsername("梁金龙");
		person.setPassword("1397856722");
		person.setAge(21);
		person.setSex(1);
		Integer num = manager.insertSelective(person);
		System.out.println(num);
	}


	@Test
	public void testRandom() throws Exception{
		ExecutorService exec = Executors.newFixedThreadPool(20);
		for(int i=0;i<20;i++){
			exec.execute(()->{
				SysPerson person = new SysPerson();
				for(int j=0;j<1000000;j++){
					person.setId(s.nextId());
					person.setUsername(RandomChinese.getRandomChinese(10));
					person.setPassword(UUID.randomUUID().toString() );
					person.setAge(new Random().nextInt(Integer.MAX_VALUE));
					person.setSex(j%2);
					person.setCreateTime(new Date());
					person.setCreateUserName(Math.abs(r.nextLong())+  "");
					person.setUpdateTime(randomDate("1997-01-01 10:10:10","3000-01-01 10:10:10"));
					person.setUpdateUserName(Math.abs(r.nextLong()) + "");
					person.setStatus(false);
					person.setEnabled(false);
					manager.insert(person);
				}
			});
		}
		exec.shutdown();
		Thread.sleep(2_100_000_000);
	}

	/**
	 * 获取随机日期
	 * @param beginDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public static Date randomDate(String beginDate,String endDate){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);

			if(start.getTime() >= end.getTime()){
				return null;
			}

			long date = random(start.getTime(),end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private static long random(long begin,long end){
		long rtn = begin + (long)(Math.random() * (end - begin));
		if(rtn == begin || rtn == end){
			return random(begin,end);
		}
		return rtn;
	}

	@Test
	public void testPageHelper(){
		Page<SysPerson> page = PageHelper.startPage(0,10);
		manager.selectAll();
		System.out.println();
	}



	@Test
	public void testQuery(){
		List<SysResource> list = resourceService.listAll();
		System.out.println();
	}

}
