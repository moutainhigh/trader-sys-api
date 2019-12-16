package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("RequestedPackages")
public class RequestedPackages {
    @XStreamAlias("Weight")
    private String Weight;
    @XStreamAlias("CustomerReferences")
    private String CustomerReferences;
    @XStreamAlias("CustomerReferenceType")
    private String CustomerReferenceType;
    @XStreamAlias("Dimensions")
    private Dimensions dimensions;
    private Integer number;
}
