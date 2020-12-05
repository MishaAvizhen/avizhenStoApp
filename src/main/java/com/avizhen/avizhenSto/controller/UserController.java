package com.avizhen.avizhenSto.controller;

import com.avizhen.avizhenSto.dto.RepairRecordDto;
import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.dto.UserRegistrationDto;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRegistrationException;
import com.avizhen.avizhenSto.exception.InvalidRepairRecordException;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;
import com.avizhen.avizhenSto.service.RepairRecordService;
import com.avizhen.avizhenSto.service.RepairRequestService;
import com.avizhen.avizhenSto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"currentUser", "cart"})
public class UserController extends CommonInitSessionControler {
    @Autowired
    private UserService userService;
    @Autowired
    private RepairRequestService repairRequestService;
    @Autowired
    private RepairRecordService repairRecordService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showWelcomePage(ModelMap model) {
        initSession(model);
        return new ModelAndView("index", model);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String goToRegistrationPage(ModelMap model) {
        initSession(model);
        return "registration";
    }

    @RequestMapping("/logout")
    public ModelAndView logoutUser(ModelMap model) {
        model.remove("currentUser");
        model.remove("cart");
        initSession(model);
        return showWelcomePage(model);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(ModelMap model, @RequestParam("login") String login, @RequestParam("password") String password) {
        User logonUser = userService.loginUserOrReturnNull(login, password);
        if (logonUser != null) {
            model.addAttribute("currentUser", logonUser);
            return "index";
        } else {
            model.addAttribute("msg", "Пользователь с именем " + login + " не найден");
            return "login";
        }
    }

    @RequestMapping(value = "/registerClient", method = RequestMethod.POST)
    public ModelAndView registrationUser(ModelMap model, @RequestParam("login") String login, @RequestParam("password")
            String password, @RequestParam("first_name") String name, @RequestParam("last_name") String surname,
                                         @RequestParam("phone_number") String phoneNumber) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto.Builder(login, password)
                .setName(name)
                .setSurname(surname)
                .setPhoneNumber(phoneNumber)
                .build();

        try {
            userService.registerClient(userRegistrationDto);
            model.addAttribute("msg", "User was created");
        } catch (InvalidRegistrationException e) {
            model.addAttribute("msg", e.getMessage());
        }

        return showWelcomePage(model);
    }

    @RequestMapping(value = "/repairRequest", method = RequestMethod.GET)
    public String goToRepairRequestPage(ModelMap model) {
        initSession(model);
        return "repairRequest";
    }

    @RequestMapping(value = "/repairRequest", method = RequestMethod.POST)
    public ModelAndView repairRequestMark(ModelMap model,
                                          @RequestParam("date_of_repair") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfRepair,
                                          @RequestParam("car_remark") String carRemark,
                                          @RequestParam("repair_description") String repairDescription,
                                          @ModelAttribute("currentUser") User currentUser) {

        RepairRequestDto repairRequestDto = new RepairRequestDto.Builder(dateOfRepair)
                .setRepairDescription(repairDescription)
                .setCarRemark(carRemark)

                .build();

        try {
            repairRequestService.registerRepairRequest(repairRequestDto, currentUser);
            model.addAttribute("msg", "Repair request  was added");
        } catch (InvalidRepairRequestException e) {
            model.addAttribute("msg", e.getMessage());
        }

        return showWelcomePage(model);

    }

    @RequestMapping(value = "/repairRecord", method = RequestMethod.POST)
    public ModelAndView repairRecordMark(ModelMap model,
                                         @RequestParam("repair_description") String repairDescription,
                                         @RequestParam("detail_price") String detailPrice,
                                         @RequestParam("work_price") String workPrice,
                                         @RequestParam("other_notes") String otherNotes,
                                         @RequestParam("repair_request_id") int repairRequestId) {

        RepairRecordDto repairRecordDto = new RepairRecordDto.Builder(repairDescription)
                .setDetailPrice(detailPrice)
                .setWorkPrice(workPrice)
                .setOtherNotes(otherNotes)
                .build();
        try {
            repairRecordService.registerRepairRecord(repairRecordDto, repairRequestId);
            model.addAttribute("msg", "Repair record  was added");

        } catch (InvalidRepairRecordException e) {
            model.addAttribute("msg", e.getMessage());
        }
        return showWelcomePage(model);
    }

    @RequestMapping(value = "/allUserOrders", method = RequestMethod.GET)
    public ModelAndView goToAllUserOrdersPage(ModelMap model, @ModelAttribute("currentUser") User currentUser) {
        initSession(model);
        Integer userId = currentUser.getId();
        List<RepairRecord> repairRecordList = userService.getUserRepairRecordList(userId);
        model.addAttribute("repairRecordList", repairRecordList);
        return new ModelAndView("allUserOrders", model);
    }


}

