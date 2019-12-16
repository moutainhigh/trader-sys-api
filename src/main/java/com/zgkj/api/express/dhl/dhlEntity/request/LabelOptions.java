package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("LabelOptions")
public class LabelOptions {
    @XStreamAlias("PrinterDPI")
    private String PrinterDPI;
    @XStreamAlias("RequestWaybillDocument")
    private String RequestWaybillDocument;
    @XStreamAlias("HideAccountInWaybillDocument")
    private String HideAccountInWaybillDocument;
    @XStreamAlias("NumberOfWaybillDocumentCopies")
    private String NumberOfWaybillDocumentCopies;
    @XStreamAlias("RequestDHLCustomsInvoice")
    private String RequestDHLCustomsInvoice;
}
