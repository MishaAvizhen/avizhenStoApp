package com.avizhen.avizhenSto.controller;

import com.avizhen.avizhenSto.dao.RepairRecordDao;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.service.RepairRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@SessionAttributes({"currentUser", "cart"})
public class RepairRecordController extends CommonInitSessionControler{

    @Autowired
    private RepairRecordService repairRecordService;
    @Autowired
    private RepairRecordDao repairRecordDao;


    @RequestMapping(value = "/allRepRecordAndRepRequestList", method = RequestMethod.GET)
    public ModelAndView goToAllRepairRequetAndRepairRecordList(ModelMap model) {
        initSession(model);
        List<RepairRecord> repairRecords = repairRecordService.getListOfAllRepairRecord();
        model.addAttribute("allRepRecordAndRepRequestList", repairRecords);
        return new ModelAndView("allRepRecordAndRepRequestList", model);
    }
    @RequestMapping(value = "/deleteFromRRAndRReqForm/{repairRecordId}", method = RequestMethod.GET)
    public ModelAndView deleteFromRRAndRReqForm (ModelMap model, @PathVariable Integer  repairRecordId) {
        initSession(model);
        if (repairRecordId != null) {
            repairRecordDao.deleteRepairRecordById(repairRecordId);

            model.addAttribute("repairRecordId", repairRecordId );

        }
        return goToAllRepairRequetAndRepairRecordList( model);
    }
}



