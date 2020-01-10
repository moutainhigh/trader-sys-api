package com.zgkj.api.shops.aliExpress.entity;

import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
public class Order {
    private String site;
    private String out_order_no;
    private String remark;
    private Double carriage_amount;
    private Double amount;
    private String merchant_store_platform_code;
    private Integer merchant_store_id;
    private Integer express_type;
    private String client_selected_logistics;
    private Integer import_order_source_type;
    private Double refunding_amount;
    private Integer order_import_record_order_id;
    private String gmt_created;
    private String gmt_updated;
    private String gmt_finished;
    private String items_design_status;
    private Address address;
    private List<Goods> items;
}
