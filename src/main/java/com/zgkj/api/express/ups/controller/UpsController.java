package com.zgkj.api.express.ups.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.api.express.dhl.dhlEntity.PdfInfo;
import com.zgkj.api.express.ups.entity.request.*;
import com.zgkj.api.express.ups.entity.request.Package;
import com.zgkj.api.express.ups.entity.response.UpsResponseBody;
import com.zgkj.api.trader.entity.OrderInfo;
import com.zgkj.api.trader.entity.OutboundInfo;
import com.zgkj.api.trader.entity.RegionInfo;
import com.zgkj.api.trader.service.*;
import com.zgkj.util.DateUtils;
import com.zgkj.util.HttpUtil;
import com.zgkj.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/7 13:57
 */
@Slf4j
@RestController
@RequestMapping("ups")
public class UpsController {

    private final static String USERNAME="zixuntech.api";
    private final static String PASSWORD="Zixun7AW697!";
    private final static String ACCESS_LICENSE_NUMBER="ED72F766E6935ED2";
    private final static String REQUEST_OPTION="nonvalidate";
    private final static String SUBVERSION="1807";
    private final static String UPS_API="https://wwwcie.ups.com/ship/1807/shipments";
    private static final String PDF_SAVE_API="http://localhost:8080/NewTO/SavePDF.jsp";
    private static final String SUCCESS="success";

    @Autowired
    IOrderInfoService orderInfoService;
    @Autowired
    IOutboundInfoService outboundInfoService;
    @Autowired
    IRegionInfoService regionInfoService;
    @Autowired
    IProductClassService productClassService;
    @Autowired
    IOrderExpressInfoService orderExpressInfoService;
    @Autowired
    IOrderRecordService orderRecordService;

    @ResponseBody
    @RequestMapping("makeExpress")
    public String makeExpress(String orderId,Double weight,Integer userId, HttpServletRequest request){
        Map<String,String> authMap=new LinkedHashMap<>();
        authMap.put("Username",USERNAME);
        authMap.put("Password",PASSWORD);
        authMap.put("AccessLicenseNumber",ACCESS_LICENSE_NUMBER);
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("OrderID",orderId);
        OrderInfo orderInfo=orderInfoService.getOne(wrapper);
        QueryWrapper<OutboundInfo> outboundInfoWrapper=new QueryWrapper<>();
        if (null!=orderInfo){
            UpsSecurity upsSecurity=makeUpsSecurity();
            ShipmentRequest shipmentRequest=new ShipmentRequest();
            shipmentRequest.setRequest(makeRequest());
            Shipment shipment=new Shipment();
            shipment.setShipper(makeShipper());
            shipment.setShipTo(makeShipTo(orderInfo));
            shipment.setPaymentInformation(makePaymentInformation());
            shipment.setDescription(orderInfo.getOrderID());
            Service service=new Service();
            if (weight<1){
                service.setCode("M4");
                shipment.setUSPSEndorsement("1");
                shipment.setPackageID(orderId.replace("-",""));
                shipment.setCostCenter(orderInfo.getId().toString());
            }else if (1<=weight&&weight<=9){
                service.setCode("93");
            }else {
                service.setCode("03");
            }
            shipment.setService(service);
            List<Package> packages=new ArrayList<>();
            packages.add(makePackage(weight));
            shipment.setPackage(packages);
            ShipmentRatingOptions shipmentRatingOptions=new ShipmentRatingOptions();
            shipmentRatingOptions.setNegotiatedRatesIndicator("");
            shipment.setShipmentRatingOptions(shipmentRatingOptions);
            LabelSpecification labelSpecification=new LabelSpecification();
            LabelImageFormat labelImageFormat=new LabelImageFormat();
            labelImageFormat.setCode("GIF");
            labelSpecification.setLabelImageFormat(labelImageFormat);
            shipmentRequest.setShipment(shipment);
            shipmentRequest.setLabelSpecification(labelSpecification);
            UpsRequestBody upsRequestBody=new UpsRequestBody();
            upsRequestBody.setUPSSecurity(upsSecurity);
            upsRequestBody.setShipmentRequest(shipmentRequest);
            String json=JSONObject.toJSON(upsRequestBody).toString();
            JSONObject jsonObject=JSON.parseObject(json);
            String result=HttpUtil.sendJsonPost(UPS_API,jsonObject,authMap);
            UpsResponseBody upsResponseBody= JSON.parseObject(result,UpsResponseBody.class);
            try {
                PdfInfo pdfInfo=new PdfInfo();
                pdfInfo.setTrackingNumber(upsResponseBody.getShipmentResponse().getShipmentResults().getPackageResults().getTrackingNumber());
                pdfInfo.setGraphicImage(upsResponseBody.getShipmentResponse().getShipmentResults().getPackageResults().getShippingLabel().getGraphicImage());
                String re= HttpUtil.sendJsonPost(PDF_SAVE_API, JSON.parseObject(JsonUtil.objToJson(pdfInfo)),null);
                if (SUCCESS.equals(re)){
                    Boolean flag=orderExpressInfoService.changeExpress(orderId,pdfInfo.getTrackingNumber(),"UPS",20,userId,request);
                    if (!flag){
                        return "failed";
                    }
                }else {
                    return "failed";
                }
            }catch (Exception e){
                log.error("----------------------------返回错误,请求如下-------------------------");
                log.error(json);
                log.error("----------------------------返回错误,结果如下-------------------------");
                log.info("结果:"+result);
                return "failed";
            }
        }else {
            return "could not find order";
        }
        return "success";
    }

    private UpsSecurity makeUpsSecurity(){
        UpsSecurity upsSecurity=new UpsSecurity();
        UsernameToken usernameToken=new UsernameToken();
        usernameToken.setUsername(USERNAME);
        usernameToken.setPassword(PASSWORD);
        upsSecurity.setUsernameToken(usernameToken);
        ServiceAccessToken serviceAccessToken=new ServiceAccessToken();
        serviceAccessToken.setAccessLicenseNumber(ACCESS_LICENSE_NUMBER);
        upsSecurity.setServiceAccessToken(serviceAccessToken);
        return upsSecurity;
    }
    private Request makeRequest(){
        Request request=new Request();
        request.setRequestOption(REQUEST_OPTION);
        request.setSubVersion(SUBVERSION);
        return request;
    }
    private Shipper makeShipper(){
        Shipper shipper=new Shipper();
        shipper.setName("ZiXun Tech");
        shipper.setAttentionName("ZiXun Tech");
        shipper.setShipperNumber("7AW697");
        Phone phone=new Phone();
        phone.setNumber("+86 18950099841");
        shipper.setPhone(phone);
        UpsAddress address=new UpsAddress();
        String[] street={"55 MALL DRIVE 808"};
        address.setAddressLine(street);
        address.setCity("COMMACK");
        address.setStateProvinceCode("NY");
        address.setPostalCode("11725-5703");
        address.setCountryCode("US");
        shipper.setAddress(address);
        return shipper;
    }
    private Shipper makeShipTo(OrderInfo orderInfo){
        Shipper shipper=new Shipper();
        shipper.setName(orderInfo.getAddressee());
        shipper.setAttentionName(orderInfo.getAddressee());
        Phone phone=new Phone();
        if (orderInfo.getPhone().contains("ext")){
            phone.setNumber(orderInfo.getPhone().split("ext")[0]);
        }else {
            phone.setNumber(orderInfo.getPhone());
        }
        shipper.setPhone(phone);
        UpsAddress address=new UpsAddress();
        String[] street=new String[3];
        String[] addr = orderInfo.getAddress().split(" ");
        String addr1="";
        String addr2="";
        if (orderInfo.getAddress().length()>35){
            for (int i=0;i<addr.length;i++){
                if ((addr1+" "+addr[i]).length()<=35&&"".equals(addr2)){
                    addr1+=" "+addr[i];
                }else {
                    addr2+=" "+addr[i];
                }
            }
            street[0]=addr1;
            street[1]=addr2;
        }else {
            street[0]=orderInfo.getAddress();
        }
        address.setAddressLine(street);
        address.setCity(orderInfo.getCity());
        QueryWrapper<RegionInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("RegionEghName",orderInfo.getProvince());
        RegionInfo regionInfo=regionInfoService.list(wrapper).get(0);
        address.setStateProvinceCode(regionInfo.getRegionAbbr());
        address.setPostalCode(orderInfo.getZipCode());
        wrapper=new QueryWrapper<>();
        wrapper.eq("RegionEghName",orderInfo.getCountry());
        wrapper.eq("ParentID",0);
        regionInfo=regionInfoService.list(wrapper).get(0);
        address.setCountryCode(regionInfo.getRegionAbbr());
        shipper.setAddress(address);
        return shipper;
    }
    private PaymentInformation makePaymentInformation(){
        ShipmentCharge shipmentCharge=new ShipmentCharge();
        shipmentCharge.setType("01");
        BillShipper billShipper=new BillShipper();
        billShipper.setAccountNumber("7AW697");
        shipmentCharge.setBillShipper(billShipper);
        PaymentInformation paymentInformation=new PaymentInformation();
        paymentInformation.setShipmentCharge(shipmentCharge);
        return paymentInformation;
    }
    private Package makePackage(Double weight){
        Package pa=new Package();
        Packaging packaging=new Packaging();
        UnitOfMeasurement unitOfMeasurement=new UnitOfMeasurement();
        if (weight<1){
            packaging.setCode("62");
            unitOfMeasurement.setCode("OZS");
            weight=weight*16;
        }else {
            packaging.setCode("02");
            unitOfMeasurement.setCode("LBS");
        }
        PackageWeight packageWeight=new PackageWeight();
        packageWeight.setUnitOfMeasurement(unitOfMeasurement);
        packageWeight.setWeight(weight.toString());
        pa.setPackaging(packaging);
        pa.setPackageWeight(packageWeight);
        return pa;
    }
}
