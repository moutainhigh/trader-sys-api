package com.zgkj.api.express.ups.entity.request;

import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/8 11:03
 */
@Data
public class InvoiceLineTotal {
    private String CurrencyCode;
    private String MonetaryValue;
}
