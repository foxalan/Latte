package net.qiujuer.web.italker.push.service;


import net.qiujuer.web.italker.push.bean.api.account.AccountRspModel;
import net.qiujuer.web.italker.push.bean.api.account.LoginModel;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/account")
public class AccountService extends BaseService{


    //登录
    @POST
    @Path("/login")
    public ResponseModel<AccountRspModel> login(LoginModel model){

        //检查参数是否为空
        if (!LoginModel.check(model)){
            return ResponseModel.buildParameterError();
        }




        return null;
    }
}
