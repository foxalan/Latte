package net.qiujuer.web.italker.push.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/account")
public class AccountService {


    @GET
    @Path("/login")
    public String get(){
        return "you get the login";
    }
}
