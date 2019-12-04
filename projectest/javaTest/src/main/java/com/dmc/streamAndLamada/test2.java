package com.dmc.streamAndLamada;

import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午7:27 2018/5/31
 * @Modified By:
 */
public class test2 {
    public static void main(String[] args) { // initMemberList为获取数据的方法
        BigDecimal sum = new BigDecimal(10);
        System.out.println(new BigDecimal("1.22"));
        System.out.println(new BigDecimal(1.22));
        System.out.println(BigDecimal.valueOf(Double.valueOf("1.22")));

        List<Member> list = Lists.newArrayList();
        list.add(new Member().setId("1").setImgPath("1111"));
        list.add(new Member().setId("2").setImgPath("2222"));
        Map<String, Member> memberMap = list.stream().collect(Collectors.toMap(Member::getId, Function.identity()));
        System.out.println(memberMap);
    }
}

class Member {

    private String id;
    private String imgPath;

    public String getId() {
        return id;
    }

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Member setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id='" + id + '\'' +
            ", imgPath='" + imgPath + '\'' +
            '}';
    }
}