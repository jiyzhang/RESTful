package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ErrorLog;
import com.pretendco.ar.repository.ErrorLogRepository;
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
@RequestMapping("errorlog")
public class ErrorLogController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private ErrorLogRepository elr;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<ErrorLog> list = new ArrayList<ErrorLog>(elr.findAll());
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

            List<ErrorLog> list = elr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        } else {
            String serialNumber = parameter;
            List<ErrorLog> list = elr.findBySerialNumber(serialNumber);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        }
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @RequstBody: httpBody, or using URL parameters
//    public ResponseData add(@RequestBody ErrorLog model) {
    public ResponseData add(ErrorLog model) {
        elr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

//    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(ErrorLog model) {
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
            List<ErrorLog> list = elr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            for (ErrorLog item : list) {
                elr.deleteById(item.getId());
            }
        } else {
            String serialNumber = parameter;
            List<ErrorLog> list = elr.findBySerialNumber(serialNumber);
            for (ErrorLog item : list) {
                elr.deleteById(item.getId());
            }
        }

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

}