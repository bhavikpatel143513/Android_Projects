package com.bhavikpatel.recipe12passingdatabetweenactivities;

import java.io.Serializable;

/**
 * Created by BHAVIK PATEL on 08-Jun-17.
 */

public class MyCustom implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
