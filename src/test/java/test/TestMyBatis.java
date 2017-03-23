package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tan.parking.model.User;
import com.tan.parking.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
	@Resource
	private IUserService userService = null;

	@Test
	public void testMybatis() {
		User user = userService.getUserByUserName("admin");
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		String result = JSON.toJSONString(user);
		System.out.println(result);
		System.out.println("Hello！");
	}

}
