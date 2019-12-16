package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("ship:ShipmentRequest")
public class ShipmentRequest {
    @XStreamAlias("Request")
    private Request request;
    @XStreamAlias("RequestedShipment")
    private RequestedShipment requestedShipment;
}
