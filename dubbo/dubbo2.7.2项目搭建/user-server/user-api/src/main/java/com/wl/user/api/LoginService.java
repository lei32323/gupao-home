package com.wl.user.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/login")
public interface LoginService {

    @GET
    @Path("/{name}")
     String login(@PathParam("name") String name);

}
