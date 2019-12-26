package com.zgkj.api.express.aliExpress.entity;

import lombok.Data;

import java.util.List;

/**
 * @author user
 */
@Data
public class Goods {
    private String imgs;
    private String product_name;
    private String product_size;
    private Integer num;
    private Double amount;
    private String product_sku;
    private Double product_unit_price;
    private Integer import_item_id;
    private String product_color;
    private List<String> img_urls;
}
