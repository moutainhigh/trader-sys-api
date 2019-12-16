package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("Ship")
public class Ship {
    @XStreamAlias("Shipper")
    private Shipper shipper;
    @XStreamAlias("Recipient")
    private Recipient recipient;
}
