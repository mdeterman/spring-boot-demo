package io.determan.springboot.demo.service;

import io.determan.springboot.demo.endpoint.PostObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by mdeterman on 3/18/17.
 */
@Service
public class DemoService {

    private RestTemplate template;

    public DemoService(RestTemplateBuilder builder, @Value("${io.determan.demo.uri}") String uri) {
        this.template = builder.rootUri(uri).build();
    }

    // With Optional
    public Optional<HttpBinGetObject> get() {
        // Optional is new in Java8
        // https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
        return Optional.ofNullable(template.getForObject("/get", HttpBinGetObject.class));
    }

    // With Out Options
    public HttpBinPostObject post(PostObject object) {
        return template.postForObject("/post", object, HttpBinPostObject.class);
    }
}
