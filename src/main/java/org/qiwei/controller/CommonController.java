package org.qiwei.controller;

import com.google.inject.Inject;
import org.qiwei.mapper.TestMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by admin on 2017/7/4.
 */

@Path("/")
public class CommonController {

    @Inject
    TestMapper testMapper;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        System.out.println(testMapper.getById(1L).getErrorMsg());
        return "I am Index page";
    }


    @GET
    @Path("user/{userId}/{userName}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("userId") String userId, @PathParam("userName") String userName) {
        return "User ID: " + userId + ", user name: " + userName;
    }

}
