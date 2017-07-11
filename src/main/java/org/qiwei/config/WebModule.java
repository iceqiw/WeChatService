package org.qiwei.config;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/7/4.
 */
public class WebModule extends ServletModule {

    @Override
    protected void configureServlets() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("com.sun.jersey.config.property.packages", "org.qiwei.controller");
        serve("/*").with(GuiceContainer.class, parameters);
    }

}