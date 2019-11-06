package com.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class XsteamUtil {

    public static String toXml(Object o){
        XStream xstream = new XStream();
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.processAnnotations(o.getClass());
        return xstream.toXML(o);
    }

    public static Object toModel(String xml,Class type){
            XStream xstream = new XStream();
            xstream.ignoreUnknownElements();
            xstream.setMode(XStream.NO_REFERENCES);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.processAnnotations(type);
            return  xstream.fromXML(xml);
    }



}