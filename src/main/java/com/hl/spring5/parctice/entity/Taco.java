package com.hl.spring5.parctice.entity;


import lombok.Data;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min = 5, max = 10, message = "Name length must between 5 and 10")
    private String name;

    @NotNull
    @Size(min = 1, message = "you must choose at least 1 ingredient")
    private List<String> ingredients;
}
