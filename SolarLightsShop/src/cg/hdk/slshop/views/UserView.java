package cg.hdk.slshop.views;

import cg.hdk.slshop.service.IUserService;
import cg.hdk.slshop.service.UserService;

public class UserView {
    private IUserService userService;
    public UserView(){
       userService = UserService.getInstance();
    }

}
