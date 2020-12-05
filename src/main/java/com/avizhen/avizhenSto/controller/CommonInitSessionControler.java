package com.avizhen.avizhenSto.controller;

import com.avizhen.avizhenSto.constants.RoleConstants;
import com.avizhen.avizhenSto.entity.User;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 23.11.2020.
 */
public class CommonInitSessionControler {
    public void initSession(ModelMap model) {
        if (!model.containsAttribute("currentUser")) {
            User guest = new User("guest", "", "", RoleConstants.CLIENT_ROLE, "Guest", "Guest");
            guest.setId(0);
            model.addAttribute("currentUser", guest);
        }
        if (!model.containsAttribute("cart")) {
            List<Integer> cartDetailIds = new ArrayList<>();
            model.addAttribute("cart", cartDetailIds);
        }
    }

}
