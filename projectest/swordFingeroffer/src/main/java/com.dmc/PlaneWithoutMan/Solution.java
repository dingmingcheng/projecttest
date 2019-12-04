package com.dmc.PlaneWithoutMan;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.*;

public class Solution {
    static class PlaneInfo {
        public String planeId;
        public Integer x;
        public Integer y;
        public Integer z;
        public Integer offsetX;
        public Integer offsetY;
        public Integer offsetZ;
        public Boolean isOk;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String pathName = "D:\\in.txt";
        List<String> contents = new ArrayList<String>();
        Map<Integer, PlaneInfo> planeInfos = new HashMap<Integer, PlaneInfo>();

        StringBuilder input = getFileContent(pathName, contents);
        analyseContents(contents, planeInfos);
        int length = planeInfos.size();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int query = in.nextInt();
            if (query >= length) {
                System.out.println("Cannot find " + query);
            } else {
                PlaneInfo planeInfo = planeInfos.get(query);
                if (planeInfo.isOk == true) {
                    System.out.println(planeInfo.planeId
                            + " " + query
                            + " " + (planeInfo.x + planeInfo.offsetX)
                            + " " + (planeInfo.y + planeInfo.offsetY)
                            + " " + (planeInfo.z + planeInfo.offsetZ));
                } else {
                    System.out.println("Error: " + query);
                }
            }
        }
    }

    public static StringBuilder getFileContent(String pathName, List<String> contents) {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(pathName);
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                contents.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public static void analyseContents(List<String> contents, Map<Integer, PlaneInfo> planeInfos) {
        int cnt = 0;
        int x = 0, y = 0, z = 0;
        Map<String, Integer> planeX = new HashMap<String, Integer>();
        Map<String, Integer> planeY = new HashMap<String, Integer>();
        Map<String, Integer> planeZ = new HashMap<String, Integer>();
        Map<String, Boolean> planeOK = new HashMap<String, Boolean>();
        for (String content : contents) {
            String[] strings = content.split("\\s+");
            PlaneInfo info = new PlaneInfo();
            if (strings.length == 4 && planeOK.get(strings[0]) == null) {
                info.x = Integer.valueOf(strings[1]);
                info.y = Integer.valueOf(strings[2]);
                info.z = Integer.valueOf(strings[3]);
                info.offsetX = 0;
                info.offsetY = 0;
                info.offsetZ = 0;
                info.planeId = strings[0];
                info.isOk = true;
                planeX.put(info.planeId, info.x);
                planeY.put(info.planeId, info.y);
                planeZ.put(info.planeId, info.z);
                planeOK.put(info.planeId, true);
            } else if (strings.length != 7) {
                info.isOk = false;
                planeOK.put(strings[0], false);
            } else {
                info.x = Integer.valueOf(strings[1]);
                info.y = Integer.valueOf(strings[2]);
                info.z = Integer.valueOf(strings[3]);
                info.offsetX = Integer.valueOf(strings[4]);
                info.offsetY = Integer.valueOf(strings[5]);
                info.offsetZ = Integer.valueOf(strings[6]);
                info.planeId = strings[0];

                if (planeOK.get(info.planeId) != null && planeOK.get(info.planeId).equals(false)) {
                    info.isOk = false;
                    planeOK.put(info.planeId, false);
                } else if (planeX.get(info.planeId) == info.x
                        && planeY.get(info.planeId) == info.y
                        && planeZ.get(info.planeId) == info.z) {
                    planeX.put(info.planeId, info.x + info.offsetX);
                    planeY.put(info.planeId, info.y + info.offsetY);
                    planeZ.put(info.planeId, info.z + info.offsetZ);
                    info.isOk = true;
                } else {
                    info.isOk = false;
                }
            }

            planeInfos.put(cnt ++, info);
        }
    }
}
