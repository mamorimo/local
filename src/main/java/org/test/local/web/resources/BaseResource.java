package org.test.local.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("base")
public class BaseResource {

	@GET
	public Response init() {

		return Response.ok(new Viewable("/vuespatest2")).build();
	}
}
