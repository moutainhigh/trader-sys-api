package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("ExportLineItem")
public class ExportLineItem {
    @XStreamAlias("ExportReasonType")
    private String ExportReasonType;
    @XStreamAlias("ItemNumber")
    private Integer ItemNumber;
    @XStreamAlias("Quantity")
    private Integer Quantity;
    @XStreamAlias("QuantityUnitOfMeasurement")
    private String QuantityUnitOfMeasurement;
    @XStreamAlias("ItemDescription")
    private String ItemDescription;
    @XStreamAlias("UnitPrice")
    private Integer UnitPrice;
    @XStreamAlias("NetWeight")
    private Double NetWeight;
    @XStreamAlias("GrossWeight")
    private Double GrossWeight;
}
