package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Shipper")
public class Shipper {
    @XStreamAlias("Contact")
    private Contact contact;
    @XStreamAlias("Address")
    private Address address;
}
