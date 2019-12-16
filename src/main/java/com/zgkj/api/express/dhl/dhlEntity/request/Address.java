package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("Address")
public class Address {
    @XStreamAlias("StreetLines")
    private String StreetLines;
    @XStreamAlias("StreetLines2")
    private String StreetLines2;
    @XStreamAlias("StreetLines3")
    private String StreetLines3;
    @XStreamAlias("City")
    private String City;
    @XStreamAlias("StateOrProvinceCode")
    private String StateOrProvinceCode;
    @XStreamAlias("PostalCode")
    private String PostalCode;
    @XStreamAlias("CountryCode")
    private String CountryCode;
    @XStreamAlias("Suburb")
    private String Suburb;
}
