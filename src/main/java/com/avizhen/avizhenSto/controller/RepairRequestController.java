package com.avizhen.avizhenSto.controller;

import com.avizhen.avizhenSto.constants.RoleConstants;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.service.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes({"currentUser", "cart"})
public class RepairRequestController extends CommonInitSessionControler {

    @Autowired
    private RepairRequestService repairRequestService;

    @RequestMapping(value = "/allRepairRequests", method = RequestMethod.GET)
    public ModelAndView goToAllRepairRequest(ModelMap model) {
        initSession(model);
        List<RepairRequest> repairRequests = repairRequestService.getListOfActiveRepairRequests();
        model.addAttribute("allRepairRequests", repairRequests);
        return new ModelAndView("allRepairRequests", model);
    }



    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)

    public ModelAndView goToUserProfile(ModelMap model, @ModelAttribute("currentUser") User currentUser) {
        initSession(model);
        Integer userId = currentUser.getId();
        List<RepairRequest> allRepairRequestsOfUser = repairRequestService.getListAllRepairRequestsOfUsers(userId);
        model.addAttribute("userRepairRequests", allRepairRequestsOfUser);
        return new ModelAndView("userProfile", model);
    }


}
