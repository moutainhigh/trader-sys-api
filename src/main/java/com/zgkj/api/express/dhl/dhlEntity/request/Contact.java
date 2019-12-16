package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Contact")
public class Contact {
    @XStreamAlias("PersonName")
    private String PersonName;
    @XStreamAlias("CompanyName")
    private String CompanyName;
    @XStreamAlias("PhoneNumber")
    private String PhoneNumber;
    @XStreamAlias("EmailAddress")
    private String EmailAddress;
    @XStreamAlias("MobilePhoneNumber")
    private String MobilePhoneNumber;
}
