package org.mwolff.toolbox.configuration;

import javax.annotation.Resource;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mwolff.toolbox.velocitytools.VelocityMerger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:application.xml" })
public class AppConfigTest {

    @Resource
    VelocityMerger velocityMerger;

    @Test
    public void appConfigVelocityMerger() throws Exception {
        Assert.assertThat(velocityMerger, CoreMatchers.notNullValue());
    }
}
