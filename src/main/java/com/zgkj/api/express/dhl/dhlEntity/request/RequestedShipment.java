package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("RequestedShipment")
public class RequestedShipment {
    @XStreamAlias("ShipmentInfo")
    private ShipmentInfo shipmentInfo;
    @XStreamAlias("ShipTimestamp")
    private String ShipTimestamp;
    @XStreamAlias("PaymentInfo")
    private String PaymentInfo;
    @XStreamAlias("InternationalDetail")
    private InternationalDetail internationalDetail;
    @XStreamAlias("Ship")
    private Ship ship;
    @XStreamAlias("Packages")
    private Packages packages;
}
