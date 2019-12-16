package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;



@Data
@XStreamAlias("SOAP-ENV:Envelope")
public class HdlResultBean {
    @XStreamAlias("SOAP-ENV:Body")
    private Body body;



}
