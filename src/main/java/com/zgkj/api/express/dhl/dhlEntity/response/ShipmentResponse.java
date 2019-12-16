package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("shipresp:ShipmentResponse")
public class ShipmentResponse {
    @XStreamAlias("PackagesResult")
    private PackagesResult packagesResult;
    @XStreamAlias("LabelImage")
    private LabelImage labelImage;
    @XStreamAlias("Documents")
    private List<Document> documents;
    @XStreamAlias("ShipmentIdentificationNumber")
    private String shipmentIdentificationNumber;
}
