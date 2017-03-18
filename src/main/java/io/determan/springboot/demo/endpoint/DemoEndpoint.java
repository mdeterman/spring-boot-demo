package io.determan.springboot.demo.endpoint;

import io.determan.springboot.demo.service.DemoService;
import io.determan.springboot.demo.service.HttpBinGetObject;
import io.determan.springboot.demo.service.HttpBinPostObject;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by mdeterman on 3/18/17.
 */
@RestController
public class DemoEndpoint {

    @Autowired
    private DemoService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public HttpBinGetObject getRequest() throws Exception{
        Optional<HttpBinGetObject> optional = service.get();
        // NotFound::new is Lambda new in Java8
        // Lambda: https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        // Optional: https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
        return optional.orElseThrow(NotFound::new);
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<HttpBinPostObject> postRequest(@RequestBody PostObject object) throws Exception {
        HttpBinPostObject obj = service.post(object);
        if(obj == null) {
            throw new NotFound();
        }
        // you can return the object or ResponseEntity for more control
        // http://docs.spring.io/spring/docs/3.0.x/javadoc-api/org/springframework/http/ResponseEntity.html
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

}
