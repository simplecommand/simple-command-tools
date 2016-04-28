package org.mwolff.configuration;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.velocitytools.VelocityMerger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class AppConfigTest {

    @Resource
    VelocityMerger velocityMerger;

    @Test
    public void appConfigVelocityMerger() throws Exception {
        assertThat(velocityMerger, CoreMatchers.notNullValue());
    }

}
