package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("LabelImage")
public class LabelImage {
    @XStreamAlias("LabelImageFormat")
    private String LabelImageFormat;
    @XStreamAlias("GraphicImage")
    private String GraphicImage;
}
