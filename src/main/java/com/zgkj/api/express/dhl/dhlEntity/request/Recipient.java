package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Recipient")
public class Recipient {
    @XStreamAlias("Contact")
    private Contact contact;
    @XStreamAlias("Address")
    private Address address;
}
