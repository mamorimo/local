package org.test.local.web.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.test.local.web.thymeleaf.ThymeleafTemplateProcessor;

public class WebapiConfig extends ResourceConfig {

    public WebapiConfig() {

    	register(RequestContextFilter.class);

    	register(MvcFeature.class);
    	property(MvcFeature.TEMPLATE_BASE_PATH, "WEB-INF/view");

    	register(ThymeleafTemplateProcessor.class);

        packages("org.test.local.web.resources");
    }
}
