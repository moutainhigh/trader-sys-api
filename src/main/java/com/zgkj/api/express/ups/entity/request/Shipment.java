package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:42
 */
@Data
public class Shipment {
    @JSONField(name = "Description")
    private String Description;
    @JSONField(name = "Shipper")
    private Shipper Shipper;
    @JSONField(name = "ShipTo")
    private Shipper ShipTo;
    @JSONField(name = "PaymentInformation")
    private PaymentInformation PaymentInformation;
    @JSONField(name = "Service")
    private Service Service;
    @JSONField(name = "Package")
    private List<Package> Package;
    @JSONField(name = "ShipmentRatingOptions")
    private ShipmentRatingOptions ShipmentRatingOptions;
    @JSONField(name = "USPSEndorsement")
    private String USPSEndorsement;
    @JSONField(name = "PackageID")
    private String PackageID;
    @JSONField(name = "CostCenter")
    private String CostCenter;
    @JSONField(name = "InvoiceLineTotal")
    private InvoiceLineTotal InvoiceLineTotal;
}
