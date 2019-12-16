package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("Dimensions")
public class Dimensions {
    @XStreamAlias("Length")
    private Double Length;
    @XStreamAlias("Width")
    private Double Width;
    @XStreamAlias("Height")
    private Double Height;
}
