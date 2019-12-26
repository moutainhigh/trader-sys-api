package com.zgkj.api.express.dhl.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.util.*;
import com.zgkj.api.express.dhl.dhlEntity.PdfInfo;
import com.zgkj.api.express.dhl.dhlEntity.request.*;
import com.zgkj.api.express.dhl.dhlEntity.response.HdlResultBean;
import com.zgkj.api.trader.entity.*;
import com.zgkj.api.trader.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
@RestController
@RequestMapping("hdl")
public class HdlController {
    private static final String SUCCESS="success";
    private static final String USER_NAME="micromediacCN";
    private static final String PASSWORD="O$3lH$2aR!6n";
    private static final String SHIPPER_ACCOUNT_NUMBER="604743457";
    private static final String QUANTITY_UNIT_OF_MEASUREMENT="PCS";
    private static final String PDF_SAVE_API="http://localhost:8080/NewTO/SavePDF.jsp";
    private static final String HDL_API_URL="https://wsbexpress.dhl.com:443/sndpt/expressRateBook";
    private static final String XML_HEADER="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ship=\"http://scxgxtt.phx-dc.dhl.com/euExpressRateBook/ShipmentMsgRequest\">\n" +
            "   <soapenv:Header>\n" +
            "   \t\t<wsse:Security soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n" +
            "                    <wsse:UsernameToken>\n" +
            "                         <wsse:Username>"+USER_NAME+"</wsse:Username>\n" +
            "                  \t\t<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">"+PASSWORD+"</wsse:Password>\n" +
            "                \t</wsse:UsernameToken>\n" +
            "          </wsse:Security>\n" +
            "   </soapenv:Header>\n";

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
    public String makeExpress(String orderId, Double weight, Double length, Double width, Double height, Integer userId, HttpServletRequest request){
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("OrderID",orderId);
        OrderInfo orderInfo=orderInfoService.getOne(wrapper);
        QueryWrapper<OutboundInfo> outboundInfoQueryWrapper=new QueryWrapper<>();
        outboundInfoQueryWrapper.eq("SystemNumber",orderInfo.getSystemNumber());
        List<OutboundInfo> outboundInfoList=outboundInfoService.list(outboundInfoQueryWrapper);
        if (!StringUtils.isEmpty(orderInfo)){
            //int priceEach= (int) (price/outboundInfoList.size());
            int priceAll=0;
            int weightEach= (int) (weight/outboundInfoList.size());
            int weightAll=0;
            List<ExportLineItem> exportLineItems=new ArrayList<>();
            for (int i=0;i<outboundInfoList.size();i++){
                ExportLineItem exportLineItem=new ExportLineItem();
                exportLineItem.setItemNumber(i+1);
                exportLineItem.setQuantity(outboundInfoList.get(i).getOutboundNumder());
                exportLineItem.setQuantityUnitOfMeasurement(QUANTITY_UNIT_OF_MEASUREMENT);
                QueryWrapper<ProductClass> productClassQueryWrapper=new QueryWrapper<>();
                productClassQueryWrapper.eq("ClassifyName",outboundInfoList.get(i).getProductClass());
                String name=productClassService.getOne(productClassQueryWrapper).getClassEngName();
                exportLineItem.setItemDescription(null!=name?name:outboundInfoList.get(i).getProductClass());
                //int pr=priceEach/exportLineItem.getQuantity();
                int pr=2;
                exportLineItem.setUnitPrice(pr);
                priceAll+=pr*exportLineItem.getQuantity();
                Double we=Double.valueOf(weightEach)/exportLineItem.getQuantity();
                DecimalFormat df   = new DecimalFormat("######0.00");
                exportLineItem.setNetWeight(Double.valueOf(df.format(we)));
                exportLineItem.setGrossWeight(Double.valueOf(df.format(we)));
                exportLineItems.add(exportLineItem);
            }

            String xml=XML_HEADER;
            RequestedShipment requestedShipment=new RequestedShipment();

            requestedShipment.setShipmentInfo(setShipmentInfo(orderId));
            requestedShipment.setShipTimestamp(DateUtils.getGMTDateTimeLater(1));
            requestedShipment.setPaymentInfo("DAP");
            requestedShipment.setInternationalDetail(setInternationalDetail(orderInfo.getId().toString(),exportLineItems,priceAll));

            Ship ship=new Ship();
            ship.setShipper(setShipper());
            ship.setRecipient(setRecipient(orderInfo));
            requestedShipment.setShip(ship);
            requestedShipment.setPackages(setPackages(orderId,weight,length,width,height));

            ShipmentRequest shipmentRequest=new ShipmentRequest();
            shipmentRequest.setRequestedShipment(requestedShipment);
            Body body=new Body();
            body.setShipmentRequest(shipmentRequest);
            xml+= XsteamUtil.toXml(body,"number","number",RequestedPackages.class)+"\n</soapenv:Envelope>";
            //System.out.println(xml);
            String result=HttpUtil.sendXmlPost(HDL_API_URL,xml);
            HdlResultBean bean= (HdlResultBean) XsteamUtil.toModel(result,HdlResultBean.class);

            try {
                PdfInfo pdfInfo=new PdfInfo();
                pdfInfo.setGraphicImage(bean.getBody().getShipmentResponse().getLabelImage().getGraphicImage());
                pdfInfo.setDocumentImage(bean.getBody().getShipmentResponse().getDocuments().get(0).getDocumentImage());
                pdfInfo.setTrackingNumber(bean.getBody().getShipmentResponse().getPackagesResult().getPackageResult().get(0).getTrackingNumber());
                String re= HttpUtil.sendJsonPost(PDF_SAVE_API, JSON.parseObject(JsonUtil.objToJson(pdfInfo)));
                if (SUCCESS.equals(re)){
                    QueryWrapper<OrderExpressInfo> orderExpressInfoQueryWrapper=new QueryWrapper<>();
                    orderExpressInfoQueryWrapper.eq("OrderID",orderId);
                    OrderExpressInfo orderExpressInfo=orderExpressInfoService.getOne(orderExpressInfoQueryWrapper);
                    Boolean flag=false;
                    if (null!=orderExpressInfo){
                        orderExpressInfo.setTheExpressNo(pdfInfo.getTrackingNumber());
                        orderExpressInfo.setExpressage(22);
                        orderExpressInfo.setTypeOfShipping("DHL");
                        orderExpressInfo.setCreateDate(DateUtils.getTimeNow());
                        flag= orderExpressInfoService.update(orderExpressInfo,orderExpressInfoQueryWrapper);
                    }else {
                        orderExpressInfo=new OrderExpressInfo();
                        orderExpressInfo.setTheExpressNo(pdfInfo.getTrackingNumber());
                        orderExpressInfo.setExpressage(22);
                        orderExpressInfo.setTypeOfShipping("DHL");
                        orderExpressInfo.setOrderID(orderId);
                        orderExpressInfo.setCreateDate(DateUtils.getTimeNow());
                        flag= orderExpressInfoService.save(orderExpressInfo);
                    }
                    OrderRecord orderRecord=new OrderRecord();
                    orderRecord.setUserID(userId);
                    orderRecord.setOrderID(orderId);
                    orderRecord.setOperateContent("将运单号修改为: "+pdfInfo.getTrackingNumber());
                    orderRecord.setIp(IPUtil.getClientIP(request));
                    orderRecord.setStatus(0);
                    orderRecord.setCreateDate(DateUtils.getTimeNow());
                    orderRecordService.save(orderRecord);
                    if (!flag){
                        return "failed";
                    }
                    return "success";
                }else {

                    return "failed";
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("----------------------------返回错误,请求如下-------------------------");
                System.out.println(xml);
                return "failed";
            }

        }else {
            return "could not find order! ";
        }

    }

    private LabelOptions setLabelOptions(){
        LabelOptions labelOptions=new LabelOptions();
        labelOptions.setRequestWaybillDocument("Y");
        labelOptions.setHideAccountInWaybillDocument("N");
        labelOptions.setNumberOfWaybillDocumentCopies("1");
        labelOptions.setRequestDHLCustomsInvoice("Y");
        return labelOptions;
    }

    private ShipmentInfo setShipmentInfo(String orderId){
        ShipmentInfo shipmentInfo=new ShipmentInfo();
        shipmentInfo.setLabelTemplate("ECOM26_84_A4_001");
        shipmentInfo.setArchiveLabelTemplate("ARCH_8X4_A4_002");
        shipmentInfo.setDropOffType("REGULAR_PICKUP");
        shipmentInfo.setServiceType("P");
        Billing billing=new Billing();
        billing.setShippingPaymentType("S");
        billing.setShipperAccountNumber(SHIPPER_ACCOUNT_NUMBER);
        shipmentInfo.setBilling(billing);
        shipmentInfo.setCurrency("USD");
        shipmentInfo.setUnitOfMeasurement("SI");
        ShipmentReference shipmentReference=new ShipmentReference();
        shipmentReference.setShipmentReference(orderId);
        List<ShipmentReference> shipmentReferences=new ArrayList<>();
        shipmentReferences.add(shipmentReference);
        shipmentInfo.setShipmentReferences(shipmentReferences);
        shipmentInfo.setLabelOptions(setLabelOptions());
        return shipmentInfo;
    }

    private InternationalDetail setInternationalDetail(String orderId, List<ExportLineItem> exportLineItems,Integer priceAll){
        InternationalDetail internationalDetail = new InternationalDetail();
        Commodities commodities=new Commodities();
        commodities.setDescription(exportLineItems.get(0).getItemDescription());
        commodities.setCustomsValue(priceAll.toString());
        internationalDetail.setCommodities(commodities);
        internationalDetail.setContent("NON_DOCUMENTS");
        ExportDeclaration exportDeclaration=new ExportDeclaration();

        exportDeclaration.setExportLineItems(exportLineItems);
        exportDeclaration.setInvoiceDate(DateUtils.getDateNow());
        exportDeclaration.setInvoiceNumber(orderId);
        internationalDetail.setExportDeclaration(exportDeclaration);
        return internationalDetail;
    }

    private Shipper setShipper(){
        Address address=new Address();
        Contact contact=new Contact();
        Shipper shipper=new Shipper();
        contact.setPersonName("Zong");
        contact.setCompanyName("Q Logistics Park");
        contact.setEmailAddress("NO-Email@test.com");
        contact.setMobilePhoneNumber("+86 18950099841");
        contact.setPhoneNumber("+86 8683503067");
        shipper.setContact(contact);

        address.setStreetLines("NO.92 4th floor FangYang xi Road");
        address.setStreetLines2("Xiang'an district");
        address.setCity("Xiamen");
        address.setPostalCode("361101");
        address.setCountryCode("CN");
        shipper.setAddress(address);
        return shipper;
    }

    private Recipient setRecipient(OrderInfo orderInfo){
        Recipient recipient=new Recipient();
        Contact contact=new Contact();
        contact.setPersonName(orderInfo.getAddressee());
        contact.setCompanyName(orderInfo.getAddressee());
        contact.setEmailAddress(orderInfo.getEmail());
        contact.setPhoneNumber(orderInfo.getPhone().replace("-",""));
        recipient.setContact(contact);

        Address address=new Address();
        address.setStreetLines(orderInfo.getAddress());
        address.setCity(orderInfo.getCity());
        address.setPostalCode(orderInfo.getZipCode());
        QueryWrapper<RegionInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("RegionEghName",orderInfo.getCountry());
        address.setCountryCode(regionInfoService.getOne(wrapper).getRegionAbbr());
        recipient.setAddress(address);
        return recipient;
    }

    private Packages setPackages(String orderId,Double weight,Double length,Double width,Double height){
        Packages packages=new Packages();
        RequestedPackages requestedPackages=new RequestedPackages();
        requestedPackages.setWeight(weight+"");
        requestedPackages.setCustomerReferences(orderId);
        Dimensions dimensions=new Dimensions();
        dimensions.setLength(length);
        dimensions.setWidth(width);
        dimensions.setHeight(height);
        requestedPackages.setDimensions(dimensions);
        requestedPackages.setNumber(1);
        List<RequestedPackages> list=new ArrayList<>();
        list.add(requestedPackages);
        packages.setRequestedPackages(list);
        return packages;
    }
}
