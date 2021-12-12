package com.pretendco.ar.controller;

import com.pretendco.ar.entity.ARDemo;
import com.pretendco.ar.repository.ARDemoRepository;
import com.pretendco.ar.result.ExceptionMsg;
import com.pretendco.ar.result.Response;
import com.pretendco.ar.result.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("ardemo")
public class ARDemoController {

    protected Response result(ExceptionMsg msg) {
        return new Response(msg);
    }
    protected Response result() {
        return new Response();
    }

    @Autowired
    private ARDemoRepository ARDemoRepository;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    //GetAll
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseData getList() {
        List<ARDemo> list = new ArrayList<ARDemo>(ARDemoRepository.findAll());
        return new ResponseData(ExceptionMsg.SUCCESS, list);
    }

    //GetOne
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseData find(@PathVariable("id") Integer id) throws IOException {
        ARDemo demo = ARDemoRepository.findById(id);
        if (demo != null) {
            return new ResponseData(ExceptionMsg.SUCCESS, demo);
        }
        return new ResponseData(ExceptionMsg.FAILED, demo);
    }

    //insert
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseData add(ARDemo model) {
        ARDemoRepository.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOne, 全部修改
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseData update(ARDemo model) {
        ARDemoRepository.save(model);
        return new ResponseData(ExceptionMsg.SUCCESS, model);
    }

    //UpdateOne, 只修改提交数据
//    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
//    public ResponseData patch(Arwatch model) {
//        arwatchRepository.
//        arwatchRepository.save(model);
//        return new ResponseData(ExceptionMsg.SUCCESS, model);
//    }
//    https://stackoverflow.com/questions/29988841/spring-rest-and-patch-method/55595016
//    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
//    public ResponseData patch(@PathVariable long id, @RequestBody Map<String, Object> fields) {
//
//        // Sanitize and validate the data
//        if (id <= 0 || fields == null || fields.isEmpty() || !fields.get("claimId").equals(claimId)){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Invalid claim object received or invalid id or id does not match object
//        }
//
//        Claim claim = claimService.get(claimId);
//
//        // Does the object exist?
//        if( claim == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Claim object does not exist
//        }
//
//        // Remove id from request, we don't ever want to change the id.
//        // This is not necessary, you can just do it to save time on the reflection
//        // loop used below since we checked the id above
//        fields.remove("claimId");
//
//        fields.forEach((k, v) -> {
//            // use reflection to get field k on object and set it to value v
//            // Change Claim.class to whatver your object is: Object.class
//            Field field = ReflectionUtils.findField(Claim.class, k); // find field in the object class
//            field.setAccessible(true);
//            ReflectionUtils.setField(field, claim, v); // set given field for defined object to value V
//        });
//
//        claimService.saveOrUpdate(claim);
//        return new ResponseEntity<>(claim, HttpStatus.OK);
//    }

    //deleteOne
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable("id") long id) {
        ARDemo demo = ARDemoRepository.findById(id);
        if (demo != null) {
            ARDemoRepository.deleteById(id);
        }
        return result(ExceptionMsg.SUCCESS);
        //return new ResponseData(ExceptionMsg.SUCCESS,"");
    }

    //查
    @RequestMapping(value = "/re/{id}", method = RequestMethod.GET)
    public ARDemo find2(@PathVariable("id") Integer id) throws IOException {
        RestTemplate client = restTemplateBuilder.build();
        String uri = "http://82.157.47.61:8080/arwatch/" + id;
        System.out.println(uri);
        ARDemo ARDemo = client.getForObject(uri, ARDemo.class, id);

        return ARDemo;
    }
}


