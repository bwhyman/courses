package com.se.courses.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class U {
    private String name;
    @JsonBackReference
    private Set<A> as;
}
