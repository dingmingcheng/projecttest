package com.dmc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel导入导出类
 */
@Slf4j
public class ExcelUtil {

    public static void main(String[] args) {
        String a = "1341";
        String b = "5432";
        String c = "a";
        String d = "adasdk";
        System.out.println(a.compareTo(b));
        System.out.println(c.compareTo(d));
        PriorityQueue<Solution> queue = new PriorityQueue<>();

    }

    private ExcelUtil() {
    }

    public static List<Map<String, Object>> excelToListHasHeader(InputStream in) throws IOException{
        return excelToListHasHeader(in, "xlsx");
    }
    /**
     * @param in 文件
     */
    public static List<Map<String, Object>> excelToListHasHeader(InputStream in, String suffix) throws IOException {
        Workbook wb;
        if ("xls".equals(suffix)) {
            wb = new HSSFWorkbook(in);
        }
        else {
            try {
                wb = new XSSFWorkbook(in);
            } catch (Exception ex) {
                POIFSFileSystem fs = new POIFSFileSystem(in);
                wb = new HSSFWorkbook(fs);
            }
        }

        int count = wb.getNumberOfSheets();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < count; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int maxNumOfRows = sheet.getLastRowNum();
            List<String> headerList = Lists.newArrayList();
            for (int j = 0; j <= maxNumOfRows; j++) {
                Row row = sheet.getRow(j);
                int maxNumCells = row.getLastCellNum();
                if (j != 0) {
                    map = Maps.newHashMap();
                    list.add(map);
                }
                for (int m = 0; m < maxNumCells; m++) {
                    Cell cell = row.getCell(m);
                    //第一行取header
                    if (j == 0) {
                        if (cell != null) {
                            cell.setCellType(CellType.STRING);
                            headerList.add(cell.getStringCellValue().trim());
                        } else {
                            headerList.add(m + "");
                        }
                    } else {
                        if (cell != null) {
                            if (CellType.NUMERIC.equals(cell.getCellTypeEnum()) && HSSFDateUtil.isCellDateFormatted(cell)) {
                                map.put(headerList.get(m), HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            } else if (CellType.NUMERIC.equals(cell.getCellTypeEnum())) {
                                map.put(headerList.get(m), NumberToTextConverter.toText(cell.getNumericCellValue()));
                            } else {
                                cell.setCellType(CellType.STRING);
                                map.put(headerList.get(m), cell.getStringCellValue().trim());
                            }
                        } else {
                            map.put(headerList.get(m), "");
                        }
                    }
                }
            }
        }
        wb.close();
        return list;
    }

    /**
     * @param in 文件
     * @param line 从第几行开始读取数据
     * @param param 每一列对应的数据内容
     */
    public static List<Map<String, String>> excelToList(InputStream in, int line, String... param) throws IOException {
        Workbook wb;
        try {
            wb = new XSSFWorkbook(in);
        } catch (Exception ex) {
            POIFSFileSystem fs = new POIFSFileSystem(in);
            wb = new HSSFWorkbook(fs);
        }

        int count = wb.getNumberOfSheets();
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int maxNumOfRows = sheet.getLastRowNum();
            for (int j = line; j <= maxNumOfRows; j++) {
                Row row = sheet.getRow(j);
                int maxNumCells = row.getLastCellNum();
                Map<String, String> map = Maps.newHashMap();
                for (int m = 0; m < maxNumCells; m++) {
                    Cell cell = row.getCell(m);
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        map.put(param[m], cell.getStringCellValue().trim());
                    } else {
                        map.put(param[m], "");
                    }
                }
                list.add(map);
            }
        }
        wb.close();
        return list;
    }

    /**
     * 生成XML文件
     *
     * @param header 表头(各列名称)
     * @param list 每列数据
     * @param path 保存文件路径
     */
    public static void generateXML(List<String> header, List<Map<String, String>> list, String path) throws IOException {
        int columnSize = header.size();
        int rowSize = list.size();
        int columnNow = 1;
        File file = new File(path);
        Workbook workbook = null;
        Sheet sheet;
        Row rows;
        try {
            if (!file.exists()) {
                try {
                    workbook = new XSSFWorkbook();
                } catch (Exception e) {
                    workbook = new HSSFWorkbook();
                }
                sheet = workbook.createSheet("sheet1");
                rows = sheet.createRow(0);
                for (int i = 0; i < columnSize; i++) {
                    rows.createCell(i).setCellValue(header.get(i));
                }
            } else {
                FileInputStream inputStream = new FileInputStream(new File(path));
                try {
                    workbook = new XSSFWorkbook(inputStream);
                } catch (Exception e) {
                    workbook = new HSSFWorkbook(inputStream);
                }
                sheet = workbook.getSheetAt(0);
                columnNow = sheet.getLastRowNum() + 1;
                inputStream.close();
            }
            for (int row = columnNow; row < columnNow + rowSize; row++) {
                rows = sheet.createRow(row);
                for (int column = 0; column < columnSize; column++) {
                    rows.createCell(column).setCellValue(list.get(row - columnNow).get(header.get(column)));
                }
            }
            FileOutputStream xlsStream = new FileOutputStream(new File(path));
            workbook.write(xlsStream);
            xlsStream.close();
        } catch (Exception e){
            log.error("error happened while generateXML, {}", e);
        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
                log.error("workbook is null");
            }
        }
    }

}
