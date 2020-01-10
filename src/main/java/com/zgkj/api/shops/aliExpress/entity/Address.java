package com.zgkj.api.shops.aliExpress.entity;

import lombok.Data;

/**
 * @author user
 */
@Data
public class Address {
    private String consignee;
    private String country;
    private String province;
    private String city;
    private String postcode;
    private String detail;
    private String mobile_phone;
    private String province_code;
    private String city_code;
    private String gmt_created;
}
