package com.zgkj.util;

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
        String bas="JVBERi0xLjQKJfbk/N8KMSAwIG9iago8PAovVHlwZSAvQ2F0YWxvZwovVmVyc2lvbiAvMS40Ci9QYWdlcyAyIDAgUgo+PgplbmRvYmoKMyAwIG9iago8PAovTW9kRGF0ZSAoRDoyMDE5MTIyNjA5MzAwNVopCi9DcmVhdGlvbkRhdGUgKEQ6MjAxOTEyMjYwOTMwMDVaKQovUHJvZHVjZXIgKGlUZXh0IDIuMS43IGJ5IDFUM1hUKQo+PgplbmRvYmoKMiAwIG9iago8PAovVHlwZSAvUGFnZXMKL0tpZHMgWzQgMCBSXQovQ291bnQgMQo+PgplbmRvYmoKNCAwIG9iago8PAovQ29udGVudHMgNSAwIFIKL1R5cGUgL1BhZ2UKL1Jlc291cmNlcyA2IDAgUgovUGFyZW50IDIgMCBSCi9NZWRpYUJveCBbMC4wIDAuMCA1MzguNTggNzc5LjUzXQovQ3JvcEJveCBbMC4wIDAuMCA1MzguNTggNzc5LjUzXQovUm90YXRlIDAKPj4KZW5kb2JqCjUgMCBvYmoKPDwKL0xlbmd0aCA1MQovRmlsdGVyIC9GbGF0ZURlY29kZQo+PgpzdHJlYW0NCnicK+RyCuEyUDA3t9QzNVYISeFyDeEK5CpUMFQwAEIImZyroB+RZqjgkq8QyAUA/fwKWA0KZW5kc3RyZWFtCmVuZG9iago2IDAgb2JqCjw8Ci9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQyAvSW1hZ2VJXQovWE9iamVjdCA8PAovWGYxIDcgMCBSCj4+Cj4+CmVuZG9iago3IDAgb2JqCjw8Ci9MZW5ndGggMjM3MQovU3VidHlwZSAvRm9ybQovRmlsdGVyIC9GbGF0ZURlY29kZQovVHlwZSAvWE9iamVjdAovTWF0cml4IFsxIDAgMCAxIDAgMF0KL0Zvcm1UeXBlIDEKL1Jlc291cmNlcyA8PAovUHJvY1NldCBbL1BERiAvVGV4dCAvSW1hZ2VCIC9JbWFnZUMgL0ltYWdlSV0KL0ZvbnQgOCAwIFIKPj4KL0JCb3ggWzAgMCA1MzguNTggNzc5LjUzXQo+PgpzdHJlYW0NCnicrVlbe9s2En3Xr8Dbpt/GDO6XPNWRndRNaim23Gx2uw+0RFtsJNKlKK+1v74DAiRBi3Ko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GeZYBJitejoCL6hCKjkJ8SoRdTxiUIhwAtohjreFvans4FwHpVQNFlQh1cpHqUnQabyz17Uj8NpahGuoF2mA4jjiw/XM3iSHBJhZtdmlRIPNsNbZi9TE4VWlJb5r3xzMQPum0q3qYIQ73jg7nX7fRSar7xTCzQruz+7F3t6LEiTc/4DSJJkdxkijnOwfdOJ1KrQ8CtcARzBn23L3poyfkB3Hp9k91HzxmPZPAQ6ghoyK69HfOC6KNCmOgAm4FGQadXl/syrTdVwmaJxnm/Q+S45xLGRUJX38z5+Stbtgj9Mytj8MQKxjEGwVS88cZz996pd5Zp8fIRN03bHhPupCcPHmS4KgZSTAuPOkKNO7HSqXcQl/gSTIoGGuKz+BSLIyBp2wgN/6h45S0w0qi22C4mwBRkA787L6uUGxnwTWqBikXIL5ph77xAV8DNZwFMmB70B79iFlxGqOktQPpuLb/DGJBswZnIBVkWqmUUK6bL6M1z0BPoDAZPM1DKHMq6lpvkn7g2pHTqqnZYl2GKOlcbdy+3WXPSubcnG5LQY5Zb8Xlp19SU79V+qHcfZyzVSTscAtrQlxbjXfv3W+YYWeC/sgJjJ29kWktqnqli+MohixJBl4SzH236GN8/VDnO3QdRmvH4aMA7nSduRJdcRrecuZnwZO4/sETN6gzoXxM/z5E7kXbPMNCmVuZHN0cmVhbQplbmRvYmoKOCAwIG9iago8PAovRjEgOSAwIFIKL0YyIDEwIDAgUgo+PgplbmRvYmoKOSAwIG9iago8PAovU3VidHlwZSAvVHlwZTEKL1R5cGUgL0ZvbnQKL0Jhc2VGb250IC9IZWx2ZXRpY2EtQm9sZAovRW5jb2RpbmcgL1dpbkFuc2lFbmNvZGluZwo+PgplbmRvYmoKMTAgMCBvYmoKPDwKL1N1YnR5cGUgL1R5cGUxCi9UeXBlIC9Gb250Ci9CYXNlRm9udCAvSGVsdmV0aWNhCi9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nCj4+CmVuZG9iagp4cmVmCjAgMTEKMDAwMDAwMDAwMCA2NTUzNSBmDQowMDAwMDAwMDE1IDAwMDAwIG4NCjAwMDAwMDAxOTUgMDAwMDAgbg0KMDAwMDAwMDA3OCAwMDAwMCBuDQowMDAwMDAwMjUyIDAwMDAwIG4NCjAwMDAwMDA0MDkgMDAwMDAgbg0KMDAwMDAwMDUzMyAwMDAwMCBuDQowMDAwMDAwNjI2IDAwMDAwIG4NCjAwMDAwMDMyMzcgMDAwMDAgbg0KMDAwMDAwMzI3OSAwMDAwMCBuDQowMDAwMDAzMzgxIDAwMDAwIG4NCnRyYWlsZXIKPDwKL1Jvb3QgMSAwIFIKL0luZm8gMyAwIFIKL0lEIFs8MzUxQjY5MDFDMEM5MkQxRUVCMjMzM0Q4MzRDMTE1NjM+IDwzNTFCNjkwMUMwQzkyRDFFRUIyMzMzRDgzNEMxMTU2Mz5dCi9TaXplIDExCj4+CnN0YXJ0eHJlZgozNDc5CiUlRU9GCg==";
        File file=new File("D:\\pdf\\2.pdf");
        base64StringToPDF(bas,file);
    }
}
