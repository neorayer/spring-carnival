package com.demo.controller;

import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public abstract class BaseController<T extends BaseController> {

    protected final T dummy;

    protected BaseController() {
        dummy = (T) methodOn(this.getClass());
    }

    protected <R> Resources<Resource<R>> resources(List<R> content, Function<R, Resource<R>> wrap, LinkBuilder selfLinkBuilder) {
        List<Resource<R>> resourceList = content.stream().map(wrap).collect(Collectors.toList());
        return new Resources(resourceList, selfLinkBuilder.withSelfRel());
    }

}
