package org.test.local.web.thymeleaf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.AbstractTemplateProcessor;
import org.jvnet.hk2.annotations.Optional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ThymeleafTemplateProcessor extends AbstractTemplateProcessor<String> {

    @Inject
    HttpServletRequest request;

    @Inject
    HttpServletResponse response;

    @Inject
    ServletContext servletContext;

    @Inject
    public ThymeleafTemplateProcessor(Configuration config, @Optional ServletContext servletContext) {
        super(config, servletContext, "thymeleaf", ".html");
        this.servletContext = servletContext;
    }

    @SuppressWarnings("unchecked")
    @Override
	public void writeTo(String templateReference, Viewable viewable, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException {

        try(Writer writer = new OutputStreamWriter(out)) {
            Object o = viewable.getModel();
            Map<String, Object> stash;

            if (o instanceof Map) {
                stash = (Map<String, Object>)o;
            } else {
                stash = new HashMap<String, Object>();

                // @ErrorTemplate時はパラメーターがExceptionで返ってくる

                if (o instanceof Exception) {
                    stash.put("error", o);
                } else {
                    // 返ってきた値がMapでない場合にitなキーで参照出来るように
                    stash.put("it", o);
                }
            }

            WebContext context = new WebContext(request, response, servletContext);
            context.setVariables(stash);

            TemplateEngine engine = new TemplateEngine();

            ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
            resolver.setTemplateMode("LEGACYHTML5");
            resolver.setCharacterEncoding("UTF-8");

            engine.setTemplateResolver(resolver);
            engine.process(templateReference, context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String resolve(String templatePath, Reader reader) throws Exception {
        return templatePath;
    }

}
