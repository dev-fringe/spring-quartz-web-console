package dev.fringe.scheduler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.fringe.scheduler.config.CommonConfig;
import dev.fringe.scheduler.config.SchedulerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class,SchedulerConfig.class})

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {CommonConfig.class,SchedulerConfig.class})
@Transactional(readOnly = true)
public class TestScheduler {
	
	@Autowired SchedulingService schedulingService;
	@Test public void test() {
		System.out.println(schedulingService.get(new Long(13)));
	}

}
