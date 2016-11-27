package com.qingqing.stock_analyse;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-dao.xml",
        "classpath:applicationContext-manager.xml"})
@TransactionConfiguration(transactionManager = "transactionManagerSa" , defaultRollback = true)
public class TestBase {


}
