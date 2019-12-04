package com.dmc.fastjsontest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dmc.fastjsontest.jsonTestBean.Complex;
import com.dmc.fastjsontest.jsonTestBean.Simple;
import com.google.common.collect.Lists;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 上午10:43 2018/5/22
 * @Modified By:
 */
public class test {

    //主要有以下三种对象之间的转换：JSON对象，json串，javabean
    //主要测试三种不同的json，包括简单json，jsonarray，复杂json
    public static String simpleJson = "{\"name\": \"ttt\",\"age\": 12,\"sex\": \"male\"}";
    public static String arrayJson = "[{\"name\": \"ttt\",\"age\": 12,\"sex\": \"male\"},{\"name\": \"fff\",\"age\": 13,\"sex\": \"female\"}]";
    public static String complexJson = "{\"simples\":[{\"name\": \"ttt\",\"age\": 12,\"sex\": \"male\"},{\"name\": \"fff\",\"age\": 13,\"sex\": \"female\"}],\"index\":5,\"grades\":[12,32,43,51]}";
    public static String now = complexJson;
    public static Simple simpleBean;
    public static Complex complexBean;
    public static List<Simple> arrayBean = new ArrayList<Simple>();

    static {
        simpleBean = new Simple();
        simpleBean.setAge(15);
        simpleBean.setName("fff");
        simpleBean.setSex("male");
    }

    static {
        complexBean = new Complex();
        complexBean.setGrades(Arrays.asList(1, 2, 4, 5, 6));
        complexBean.setIndex(1);
        complexBean.setSimples(Arrays.asList(new Simple("ggg", "male", 17)
            , new Simple("ttt", "male", 18)
            , new Simple("yyy", "male", 19)
        ));
    }

    static {
        Simple simple0 = new Simple();
        simple0.setAge(15);
        simple0.setName("fff");
        simple0.setSex("male");
        Simple simple1 = new Simple();
        simple1.setSex("female");
        simple1.setAge(123);
        simple1.setName("asdas");
        Simple simple2 = new Simple();
        simple2.setSex("female");
        simple2.setAge(12);
        simple2.setName("asdas");
        arrayBean.add(simple0);
        arrayBean.add(simple1);
        arrayBean.add(simple2);
    }

    static class ttt {
        public Date now;
        public String a;
    }
    @Test
    public void tesat() {
        ttt t = new ttt();
        t.now = new Date();
        t.a = "123123";
        ttt t1 = new ttt();
        t1.now = new Date();
        t1.a = "321321";
        List<ttt> ttts = Lists.newArrayList();
        ttts.add(t);
        ttts.add(t1);
        String jsstr = JSONObject.toJSONString(ttts);
        System.out.println(jsstr);
        List<Map<String, String>> maps = JSONObject.parseObject(jsstr, new TypeReference<List<Map<String, String>>>(){});

    }
    @Test
    public void test123() {
        int ans = arrayBean.stream()
            .flatMap(item -> Stream.of(item.getAge()))
            .filter(item -> item != null)
            .reduce(0, (cnt, x) -> cnt + x);
        System.out.println(ans);
        arrayBean.stream()
            .filter(bean -> bean.getAge() > 40)
            .forEach(bean -> {
                System.out.println(bean.toString());
            });
    }

    @Test
    public void erwerw() {
        JSONArray array = JSONArray.parseArray(arrayJson);
        array.parallelStream().forEach(re -> {
            System.out.println(re.toString());
        });
    }

    @Test
    public void test21() {
        JSONArray packageModels = JSONArray.parseArray(arrayJson);
        packageModels.stream().forEach(packageModel -> {
            Map<String, String> model = JSONObject.parseObject(packageModel.toString(), new TypeReference<Map<String, String>>(){});
//            System.out.println(model.get("sex"));
        });

        List<Map<String, String>> models = JSONObject.parseObject(arrayJson, new TypeReference<List<Map<String, String>>>(){});
        models.parallelStream().forEach(model -> {
            System.out.println(model.get("sex"));
        });
        System.out.println("asdasd");
    }
    //1.1 json串 ==> JSON
    @Test
    public void test11() {
        JSONObject jsonObject = JSONObject.parseObject(simpleJson);
        System.out.println(jsonObject.get("sex"));
        System.out.println(jsonObject.toString());
        if (jsonObject.get("simples") != null) {
            System.out.println(JSONObject.parseObject(JSONArray.parseArray(jsonObject.get("simples").toString()).get(1).toString()).get("sex"));
        }
    }

    //1.2 json串 <== JSON
    @Test
    public void test12() {
        JSONObject jsonObject = JSONObject.parseObject(simpleJson);
        String json = jsonObject.toJSONString();
        System.out.println(json);
    }

    //1.3 json串 <== javabean
    @Test
    public void test13() {
        //1.3.1
        String jsonString = JSON.toJSONString(simpleBean);
        System.out.println(jsonString);
        //1.3.2
        String jsonString1 = JSONObject.toJSONString(complexBean);
        System.out.println(jsonString1);
    }

    //1.4 json串 ==> javabean
    @Test
    public void test14() {
        //1.4.1
        Simple simple = JSONObject.parseObject(simpleJson, Simple.class);
        System.out.println(simple);

        //1.4.2
        Simple simple1 = JSONObject.parseObject(simpleJson, new TypeReference<Simple>() {});
        System.out.println(simple1);

    }

    //1.5 JSON ==> javabean
    @Test
    public void test15() {
        JSONObject jsonObject1 = JSONObject.parseObject(simpleJson);
        JSONObject jsonObject2 = JSONObject.parseObject(complexJson);
        //JSON => json => javabean
        Simple simple1 = JSONObject.parseObject(jsonObject1.toJSONString(), new TypeReference<Simple>() {});
        System.out.println(simple1);
        Complex complex1 = JSONObject.parseObject(jsonObject2.toJSONString(), new TypeReference<Complex>() {
        });
        System.out.println(complex1);
    }

    //1.6 JSON <== javabean
    @Test
    public void test16() {
        //...
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(simpleBean));
        System.out.println(jsonObject.get("name"));
        System.out.println(jsonObject.get("simples"));
    }

    @Test
    public void test() {
        List<Map<String, String>> as = new ArrayList<>();
        Map<String, String> qw = new HashMap<>();
        as.add(qw);
        as.stream().forEach(a -> {
            System.out.println(a);
        });
    }

    @Test
    public void test12312() {
        Map<String, String> personModel = JSONObject.parseObject(null, new TypeReference<Map<String, String>>() {
        });
        System.out.println(JSONObject.toJSONString(null));
        System.out.println(personModel);
        List<Map<String, String>> asda = JSONObject.parseObject(null, new TypeReference<List<Map<String, String>>>() {
        });
        System.out.println(asda);
        System.out.println(JSONObject.toJSONString(null));
        asda = new ArrayList<Map<String, String>>();
        System.out.println(asda.get(0));
    }

    @Test
    public void test432() {
        System.out.println(BigDecimal.valueOf(Double.valueOf(123)));
        System.out.println(BigDecimal.valueOf(Double.valueOf(12.32112)));
        System.out.println(BigDecimal.valueOf(Double.valueOf(0.1231)));
        System.out.println(BigDecimal.valueOf(Double.valueOf(-12.1231)));
        System.out.println((Double.valueOf("234")));
    }

    @Test
    public void asdfs() {
        Object asd = new Object();
        System.out.println(asd.toString());
    }

    //JSON<==>json
    @Test
    public void test31() {
        //json==>JSON
        JSONArray array = JSONArray.parseArray(arrayJson);
        JSONObject object = array.getJSONObject(1);
        System.out.println(object.get("sex"));
        //json<==JSON
        String json = JSONArray.toJSONString(array);
        System.out.println(json);
    }

    //JSON<==>javabean
    @Test
    public void test32() {
        //JSON==>javabean
        JSONArray array = JSONArray.parseArray(arrayJson);
        List<Simple> simples = JSONArray.parseArray(array.toJSONString(), Simple.class);
        System.out.println(simples);
        //JSON<==javabean
        JSONArray array1 = JSONArray.parseArray(JSONArray.toJSONString(arrayBean));
        JSONObject object = array.getJSONObject(1);
        System.out.println(object.get("sex"));
    }

    //json<==>javabean
    @Test
    public void test33() {
        //json=>javabean
        //第一种方式
        List<Simple> simples = JSONArray.parseArray(arrayJson, Simple.class);
        System.out.println(simples);
        //第二种方式
        JSONArray array = JSONArray.parseArray(arrayJson);
        Type[] types = new Type[array.size()];
        Arrays.fill(types, Map.class);
        List<Object> simples1 = JSONArray.parseArray(arrayJson, types);
        simples1.forEach(simples2 -> {
            Map<String, Object> asd = (Map<String, Object>) simples2;
            System.out.println(asd.get("name"));
        });
        //json<=javabean
        String str = JSONArray.toJSONString(arrayBean);
        System.out.println(str);
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void test435() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 50; i ++) {
            list.add(i);
        }
        list.parallelStream().forEach(item ->
            System.out.println(item)
        );
        System.out.println("asdasdasd");
    }

    @Test
    public void test45() {
        String js = "[\n"
            + "        {\n"
            + "            \"pid\": \"587237e3eafeff4fab38a3ab\",\n"
            + "            \"pname\": \"Korra Avatar The Legend of Korra Decorate Mouse Pad / Mouse Mat\",\n"
            + "            \"mid\": \"5844f37515b9d74c917992a9\",\n"
            + "            \"mname\": \"flywings\",\n"
            + "            \"approved_date\": \"2016-12-05\",\n"
            + "            \"is_promo\": 0,\n"
            + "            \"is_verified\": 0,\n"
            + "            \"num_bought\": 1,\n"
            + "            \"num_entered\": 7,\n"
            + "            \"num_rating\": 0,\n"
            + "            \"rating\": 5,\n"
            + "            \"gen_time\": \"2017-01-08\",\n"
            + "            \"o_price\": 8,\n"
            + "            \"o_shipping\": 3,\n"
            + "            \"price\": 8,\n"
            + "            \"shipping\": 3,\n"
            + "            \"merchant\": \"Fly Wings\",\n"
            + "            \"c_ids\": [\n"
            + "                \"tag_5592f2d36c5463757dd92890\"\n"
            + "            ],\n"
            + "            \"supplier_url\": 0,\n"
            + "            \"mer_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"Mats\",\n"
            + "                    \"id\": \"mat\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"korra\",\n"
            + "                    \"id\": \"korra\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thelegendofkorra\",\n"
            + "                    \"id\": \"thelegendofkorra\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"avatar\",\n"
            + "                    \"id\": \"avatar\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"mouse pad\",\n"
            + "                    \"id\": \"mousepad\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"mouse mat\",\n"
            + "                    \"id\": \"mousemat\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"pro_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"Mouse\",\n"
            + "                    \"id\": \"mouse\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Mats\",\n"
            + "                    \"id\": \"mat\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"mouse pad\",\n"
            + "                    \"id\": \"mousepad\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"korra\",\n"
            + "                    \"id\": \"korra\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thelegendofkorra\",\n"
            + "                    \"id\": \"thelegendofkorra\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"mouse mat\",\n"
            + "                    \"id\": \"mousemat\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"avatar\",\n"
            + "                    \"id\": \"avatar\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"is_hwc\": 0,\n"
            + "            \"sales_week1\": 0,\n"
            + "            \"sales_week2\": 0,\n"
            + "            \"sales_growth\": 0,\n"
            + "            \"payment_week1\": 0,\n"
            + "            \"payment_week2\": 0,\n"
            + "            \"wishs_sweek1\": null,\n"
            + "            \"wishs_week2\": null,\n"
            + "            \"wishs_growth\": null,\n"
            + "            \"hy_index\": null,\n"
            + "            \"hot_flag\": 0,\n"
            + "            \"total_price\": 11,\n"
            + "            \"m_sales_week1\": 3,\n"
            + "            \"rate_week1\": 0,\n"
            + "            \"daily_bought\": 0,\n"
            + "            \"status\": 0,\n"
            + "            \"is_pb\": 0,\n"
            + "            \"last_upd_date\": \"2018-07-11 06:00:15\",\n"
            + "            \"m_rating_count\": 252,\n"
            + "            \"m_status\": 0,\n"
            + "            \"feed_tile_text\": 0\n"
            + "        },\n"
            + "        {\n"
            + "            \"pid\": \"58755ce5ffe2e158489cd133\",\n"
            + "            \"pname\": \"Car 5V 2.1A Dual USB Port Socket GPS Cell Phone Charger For MITSUBISHI\",\n"
            + "            \"mid\": \"55dc6b85f51bf838d3e3dcc2\",\n"
            + "            \"mname\": \"topsea\",\n"
            + "            \"approved_date\": \"2015-08-27\",\n"
            + "            \"is_promo\": 0,\n"
            + "            \"is_verified\": 0,\n"
            + "            \"num_bought\": 1,\n"
            + "            \"num_entered\": 8,\n"
            + "            \"num_rating\": 1,\n"
            + "            \"rating\": 5,\n"
            + "            \"gen_time\": \"2017-01-10\",\n"
            + "            \"o_price\": 9,\n"
            + "            \"o_shipping\": 4,\n"
            + "            \"price\": 9,\n"
            + "            \"shipping\": 4,\n"
            + "            \"merchant\": \"topsea\",\n"
            + "            \"c_ids\": [],\n"
            + "            \"supplier_url\": 0,\n"
            + "            \"mer_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"carcigarettelighter\",\n"
            + "                    \"id\": \"carcigarettelighter\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Usb Charger\",\n"
            + "                    \"id\": \"usbcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"dualusbcarcharger\",\n"
            + "                    \"id\": \"dualusbcarcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Adapter\",\n"
            + "                    \"id\": \"adapter\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Splitter\",\n"
            + "                    \"id\": \"splitter\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"fastcharger\",\n"
            + "                    \"id\": \"fastcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"forphone\",\n"
            + "                    \"id\": \"forphone\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Gps\",\n"
            + "                    \"id\": \"gp\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"withledlight\",\n"
            + "                    \"id\": \"withledlight\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"formitsubishi\",\n"
            + "                    \"id\": \"formitsubishi\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"pro_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"usb\",\n"
            + "                    \"id\": \"usb\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Phone\",\n"
            + "                    \"id\": \"phone\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"charger\",\n"
            + "                    \"id\": \"charger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Cars\",\n"
            + "                    \"id\": \"car\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"withledlight\",\n"
            + "                    \"id\": \"withledlight\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"fastcharger\",\n"
            + "                    \"id\": \"fastcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Splitter\",\n"
            + "                    \"id\": \"splitter\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Usb Charger\",\n"
            + "                    \"id\": \"usbcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Gps\",\n"
            + "                    \"id\": \"gp\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"carcigarettelighter\",\n"
            + "                    \"id\": \"carcigarettelighter\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"formitsubishi\",\n"
            + "                    \"id\": \"formitsubishi\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"forphone\",\n"
            + "                    \"id\": \"forphone\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"dualusbcarcharger\",\n"
            + "                    \"id\": \"dualusbcarcharger\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Adapter\",\n"
            + "                    \"id\": \"adapter\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"is_hwc\": 0,\n"
            + "            \"sales_week1\": 0,\n"
            + "            \"sales_week2\": 0,\n"
            + "            \"sales_growth\": 0,\n"
            + "            \"payment_week1\": 0,\n"
            + "            \"payment_week2\": 0,\n"
            + "            \"wishs_sweek1\": null,\n"
            + "            \"wishs_week2\": null,\n"
            + "            \"wishs_growth\": null,\n"
            + "            \"hy_index\": null,\n"
            + "            \"hot_flag\": 0,\n"
            + "            \"total_price\": 13,\n"
            + "            \"m_sales_week1\": 521,\n"
            + "            \"rate_week1\": 0,\n"
            + "            \"daily_bought\": 0,\n"
            + "            \"status\": 0,\n"
            + "            \"is_pb\": 0,\n"
            + "            \"last_upd_date\": \"2018-07-11 06:00:18\",\n"
            + "            \"m_rating_count\": 47311,\n"
            + "            \"m_status\": 0,\n"
            + "            \"feed_tile_text\": 0\n"
            + "        },\n"
            + "        {\n"
            + "            \"pid\": \"587caf7412dfee4f2dd3ea3c\",\n"
            + "            \"pname\": \"24'' Long Straight Hair Ponytail Drawstring human Ponytails Natural Hair Ponytail Extnesions Apply Hair real natural 100%\",\n"
            + "            \"mid\": \"585f9e174b60fe4cb032cb39\",\n"
            + "            \"mname\": \"hebeixiongbangimpexpcoltd\",\n"
            + "            \"approved_date\": \"2016-12-25\",\n"
            + "            \"is_promo\": 0,\n"
            + "            \"is_verified\": 0,\n"
            + "            \"num_bought\": 2,\n"
            + "            \"num_entered\": 9,\n"
            + "            \"num_rating\": 0,\n"
            + "            \"rating\": 5,\n"
            + "            \"gen_time\": \"2017-01-16\",\n"
            + "            \"o_price\": 15,\n"
            + "            \"o_shipping\": 7,\n"
            + "            \"price\": 15,\n"
            + "            \"shipping\": 7,\n"
            + "            \"merchant\": \"HEBEI XIONGBANG IMP & EXP CO.,LTD\",\n"
            + "            \"c_ids\": [],\n"
            + "            \"supplier_url\": 0,\n"
            + "            \"mer_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"24\",\n"
            + "                    \"id\": \"24\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"human\",\n"
            + "                    \"id\": \"human\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Natural\",\n"
            + "                    \"id\": \"natural\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Straight\",\n"
            + "                    \"id\": \"straight\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"extnesion\",\n"
            + "                    \"id\": \"extnesion\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"long\",\n"
            + "                    \"id\": \"long\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"hair\",\n"
            + "                    \"id\": \"hair\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"drawstring\",\n"
            + "                    \"id\": \"drawstring\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"apply\",\n"
            + "                    \"id\": \"apply\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"ponytails\",\n"
            + "                    \"id\": \"ponytail\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"pro_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"long\",\n"
            + "                    \"id\": \"long\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"hair\",\n"
            + "                    \"id\": \"hair\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"24\",\n"
            + "                    \"id\": \"24\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"extnesion\",\n"
            + "                    \"id\": \"extnesion\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Natural\",\n"
            + "                    \"id\": \"natural\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Straight\",\n"
            + "                    \"id\": \"straight\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"drawstring\",\n"
            + "                    \"id\": \"drawstring\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"human\",\n"
            + "                    \"id\": \"human\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"apply\",\n"
            + "                    \"id\": \"apply\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"ponytails\",\n"
            + "                    \"id\": \"ponytail\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"is_hwc\": 0,\n"
            + "            \"sales_week1\": 0,\n"
            + "            \"sales_week2\": 0,\n"
            + "            \"sales_growth\": 0,\n"
            + "            \"payment_week1\": 0,\n"
            + "            \"payment_week2\": 0,\n"
            + "            \"wishs_sweek1\": null,\n"
            + "            \"wishs_week2\": null,\n"
            + "            \"wishs_growth\": null,\n"
            + "            \"hy_index\": null,\n"
            + "            \"hot_flag\": 0,\n"
            + "            \"total_price\": 22,\n"
            + "            \"m_sales_week1\": 2,\n"
            + "            \"rate_week1\": 0,\n"
            + "            \"daily_bought\": 0,\n"
            + "            \"status\": 0,\n"
            + "            \"is_pb\": 0,\n"
            + "            \"last_upd_date\": \"2018-07-11 06:00:21\",\n"
            + "            \"m_rating_count\": 1976,\n"
            + "            \"m_status\": 0,\n"
            + "            \"feed_tile_text\": 0\n"
            + "        },\n"
            + "        {\n"
            + "            \"pid\": \"587b1c3660773822f0ec925e\",\n"
            + "            \"pname\": \"Bird Metal Die cutting Dies For DIY Scrapbooking Photo Album Decorative Embossing Folder Stencil\",\n"
            + "            \"mid\": \"5492a43bb9b7e76cdbc6b334\",\n"
            + "            \"mname\": \"kwanwohsstore\",\n"
            + "            \"approved_date\": \"2014-12-18\",\n"
            + "            \"is_promo\": 0,\n"
            + "            \"is_verified\": 0,\n"
            + "            \"num_bought\": 1,\n"
            + "            \"num_entered\": 3,\n"
            + "            \"num_rating\": 0,\n"
            + "            \"rating\": 5,\n"
            + "            \"gen_time\": \"2017-01-15\",\n"
            + "            \"o_price\": 1,\n"
            + "            \"o_shipping\": 2,\n"
            + "            \"price\": 1,\n"
            + "            \"shipping\": 2,\n"
            + "            \"merchant\": \"Kwan Woh's Store\",\n"
            + "            \"c_ids\": [],\n"
            + "            \"supplier_url\": 0,\n"
            + "            \"mer_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"diyscrapbook\",\n"
            + "                    \"id\": \"diyscrapbook\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diecutting\",\n"
            + "                    \"id\": \"diecutting\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Stamps\",\n"
            + "                    \"id\": \"stamp\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"die\",\n"
            + "                    \"id\": \"die\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"cuttingdie\",\n"
            + "                    \"id\": \"cuttingdie\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"metaldie\",\n"
            + "                    \"id\": \"metaldie\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Embossing\",\n"
            + "                    \"id\": \"embossing\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diyalbum\",\n"
            + "                    \"id\": \"diyalbum\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Scrapbooking & Paper Crafts\",\n"
            + "                    \"id\": \"scrapbookingpapercraft\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Bird\",\n"
            + "                    \"id\": \"bird\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"pro_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"Metal\",\n"
            + "                    \"id\": \"metal\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Bird\",\n"
            + "                    \"id\": \"bird\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diyscrapbook\",\n"
            + "                    \"id\": \"diyscrapbook\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diecutting\",\n"
            + "                    \"id\": \"diecutting\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Stamps\",\n"
            + "                    \"id\": \"stamp\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"die\",\n"
            + "                    \"id\": \"die\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"cuttingdie\",\n"
            + "                    \"id\": \"cuttingdie\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"metaldie\",\n"
            + "                    \"id\": \"metaldie\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Embossing\",\n"
            + "                    \"id\": \"embossing\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diyalbum\",\n"
            + "                    \"id\": \"diyalbum\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"diy\",\n"
            + "                    \"id\": \"diy\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Scrapbooking & Paper Crafts\",\n"
            + "                    \"id\": \"scrapbookingpapercraft\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"is_hwc\": 0,\n"
            + "            \"sales_week1\": 0,\n"
            + "            \"sales_week2\": 0,\n"
            + "            \"sales_growth\": 0,\n"
            + "            \"payment_week1\": 0,\n"
            + "            \"payment_week2\": 0,\n"
            + "            \"wishs_sweek1\": null,\n"
            + "            \"wishs_week2\": null,\n"
            + "            \"wishs_growth\": null,\n"
            + "            \"hy_index\": null,\n"
            + "            \"hot_flag\": 0,\n"
            + "            \"total_price\": 3,\n"
            + "            \"m_sales_week1\": 124,\n"
            + "            \"rate_week1\": 0,\n"
            + "            \"daily_bought\": 0,\n"
            + "            \"status\": 0,\n"
            + "            \"is_pb\": 0,\n"
            + "            \"last_upd_date\": \"2018-07-11 06:00:21\",\n"
            + "            \"m_rating_count\": 13354,\n"
            + "            \"m_status\": 0,\n"
            + "            \"feed_tile_text\": 0\n"
            + "        },\n"
            + "        {\n"
            + "            \"pid\": \"587f6529c86bf1470c5b7f69\",\n"
            + "            \"pname\": \"200ML Stainless Steel Thermal Bottle\",\n"
            + "            \"mid\": \"57a2a6d823201d18a3ee2488\",\n"
            + "            \"mname\": \"taotaobao\",\n"
            + "            \"approved_date\": \"2016-08-23\",\n"
            + "            \"is_promo\": 0,\n"
            + "            \"is_verified\": 0,\n"
            + "            \"num_bought\": 3,\n"
            + "            \"num_entered\": 10,\n"
            + "            \"num_rating\": 1,\n"
            + "            \"rating\": 5,\n"
            + "            \"gen_time\": \"2017-01-18\",\n"
            + "            \"o_price\": 10,\n"
            + "            \"o_shipping\": 4,\n"
            + "            \"price\": 10,\n"
            + "            \"shipping\": 4,\n"
            + "            \"merchant\": \"Taotaobao\",\n"
            + "            \"c_ids\": [],\n"
            + "            \"supplier_url\": 0,\n"
            + "            \"mer_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"Bottle\",\n"
            + "                    \"id\": \"bottle\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thermalbottle\",\n"
            + "                    \"id\": \"thermalbottle\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thermalbottlecup\",\n"
            + "                    \"id\": \"thermalbottlecup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"childrencup\",\n"
            + "                    \"id\": \"childrencup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thenewvacuumcup\",\n"
            + "                    \"id\": \"thenewvacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"vacuumcup\",\n"
            + "                    \"id\": \"vacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"highqualityvacuumcup\",\n"
            + "                    \"id\": \"highqualityvacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"vacuuminsulationcup\",\n"
            + "                    \"id\": \"vacuuminsulationcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Stainless Steel\",\n"
            + "                    \"id\": \"stainlesssteel\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Adult\",\n"
            + "                    \"id\": \"adult\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"pro_tags\": [\n"
            + "                {\n"
            + "                    \"name\": \"Steel\",\n"
            + "                    \"id\": \"steel\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Stainless Steel\",\n"
            + "                    \"id\": \"stainlesssteel\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Stainless\",\n"
            + "                    \"id\": \"stainles\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Bottle\",\n"
            + "                    \"id\": \"bottle\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thenewvacuumcup\",\n"
            + "                    \"id\": \"thenewvacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"vacuuminsulationcup\",\n"
            + "                    \"id\": \"vacuuminsulationcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"vacuumcup\",\n"
            + "                    \"id\": \"vacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thermalbottle\",\n"
            + "                    \"id\": \"thermalbottle\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"highqualityvacuumcup\",\n"
            + "                    \"id\": \"highqualityvacuumcup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Thermal\",\n"
            + "                    \"id\": \"thermal\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"Adult\",\n"
            + "                    \"id\": \"adult\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"childrencup\",\n"
            + "                    \"id\": \"childrencup\",\n"
            + "                    \"is_filter\": false\n"
            + "                },\n"
            + "                {\n"
            + "                    \"name\": \"thermalbottlecup\",\n"
            + "                    \"id\": \"thermalbottlecup\",\n"
            + "                    \"is_filter\": false\n"
            + "                }\n"
            + "            ],\n"
            + "            \"is_hwc\": 0,\n"
            + "            \"sales_week1\": 0,\n"
            + "            \"sales_week2\": 1,\n"
            + "            \"sales_growth\": -100,\n"
            + "            \"payment_week1\": 0,\n"
            + "            \"payment_week2\": 12,\n"
            + "            \"wishs_sweek1\": null,\n"
            + "            \"wishs_week2\": null,\n"
            + "            \"wishs_growth\": null,\n"
            + "            \"hy_index\": null,\n"
            + "            \"hot_flag\": 0,\n"
            + "            \"total_price\": 14,\n"
            + "            \"m_sales_week1\": 226,\n"
            + "            \"rate_week1\": 0,\n"
            + "            \"daily_bought\": 0,\n"
            + "            \"status\": 0,\n"
            + "            \"is_pb\": 0,\n"
            + "            \"last_upd_date\": \"2018-07-11 06:00:25\",\n"
            + "            \"m_rating_count\": 25937,\n"
            + "            \"m_status\": 0,\n"
            + "            \"feed_tile_text\": 0\n"
            + "        }]";

        JSONArray resultArray = JSONArray.parseArray(js);
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 1; i ++) {
            for (int j = 0; j < resultArray.size(); j ++) {
                list.add(resultArray.getJSONObject(j).get("pid").toString());
            }
            String pids = list.stream().collect(Collectors.joining(",", "", ""));
            System.out.println(pids);
        }

    }

    @Test
    public void test456() {
        for (int i = 0; i <= 10; i ++) {
            Simple simple = new Simple();
            System.out.println(simple.hashCode());
        }
    }
}
