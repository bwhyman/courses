package com.se.courses.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class A {

    private String name;
    @JsonManagedReference
    private U u;
}
