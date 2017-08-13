package com.demo.controller;

import lombok.Data;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/developers")
public class DeveloperController extends BaseController<DeveloperController> {

    private List<Developer> allDevelopers = new ArrayList<>();

    public DeveloperController() {
        allDevelopers.add(new Developer(0L, "Wolfgang Amadeus Mozart"));
        allDevelopers.add(new Developer(1L, "Leonardo da Vinci"));
        allDevelopers.add(new Developer(2L, "Linus Torvalds"));
    }

    @GetMapping("")
    public Resources<Resource<Developer>> list() {
        List<Developer> developers = this.allDevelopers;

        Resources resources = resources(developers, this::wrap, linkTo(dummy.list()));
        resources.add(linkTo(dummy.searchByName(null)).withRel("searchByName"));
        return resources;
    }

    @GetMapping(path = "/search", params = {"name"})
    public Resources<Resource<Developer>> searchByName(@RequestParam String name) {
        List<Developer> developers = this.allDevelopers.stream()
                .filter((developer -> developer.getFullName().contains(name)))
                .collect(Collectors.toList());

        Resources resources = resources(developers, this::wrap, linkTo(dummy.searchByName(name)));
        return resources;
    }

    @GetMapping("/{id}")
    public Resource<Developer> get(@PathVariable Long id) {
        Developer developer = allDevelopers.get(id.intValue());

        return wrap(developer);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public Resource<Developer> create(@RequestBody Developer developer) {
        allDevelopers.add(developer);

        return wrap(developer);
    }

    protected Resource<Developer> wrap(Developer developer) {
        return new Resource(developer, linkTo(dummy.get(developer.getId())).withSelfRel());
    }


}

@Data
class Developer {
    private final Long id;

    private final String fullName;

    private String email;

    private String country;
}
