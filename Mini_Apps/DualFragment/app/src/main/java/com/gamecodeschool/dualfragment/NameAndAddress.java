package com.gamecodeschool.dualfragment;

import java.io.Serializable;

/**
 * Created by BHAVIK PATEL on 17-May-17.
 */

public class NameAndAddress implements Serializable {
    private String mName;
    private String mAddress1;
    private String mAddress2;
    private String mZipCode;

    public String getZipCode() {
        return mZipCode;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public String getAddress1() {
        return mAddress1;
    }

    public String getNamee() {
        return mName;
    }

    public NameAndAddress(String name , String address1 , String address2 , String zipCode ){
        mName = name;
        mAddress1 = address1;
        mAddress2 = address2;
        mZipCode = zipCode;

    }
}
