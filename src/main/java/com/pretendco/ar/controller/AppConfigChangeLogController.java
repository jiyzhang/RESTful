package com.pretendco.ar.controller;

import com.pretendco.ar.entity.AppConfigChangeLog;
import com.pretendco.ar.repository.AppConfigChangeLogRepository;
import com.pretendco.ar.result.ExceptionMsg;
import com.pretendco.ar.result.Response;
import com.pretendco.ar.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("appconfigchangelog")
public class AppConfigChangeLogController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private AppConfigChangeLogRepository acclr;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<AppConfigChangeLog> list = new ArrayList<AppConfigChangeLog>(acclr.findAll());
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

            List<AppConfigChangeLog> list = acclr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        } else {
            String serialNumber = parameter;
            List<AppConfigChangeLog> list = acclr.findBySerialNumber(serialNumber);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        }
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @RequstBody: httpBody, or using URL parameters
//    public ResponseData add(@RequestBody AppConfigChangeLog model) {
    public ResponseData add(AppConfigChangeLog model) {
        acclr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

//    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(AppConfigChangeLog model) {
//        alr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }

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
            List<AppConfigChangeLog> list = acclr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            for (AppConfigChangeLog item : list) {
                acclr.deleteById(item.getId());
            }
        } else {
            String serialNumber = parameter;
            List<AppConfigChangeLog> list = acclr.findBySerialNumber(serialNumber);
            for (AppConfigChangeLog item : list) {
                acclr.deleteById(item.getId());
            }
        }

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

}