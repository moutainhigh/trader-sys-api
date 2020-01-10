package com.zgkj.api.express.dhl.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.util.*;
import com.zgkj.api.express.dhl.dhlEntity.PdfInfo;
import com.zgkj.api.express.dhl.dhlEntity.request.*;
import com.zgkj.api.express.dhl.dhlEntity.response.HdlResultBean;
import com.zgkj.api.trader.entity.*;
import com.zgkj.api.trader.service.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("hdl")
public class HdlController {
    private static final String SUCCESS="success";
    private static final String USER_NAME="micromediacCN";
    private static final String PASSWORD="O$3lH$2aR!6n";
    private static final String SHIPPER_ACCOUNT_NUMBER="604743457";
    private static final String QUANTITY_UNIT_OF_MEASUREMENT="PCS";
    private static final String PDF_SAVE_API="http://161.189.12.212:8012/NewTO/SavePDF.jsp";
    private static final String HDL_API_URL="https://wsbexpress.dhl.com/gbl/expressRateBook";
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
                Double value=0.1;
                if (Double.valueOf(df.format(we))!=0.00){
                    value = Double.valueOf(df.format(we));
                }
                exportLineItem.setNetWeight(value);
                exportLineItem.setGrossWeight(value);
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
            Request request1=makeRequest();
            shipmentRequest.setRequest(request1);
            shipmentRequest.setRequestedShipment(requestedShipment);
            Body body=new Body();
            body.setShipmentRequest(shipmentRequest);
            xml+= XsteamUtil.toXml(body,"number","number",RequestedPackages.class)+"\n</soapenv:Envelope>";
            //log.info("请求:"+xml);
            String result=HttpUtil.sendXmlPost(HDL_API_URL,xml);
            HdlResultBean bean= (HdlResultBean) XsteamUtil.toModel(result,HdlResultBean.class);
            try {
                PdfInfo pdfInfo=new PdfInfo();
                pdfInfo.setGraphicImage(bean.getBody().getShipmentResponse().getLabelImage().getGraphicImage());
                pdfInfo.setDocumentImage(bean.getBody().getShipmentResponse().getDocuments().get(0).getDocumentImage());
                pdfInfo.setTrackingNumber(bean.getBody().getShipmentResponse().getPackagesResult().getPackageResult().get(0).getTrackingNumber());
                String re= HttpUtil.sendJsonPost(PDF_SAVE_API, JSON.parseObject(JsonUtil.objToJson(pdfInfo)),null);
                if (SUCCESS.equals(re)){
                    Boolean flag=orderExpressInfoService.changeExpress(orderId,pdfInfo.getTrackingNumber(),"DHL",22,userId,request);
                if (!flag){
                    return "failed";
                }
                return "success";
                }else {

                    return "failed";
                }
            }catch (Exception e){
                log.error("----------------------------返回错误,请求如下-------------------------");
                log.error(xml);
                log.error("----------------------------返回错误,结果如下-------------------------");
                log.info("结果:"+result);
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
        if (orderInfo.getAddress().length()>45){
            String[] addr=orderInfo.getAddress().split(" ");
            Integer size=addr.length;
            String add1="";
            String add2="";
            for (int i =0;i<size;i++){
                if ((add1+addr[i]+" ").length()<45&&"".equals(add2)){
                    add1+=addr[i]+" ";
                }else {
                    add2+=addr[i]+" ";
                }
            }
            address.setStreetLines(add1);
            if (!"".equals(add2)){
                address.setStreetLines2(add2);
            }
        }else {
            address.setStreetLines(orderInfo.getAddress());
        }
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
        dimensions.setLength(length<0.5?0.5:length);
        dimensions.setWidth(width<0.5?0.5:width);
        dimensions.setHeight(height<0.5?0.5:height);
        requestedPackages.setDimensions(dimensions);
        requestedPackages.setNumber(1);
        List<RequestedPackages> list=new ArrayList<>();
        list.add(requestedPackages);
        packages.setRequestedPackages(list);
        return packages;
    }

    private Request makeRequest(){
        Request request=new Request();
        ServiceHeader serviceHeader=new ServiceHeader();
        serviceHeader.setMessageTime(DateUtils.getGMTDateTimeNow());
        serviceHeader.setMessageReference(RandomCodeUtil.getRandomString(30));
        serviceHeader.setWebstorePlatform("Amzaone");
        serviceHeader.setWebstorePlatformVersion("1");
        serviceHeader.setShippingSystemPlatform("ZiGuangExpressSys");
        serviceHeader.setShippingSystemPlatformVersion("1");
        request.setServiceHeader(serviceHeader);
        return request;
    }
}
