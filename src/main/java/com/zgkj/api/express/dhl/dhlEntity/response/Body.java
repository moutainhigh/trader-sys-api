package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("SOAP-ENV:Body")
public class Body {
    @XStreamAlias("shipresp:ShipmentResponse")
    private ShipmentResponse shipmentResponse;
}
