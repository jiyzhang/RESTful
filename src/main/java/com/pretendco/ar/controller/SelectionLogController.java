package com.pretendco.ar.controller;

import com.pretendco.ar.entity.SelectionLog;
import com.pretendco.ar.entity.SelectionLogId;
import com.pretendco.ar.repository.SelectionLogRepository;
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
    @RequestMapping(value = "/{SerialNumber}", method = RequestMethod.GET)
    public ResponseData getList(@PathVariable("SerialNumber") String serialNumber) throws IOException {
        List<SelectionLog> list = new ArrayList<SelectionLog>(slr.findBySerialNumber(serialNumber));
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseData add(SelectionLog model) {
//        SelectionLogId id = new SelectionLogId(model.
//        acclr.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }
    public ResponseData add(@RequestParam("SerialNumber") String sn,
                            @RequestParam("SessionID") String sessid,
                            @RequestParam("SelectedWatchSeries") String sws,
                            @RequestParam("SelectedWatchSize") String swz,
                            @RequestParam("SelectedWatchCase") String swc,
                            @RequestParam("SelectedWatchBand") String swb
    ) {

        SelectionLogId id = new SelectionLogId();
        id.setSerialNumber(sn);
        id.setSessionID(sessid);

        SelectionLog model = new SelectionLog();
        model.setId(id);
        model.setSelectedWatchSeries(sws);
        model.setSelectedWatchSize(swz);
        model.setSelectedWatchCase(swc);
        model.setSelectedWatchBand(swb);

        slr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOne, 全部修改
    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.PUT)
    public ResponseData update(SelectionLog model) {
        slr.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

//    //deleteOne
//    @RequestMapping(value = "/{serialNumber}", method = RequestMethod.DELETE)
//    public Response delete(@PathVariable("serialNumber") String serialNumber) {
//        SelectionLog demo = acclr.findBySerialNumber(serialNumber);
//        if (demo != null) {
//            acclr.deleteById(serialNumber); //???
//        }
//        return result(ExceptionMsg.SUCCESS);
//        //return new ResponseData(ExceptionMsg.SUCCESS,"");
//    }
}

