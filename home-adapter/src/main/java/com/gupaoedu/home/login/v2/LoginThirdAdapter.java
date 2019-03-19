package com.gupaoedu.home.login.v2;

import com.gupaoedu.home.login.v1.UserService;
import com.gupaoedu.home.login.v2.adapters.ILoginAdapter;
import com.gupaoedu.home.login.v2.adapters.LoginForQQAdapter;
import com.gupaoedu.home.login.v2.adapters.LoginForWXAdapter;

/**
 * 第三方适配器
 */
public class LoginThirdAdapter extends UserService implements ILoginForThirdAdapter{


    @Override
    public void loginForQQ(String qq) {
//        LoginForQQAdapter loginForQQAdapter = new LoginForQQAdapter();
//        if(loginForQQAdapter.support(loginForQQAdapter)){
//            loginForQQAdapter.login(qq);
//        }
        supper(qq,LoginForQQAdapter.class);
    }

    @Override
    public void loginForWX(String wx) {
//        LoginForWXAdapter loginForWXAdapter = new LoginForWXAdapter();
//        if(loginForWXAdapter.support(loginForWXAdapter)){
//            loginForWXAdapter.login(wx);
//        }
        supper(wx,LoginForWXAdapter.class);
    }

    @Override
    public void loginForPhone(String phone) {

    }

    @Override
    public void registerLogn(String username, String password) {
        super.register(username,password);
        super.loginByUsernamePassword(username,password);
    }

    private void supper(String key,Object apadper) {
        try{
            Class<?> aClass = apadper.getClass();
            ILoginAdapter iLoginAdapter = (ILoginAdapter) aClass.newInstance();
            if(iLoginAdapter.support(iLoginAdapter)){
                iLoginAdapter.login(key);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
