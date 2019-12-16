package com.zgkj.api.express.dhl.dhlEntity.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author user
 */
@Data
@XStreamAlias("ServiceHeader")
public class ServiceHeader {
    @XStreamAlias("MessageTime")
    private String MessageTime;
    @XStreamAlias("MessageReference")
    private String MessageReference;
    @XStreamAlias("WebstorePlatform")
    private String WebstorePlatform;
    @XStreamAlias("WebstorePlatformVersion")
    private String WebstorePlatformVersion;
    @XStreamAlias("ShippingSystemPlatform")
    private String ShippingSystemPlatform;
    @XStreamAlias("ShippingSystemPlatformVersion")
    private String ShippingSystemPlatformVersion;
    @XStreamAlias("PlugIn")
    private String PlugIn;
    @XStreamAlias("PlugInVersion")
    private String PlugInVersion;
}
