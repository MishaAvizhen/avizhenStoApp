package com.avizhen.avizhenSto.controller.rest;

import com.avizhen.avizhenSto.dao.RepairRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class RepairRecordRestController {
    @Autowired
    RepairRecordDao repairRecordDao;

    @RequestMapping(value = "/deleteFromRRAndRReqForm/{repairRecordId}", method = RequestMethod.GET)
    public ResponseEntity removeDetailFromRepairRecord(@PathVariable Integer repairRecordId) {
        if (repairRecordId != null) {
            repairRecordDao.deleteRepairRecordById(repairRecordId);

        }

        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
