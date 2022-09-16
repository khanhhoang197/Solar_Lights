package cg.hdk.slshop;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.views.AdminView;

public class MainAdmin {
    public static void main(String[] args) {
        AdminView.loginAdmin(Role.ADMIN);
    }
}
