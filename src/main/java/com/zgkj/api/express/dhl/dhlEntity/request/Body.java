package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("soapenv:Body")
public class Body {
    @XStreamAlias("ship:ShipmentRequest")
    private ShipmentRequest shipmentRequest;
}
