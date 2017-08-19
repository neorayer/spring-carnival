package com.demo.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resources;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class RootController extends BaseController<RootController>{

    @GetMapping("/api")
    public ResourceSupport root() {
       ResourceSupport resource = new ResourceSupport();
        {
            DeveloperController ctl = methodOn(DeveloperController.class);
            resource.add(linkTo(ctl.list()).withRel("developer.list"));
            resource.add(linkTo(ctl.searchByName(null)).withRel("developer.searchByName"));
            resource.add(linkTo(ctl.get(null)).withRel("developer.get"));
            resource.add(linkTo(ctl.create(null)).withRel("developer.create"));
        }

       return resource;
    }
}
