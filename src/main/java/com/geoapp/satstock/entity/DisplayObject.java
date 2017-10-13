package com.geoapp.satstock.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Data
@Accessors
@Entity
public class DisplayObject {
    private String sometext;
    private String someOtherText;
    private String someotherlongtext;
    private String somecaption;
}
