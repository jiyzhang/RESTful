package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ErrorLog;
import com.pretendco.ar.entity.ErrorLogId;
import com.pretendco.ar.repository.ErrorLogRepository;
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
    @RequestMapping(value = "/{SerialNumber}", method = RequestMethod.GET)
    public ResponseData getList(@PathVariable("SerialNumber") String serialNumber) throws IOException {
        List<ErrorLog> list = new ArrayList<ErrorLog>(elr.findBySerialNumber(serialNumber));
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseData add(ErrorLog model) {
//        ErrorLogId id = new ErrorLogId(model.
//        acclr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }
    public ResponseData add(@RequestParam("SerialNumber") String sn,
                            @RequestParam("SessionID") String sessid,
                            @RequestParam("EventTime") String eventtime,
                            @RequestParam("ErrorMessage") String errormsg
                            ) {

        Date eventTime = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            eventTime = formatter.parse(eventtime);
            System.out.println(formatter.format(eventTime));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ErrorLogId id = new ErrorLogId();
        id.setSerialNumber(sn);
        id.setSessionID(sessid);
        id.setEventTime( new Timestamp(eventTime.getTime()) );

        ErrorLog model = new ErrorLog();
        model.setId(id);
        model.setErrorMessage(errormsg);

        elr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOne, 全部修改
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
//    public ResponseData update(ErrorLog model) {
//        elr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }

//    //deleteOne
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.DELETE)
//    public Response delete(@PathVariable("serialNumber") String serialNumber) {
//        ErrorLog demo = acclr.findBySerialNumber(serialNumber);
//        if (demo != null) {
//            acclr.deleteById(serialNumber); //???
//        }
//        return result(ExceptionMsg.SUCCESS);
//        //return new ResponseData(ExceptionMsg.SUCCESS,"");
//    }
}
