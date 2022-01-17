package com.pestmonitors.pestmonitors.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class SportDTO {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String description;
}
