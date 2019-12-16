package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Billing")
public class Billing {
    @XStreamAlias("ShipperAccountNumber")
    private String ShipperAccountNumber;
    @XStreamAlias("ShippingPaymentType")
    private String ShippingPaymentType;
    @XStreamAlias("BillingAccountNumber")
    private String BillingAccountNumber;
}
