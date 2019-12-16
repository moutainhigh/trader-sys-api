package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
@XStreamAlias("ShipmentInfo")
public class ShipmentInfo {
    @XStreamAlias("DropOffType")
    private String DropOffType;
    @XStreamAlias("ServiceType")
    private String ServiceType;
    @XStreamAlias("Billing")
    private Billing billing;
    @XStreamAlias("Currency")
    private String Currency;
    @XStreamAlias("UnitOfMeasurement")
    private String UnitOfMeasurement;
    @XStreamAlias("ShipmentReferences")
    private List<ShipmentReference> shipmentReferences;
    @XStreamAlias("LabelType")
    private String LabelType;
    @XStreamAlias("LabelTemplate")
    private String LabelTemplate;
    @XStreamAlias("ArchiveLabelTemplate")
    private String ArchiveLabelTemplate;
    @XStreamAlias("LabelOptions")
    private LabelOptions labelOptions;
}
