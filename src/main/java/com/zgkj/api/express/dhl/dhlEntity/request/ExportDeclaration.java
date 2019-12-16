package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
@XStreamAlias("ExportDeclaration")
public class ExportDeclaration {
    @XStreamAlias("ExportLineItems")
    private List<ExportLineItem> exportLineItems;
    @XStreamAlias("InvoiceDate")
    private String InvoiceDate;
    @XStreamAlias("InvoiceNumber")
    private String InvoiceNumber;
}
