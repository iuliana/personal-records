package com.pr.repos;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iuliana.cosmina on 5/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-service-config.xml", "classpath:spring/db-config.xml"})
@Transactional
public class RepoTestCase {
}
