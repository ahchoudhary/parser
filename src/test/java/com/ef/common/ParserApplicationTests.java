package com.ef.common;

import com.ef.ParserStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserApplicationTests {

	/*@Test
	public void contextLoads() {
	}*/

	@Autowired
	ApplicationContext ctx;

	@Test
	public void testRun() {
		String[] args = {"accesslog=/Users/husain/Downloads/Java_MySQL_Test/access.log","startDate=2017-01-01.13:00:00", "duration=hourly", "threshold=100"};
		DefaultApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
		ParserStarter runner = ctx.getBean(ParserStarter.class);
		try {
			runner.run (applicationArguments );
			assert(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
