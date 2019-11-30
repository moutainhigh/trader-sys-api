package com.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /**
     *
     * @param data 数据
     * @param header 标题，若空，则使用mapKey作为标题
     * @param filename 导出的名字
     * @param response
     * @throws Exception
     */
    public static void exportExcel(List<Map<String,Object>> data,String[] header,String filename,HttpServletResponse response) throws Exception{

        //创建HSSFWorkbook对象(excel的文档对象)
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建XSSFCellStyle
        XSSFCellStyle cellStyle_red=wb.createCellStyle();
        XSSFCellStyle cellStyle_green=wb.createCellStyle();
        XSSFCellStyle cellStyle_row=wb.createCellStyle();
        //字体对象
        XSSFFont fontStyle=wb.createFont();
        fontStyle.setFontName("宋体");
        cellStyle_row.setFont(fontStyle);
        cellStyle_green.setFont(fontStyle);
        cellStyle_red.setFont(fontStyle);
        //设置填充方式(填充图案)
        cellStyle_red.setFillPattern(HSSFCellStyle.DIAMONDS);
        cellStyle_green.setFillPattern(HSSFCellStyle.DIAMONDS);
        //设置前景色
        cellStyle_red.setFillForegroundColor(HSSFColor.RED.index);
        cellStyle_green.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        //设置背景颜色
        cellStyle_red.setFillBackgroundColor(HSSFColor.RED.index);
        cellStyle_green.setFillBackgroundColor(HSSFColor.LIGHT_GREEN.index);
        //对齐居中样式
        cellStyle_red.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle_red.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle_green.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle_green.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle_row.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle_row.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //建立新的sheet对象（excel的表单）
        XSSFSheet sheet=wb.createSheet("Sheet1");

       //设置标题
        XSSFRow rowHeader=sheet.createRow(0);
        if (null!=header&&!"".equals(header)){
            for (int i =0;i<header.length;i++){
                rowHeader.createCell(i).setCellValue(header[i]);
                rowHeader.getCell(i).setCellStyle(cellStyle_green);
                //设置列宽
                sheet.setColumnWidth(i, 256 * 15);
            }
        }else {
            Map<String,Object> map=data.get(0);
            int j=0;
            for (Map.Entry<String,Object> entry:map.entrySet()){
                rowHeader.createCell(j).setCellValue(entry.getKey());
                rowHeader.getCell(j).setCellStyle(cellStyle_green);
                //设置列宽
                if (String.valueOf(entry.getValue()).length()>23){
                    sheet.setColumnWidth(j, 256 * 50);
                }else if (String.valueOf(entry.getValue()).length()<5){
                    sheet.setColumnWidth(j, 256 * 12);
                }else if (String.valueOf(entry.getValue()).length()>18){
                    sheet.setColumnWidth(j, 256 * 20);
                } else {
                    sheet.setColumnWidth(j, 256 * 15);
                }
                j++;
            }
        }

        //设置数据,第二行开始，i=1
        int i=1;
        for (Map<String,Object> dataMap:data){
            XSSFRow rowData=sheet.createRow(i);
            int j=0;
            for (Map.Entry<String,Object> entry:dataMap.entrySet()){
                rowData.createCell(j).setCellValue(String.valueOf(entry.getValue()));
                rowData.getCell(j).setCellStyle(cellStyle_row);
                j++;
            }
            i++;
        }

        //输出Excel文件
        OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+toUtf8String(filename));
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }

    //解决文件名中文乱码
    public static String toUtf8String(String s){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c >= 0 && c <= 255){sb.append(c);}
            else{
                byte[] b;
                try { b = Character.toString(c).getBytes("utf-8");}
                catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    public static List<Map<String,Object>> importExcel(MultipartFile file,String[] mapKeys){
        XSSFWorkbook workbook=null;
        try {
            InputStream in = file.getInputStream();
            workbook = new XSSFWorkbook(in);// 创建对Excel工作薄
        }catch (IOException e){
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 获取该工作表的第一行
        XSSFRow row = null;
        // 获取该工作表的第一个单元格
        XSSFCell cell = null;
        // 存放excel的List
        List<Map<String,Object>> itemList=new ArrayList<>();
        for (int i = 0;i<=sheet.getLastRowNum(); i++) {
            // 在循环里面写，List保存的是引用，故在外边写会被覆盖，对象的地址相同
            Map<String,Object> item=new HashMap<>();
            row = sheet.getRow(i);
            for (int j=0;j<=mapKeys.length;j++){
                // 获取第I列 第j个单元格
                if (row.getCell(j)!=null){
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    item.put(mapKeys[j],row.getCell(j).getRichStringCellValue().getString().replaceAll("\r|\n", "").trim());
                }
            }
            itemList.add(item);
        }
        return itemList;
    }

}
