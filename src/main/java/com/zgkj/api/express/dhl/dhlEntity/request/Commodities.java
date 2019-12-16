package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("Commodities")
public class Commodities {
    @XStreamAlias("Description")
    private String Description;
    @XStreamAlias("CustomsValue")
    private String CustomsValue;
}
