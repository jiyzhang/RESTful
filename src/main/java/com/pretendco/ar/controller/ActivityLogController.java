package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ActivityLog;
import com.pretendco.ar.repository.ActivityLogRepository;
import com.pretendco.ar.result.ExceptionMsg;
import com.pretendco.ar.result.Response;
import com.pretendco.ar.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("activitylog")
public class ActivityLogController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private ActivityLogRepository alr;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<ActivityLog> list = new ArrayList<ActivityLog>(alr.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //GetBySerialNumber
    //OR
    //GetBySerialNumberAndSessionID
    //serialNumber,sesionID
    @RequestMapping(value = "/{parameter}", method = RequestMethod.GET)
    public ResponseData getList(@PathVariable("parameter") String parameter) throws IOException {
        if (parameter.contains(",") ){
            String[] ss = parameter.split(",");
            String serialNumber = ss[0];
            String sessionID = ss[1];

            List<ActivityLog> list = alr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        } else {
            String serialNumber = parameter;
            List<ActivityLog> list = alr.findBySerialNumber(serialNumber);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        }
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @RequstBody: httpBody, or using URL parameters
//    public ResponseData add(@RequestBody ActivityLog model) {
    public ResponseData add(ActivityLog model) {
        System.out.println("SerialNumber: " + model.getSerialNumber());
        System.out.println("SessionID: " + model.getSessionID());

        alr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOneField: PATCH
    //UpdateAllFields: PUT, null for fields not mentioned in SQL
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseData update(ActivityLog model) {
        String sessionID = model.getSessionID();
        Timestamp sessionTerminationTime = model.getSessionTerminationTime();

        List<ActivityLog> als = alr.findBySessionID(sessionID);
        int count = als.size();
        if( count == 0 ) {
            return new ResponseData(ExceptionMsg.NORECORDFOUND);
        }else if( count == 1 ) {
            ActivityLog al = als.get(0);
            al.setSessionTerminationTime(sessionTerminationTime);
            alr.save(al);
            return new ResponseData(ExceptionMsg.SUCCESS, al);
        }else {
//            for(ActivityLog al:als) {
//                al.setSessionTerminationTime(sessionTerminationTime);
//                alr.save(al);
//            }
            return new ResponseData(ExceptionMsg.MORETHANONERECFOUND);
        }
    }

    //deleteBySerialNumber
    //OR
    //deleteBySerialNumberAndSessionID
    //serialNumber,sesionID
    @RequestMapping(value = "/{parameter}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("parameter") String parameter) {
        if (parameter.contains(",")) {
            String[] ss = parameter.split(",");
            String serialNumber = ss[0];
            String sessionID = ss[1];
            List<ActivityLog> list = alr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            for (ActivityLog item : list) {
                alr.deleteById(item.getId());
            }
        } else {
            String serialNumber = parameter;
            List<ActivityLog> list = alr.findBySerialNumber(serialNumber);
            for (ActivityLog item : list) {
                alr.deleteById(item.getId());
            }
        }

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

}



