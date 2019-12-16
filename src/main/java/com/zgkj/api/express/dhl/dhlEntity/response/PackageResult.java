package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("PackageResult")
public class PackageResult {
    @XStreamAlias("TrackingNumber")
    private String TrackingNumber;
}
