package com.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 用于PDF与Base64之间的转换
 * Created by Administrator on 2018/12/5 0005.
 */
public class PDFAndBase64ConvertUtil {

    /**
     * 将base64字符串转换为PDF在显示到页面中
     * @param base64String
     * @param httpServletResponse
     */
    public static void base64StringToPDFToPage(String base64String, HttpServletResponse httpServletResponse){
        BASE64Decoder decoder = new BASE64Decoder();
        ByteArrayOutputStream baos = null;
        ServletOutputStream sos = null;
        try {
            byte[] bytes = decoder.decodeBuffer(base64String);
            baos = new ByteArrayOutputStream();
            baos.write(bytes); //把byte写进输出流里
            if (baos != null) {
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setContentLength(baos.size());
                httpServletResponse.setHeader("Expires", "0");
                httpServletResponse.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                httpServletResponse.setHeader("Pragma", "public");
                // 设置打印PDF的文件名
                String fileName = "社保证明文件.pdf";
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                httpServletResponse.setHeader("Content-Disposition", "filename=" + fileName);
                sos = httpServletResponse.getOutputStream();
                baos.writeTo(sos); //byte输出流写入servlet输出流
                sos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  将base64编码转换成PDF
     *  @param base64String
     *  1.使用BASE64Decoder对编码的字符串解码成字节数组
     *  2.使用底层输入流ByteArrayInputStream对象从字节数组中获取数据；
     *  3.建立从底层输入流中读取数据的BufferedInputStream缓冲输出流对象；
     *  4.使用BufferedOutputStream和FileOutputSteam输出数据到指定的文件中
     */
    public static void base64StringToPDF(String base64String, File file){
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bin = null;
        FileOutputStream fout = null;
        BufferedOutputStream bout = null;
        try {
            //将base64编码的字符串解码成字节数组
            byte[] bytes = decoder.decodeBuffer(base64String);
            //创建一个将bytes作为其缓冲区的ByteArrayInputStream对象
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //创建从底层输入流中读取数据的缓冲输入流对象
            bin = new BufferedInputStream(bais);

            //创建到指定文件的输出流
            fout  = new FileOutputStream(file);
            //为文件输出流对接缓冲输出流对象
            bout = new BufferedOutputStream(fout);
            common(bin,bout);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bout.close();
                fout.close();
                bin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * PDF转换为Base64编码
     * @param file
     * @return
     */
    public static String pdfToBase64(File file) {
        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            common(bin,bout);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fin.close();
                bin.close();
                baos.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void common(BufferedInputStream bin,BufferedOutputStream bout) throws Exception{
        byte[] buffer = new byte[1024];
        int len = bin.read(buffer);
        while(len != -1){
            bout.write(buffer, 0, len);
            len = bin.read(buffer);
        }
        //刷新此输出流并强制写出所有缓冲的输出字节
        bout.flush();
    }

    public static void main(String[] args) {
        String bas="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IDAgUj4+Pj4vUGFyZW50IDYgMCBSL01lZGlhQm94WzAgMCA4NDEuODkgNTk1LjI4XT4+CmVuZG9iago2IDAgb2JqCjw8L0tpZHNbMSAwIFIgNyAwIFJdL1R5cGUvUGFnZXMvQ291bnQgMi9JVFhUKDIuMS43KT4+CmVuZG9iagoxMiAwIG9iago8PC9UeXBlL0NhdGFsb2cvUGFnZXMgNiAwIFI+PgplbmRvYmoKMTMgMCBvYmoKPDwvTW9kRGF0ZShEOjIwMTkxMjEzMDIxOTM3WikvQ3JlYXRpb25EYXRlKEQ6MjAxOTEyMTMwMjE5MzdaKS9Qcm9kdWNlcihpVGV4dCAyLjEuNyBieSAxVDNYVCk+PgplbmRvYmoKeHJlZgowIDE0CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwNTAyMSAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDAzMTMgMDAwMDAgbiAKMDAwMDAwMDEzMiAwMDAwMCBuIAowMDAwMDAwMjI1IDAwMDAwIG4gCjAwMDAwMDkwNTkgMDAwMDAgbiAKMDAwMDAwODg5NyAwMDAwMCBuIAowMDAwMDA1MTgzIDAwMDAwIG4gCjAwMDAwMDU0ODMgMDAwMDAgbiAKMDAwMDAwNTMwMCAwMDAwMCBuIAowMDAwMDA1Mzk0IDAwMDAwIG4gCjAwMDAwMDkxMjggMDAwMDAgbiAKMDAwMDAwOTE3NCAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gMTMgMCBSL0lEIFs8YzFkMWNlNGZhN2M5NGNlZmUyNmI2M2MyMmUyMDcxYTM+PDQ0ZTFkODQ2MzUxNWIzZjVlNzZkMWYyYTYxOWVhZjk3Pl0vUm9vdCAxMiAwIFIvU2l6ZSAxND4+CnN0YXJ0eHJlZgo5Mjg1CiUlRU9GCg==";
        File file=new File("D:\\img\\227.pdf");
        base64StringToPDF(bas,file);
    }
}
