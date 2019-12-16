package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
@XStreamAlias("Packages")
public class Packages {
    @XStreamImplicit
    @XStreamAlias("RequestedPackages")
    private List<RequestedPackages> requestedPackages;
}
