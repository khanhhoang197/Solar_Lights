package cg.hdk.slshop;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.views.AdminView;

public class MainUser {
    public static void main(String[] args) {
       AdminView.loginUser(Role.USER);
    }
}
