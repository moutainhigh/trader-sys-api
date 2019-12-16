package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
@XStreamAlias("PackagesResult")
public class PackagesResult {
    @XStreamImplicit
    @XStreamAlias("PackageResult")
    private List<PackageResult> packageResult;
}
