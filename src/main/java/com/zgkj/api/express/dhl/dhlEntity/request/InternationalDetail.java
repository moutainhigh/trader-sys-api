package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("InternationalDetail")
public class InternationalDetail {
    @XStreamAlias("Commodities")
    private Commodities commodities;
    @XStreamAlias("Content")
    private String Content;
    @XStreamAlias("ExportDeclaration")
    private ExportDeclaration exportDeclaration;
}
