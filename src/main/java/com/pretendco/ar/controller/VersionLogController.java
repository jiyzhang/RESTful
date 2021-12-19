package com.pretendco.ar.controller;

import com.pretendco.ar.entity.VersionLog;
import com.pretendco.ar.repository.VersionLogRepository;
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
@RequestMapping("versionlog")
public class VersionLogController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private VersionLogRepository vlr;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<VersionLog> list = new ArrayList<VersionLog>(vlr.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //GetBySerialNumber
    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.GET)
    public ResponseData getList(@PathVariable("serialNumber") String serialNumber) throws IOException {
        List<VersionLog> list = vlr.findBySerialNumber(serialNumber);
        return new ResponseData(ExceptionMsg.SUCCESS, list);

    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    // @RequstBody: httpBody, or using URL parameters
//    public ResponseData add(@RequestBody VersionLog model) {
    public ResponseData add(VersionLog model) {
        vlr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

//    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(VersionLog model) {
//        alr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }

    //deleteBySerialNumber
    @RequestMapping(value = "/{parameter}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("parameter") String parameter) {
        String serialNumber = parameter;
        List<VersionLog> list = vlr.findBySerialNumber(serialNumber);
        for (VersionLog item : list) {
            vlr.deleteById(item.getId());
        }

        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

}