package net.qiujuer.web.italker.push.service;


import com.google.common.base.Strings;
import net.qiujuer.web.italker.push.bean.api.account.AccountRspModel;
import net.qiujuer.web.italker.push.bean.api.account.LoginModel;
import net.qiujuer.web.italker.push.bean.api.account.RegisterModel;
import net.qiujuer.web.italker.push.bean.api.base.ResponseModel;
import net.qiujuer.web.italker.push.bean.db.User;
import net.qiujuer.web.italker.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/account")
public class AccountService extends BaseService {


    //登录
    @POST
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public ResponseModel<AccountRspModel> login(LoginModel model) {

        //检查参数是否为空
        if (!LoginModel.check(model)) {
            return ResponseModel.buildParameterError();
        }

        //查询数据库
        User user = UserFactory.login(model.getAccount(), model.getPassword());

        if (user != null) {

            // 如果有携带PushId
            if (!Strings.isNullOrEmpty(model.getPushId())) {
                return bind(user, model.getPushId());
            }
            AccountRspModel accountRspModel = new AccountRspModel(user);

            return ResponseModel.buildOk(accountRspModel);

        } else {
            return ResponseModel.buildAccountError();
        }
    }

    @GET
    @Path("/test")
    public String test(){
        return "test";
    }


    //注册
    @POST
    // 指定请求与返回的相应体为JSON
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public ResponseModel<AccountRspModel> register(RegisterModel model) {
        if (!RegisterModel.check(model)) {
            // 返回参数异常
            return ResponseModel.buildParameterError();
        }

        //检查是否已注册
        User user = UserFactory.findByPhone(model.getAccount().trim());
        if (user != null) {
            return ResponseModel.buildHaveAccountError();
        }

        user = UserFactory.findByName(model.getName().trim());
        if (user != null) {
            // 已有用户名
            return ResponseModel.buildHaveNameError();
        }

        // 开始注册逻辑
        user = UserFactory.register(model.getAccount(),
                model.getPassword(),
                model.getName());

        if (user != null) {

            // 如果有携带PushId
            if (!Strings.isNullOrEmpty(model.getPushId())) {
                return bind(user, model.getPushId());
            }

            // 返回当前的账户
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);
        } else {
            // 注册异常
            return ResponseModel.buildRegisterError();
        }
    }

    /**
     * 绑定的操作
     *
     * @param self   自己
     * @param pushId PushId
     * @return User
     */
    private ResponseModel<AccountRspModel> bind(User self, String pushId) {
        // 进行设备Id绑定的操作
        User user = UserFactory.bindPushId(self, pushId);

        if (user == null) {
            // 绑定失败则是服务器异常
            return ResponseModel.buildServiceError();

        }

        // 返回当前的账户, 并且已经绑定了
        AccountRspModel rspModel = new AccountRspModel(user, true);
        return ResponseModel.buildOk(rspModel);

    }
}
