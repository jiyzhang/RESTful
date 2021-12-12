package com.pretendco.ar.controller;

import com.pretendco.ar.entity.SelectionLog;
import com.pretendco.ar.repository.SelectionLogRepository;
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
@RequestMapping("selectionlog")
public class SelectionLogController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private SelectionLogRepository slr;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<SelectionLog> list = new ArrayList<SelectionLog>(slr.findAll());
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

            List<SelectionLog> list = slr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        } else {
            String serialNumber = parameter;
            List<SelectionLog> list = slr.findBySerialNumber(serialNumber);
            return new ResponseData(ExceptionMsg.SUCCESS, list);
        }
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @RequstBody: httpBody, or using URL parameters
//    public ResponseData add(@RequestBody SelectionLog model) {
    public ResponseData add(SelectionLog model) {
        slr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

//    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(SelectionLog model) {
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
            List<SelectionLog> list = slr.findBySerialNumberAndSessionID(serialNumber, sessionID);
            for (SelectionLog item : list) {
                slr.deleteById(item.getId());
            }
        } else {
            String serialNumber = parameter;
            List<SelectionLog> list = slr.findBySerialNumber(serialNumber);
            for (SelectionLog item : list) {
                slr.deleteById(item.getId());
            }
        }

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

}