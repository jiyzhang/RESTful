package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ActivityLog;
import com.pretendco.ar.entity.AppConfigChangeLog;
import com.pretendco.ar.entity.AppConfigChangeLogId;
import com.pretendco.ar.repository.AppConfigChangeLogRepository;
import com.pretendco.ar.result.ExceptionMsg;
import com.pretendco.ar.result.Response;
import com.pretendco.ar.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

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
    @RequestMapping(value = "/{SerialNumber}", method = RequestMethod.GET)
    public ResponseData getList(@PathVariable("SerialNumber") String serialNumber) throws IOException {
        List<AppConfigChangeLog> list = acclr.findBySerialNumber(serialNumber);
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //GetByPK ?
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.GET)
//    public ResponseData find(@PathVariable("serialNumber") String serialNumber) throws IOException {
//        AppConfigChangeLog accl= acclr.findBySerialNumber(serialNumber);
//        if (accl != null) {
//            return new ResponseData(ExceptionMsg.SUCCESS, accl);
//        }
//        return new ResponseData(ExceptionMsg.FAILED, accl);
//    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseData add(AppConfigChangeLog model) {
//        AppConfigChangeLogId id = new AppConfigChangeLogId(model.
//        acclr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }
    public ResponseData add(@RequestParam("SerialNumber") String sn,
                            @RequestParam("SessionID") String sessid,
                            @RequestParam("ParameterChangeTime") String pctime,
                            @RequestParam("ParameterToBeChanged") String ptbc,
                            @RequestParam("ParameterValueBeforeChange") String pbefore,
                            @RequestParam("ParameterValueAfterChange") String pafter) {


        Date pChangeTime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            pChangeTime = formatter.parse(pctime);
            System.out.println(formatter.format(pChangeTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        AppConfigChangeLogId id = new AppConfigChangeLogId();
        id.setSerialNumber(sn);
        id.setSessionId(sessid);
        id.setParameterChangeTime( new Timestamp(pChangeTime.getTime()) );

        AppConfigChangeLog model = new AppConfigChangeLog();
        model.setId(id);
        model.setParameterToBeChanged(ptbc);
        model.setParameterValueBeforeChange(pbefore);
        model.setParameterValueAfterChange(pafter);

        acclr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(AppConfigChangeLog model) {
//        acclr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }

//    //deleteOne
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.DELETE)
//    public Response delete(@PathVariable("serialNumber") String serialNumber) {
//        AppConfigChangeLog demo = acclr.findBySerialNumber(serialNumber);
//        if (demo != null) {
//            acclr.deleteById(serialNumber); //???
//        }
//        return result(ExceptionMsg.SUCCESS);
//        //return new ResponseData(ExceptionMsg.SUCCESS,"");
//    }
}
