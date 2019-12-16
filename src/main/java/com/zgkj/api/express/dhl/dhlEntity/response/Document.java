package com.zgkj.api.express.dhl.dhlEntity.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("Document")
public class Document {
    @XStreamAlias("DocumentName")
    private String DocumentName;
    @XStreamAlias("DocumentFormat")
    private String DocumentFormat;
    @XStreamAlias("DocumentImage")
    private String DocumentImage;
}
