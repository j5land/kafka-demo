package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath*:applicationContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public abstract class BaseSpringTestCase {
  protected final static Log log = LogFactory.getLog("spiderticket film Test:");
  static{
	  System.out.println("applicationContext started!");
  }
}

