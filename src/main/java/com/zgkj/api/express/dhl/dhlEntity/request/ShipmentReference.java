package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("ShipmentReference")
public class ShipmentReference {
    @XStreamAlias("ShipmentReference")
    private String ShipmentReference;
    @XStreamAlias("ShipmentReferenceType")
    private String ShipmentReferenceType;
}
