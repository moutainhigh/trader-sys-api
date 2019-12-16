package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Request")
public class Request {
    @XStreamAlias("ServiceHeader")
    private ServiceHeader serviceHeader;
}
