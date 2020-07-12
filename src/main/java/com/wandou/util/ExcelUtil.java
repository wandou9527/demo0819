package com.wandou.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public class ExcelUtil {

    public static Workbook get2003Workbook(InputStream is) {
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (Exception e) {
            return wb;
        }
        return wb;
    }

    public static Workbook get2007Workbook(InputStream is) {
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            return wb;
        }
        return wb;
    }

    /**
     * 获取表格中数据
     *
     * @param inputStream
     * @param skipFirstRow
     * @param rowLimit
     * @return 列名-数据值
     */
    public static List<Map<String, String>> getData(InputStream inputStream, boolean skipFirstRow, int rowLimit) throws IOException {
        long start = System.currentTimeMillis();
        Map<Integer, String> columnIdxNameMap = new HashMap<>(8);
        List<Map<String, String>> mapList = new ArrayList<>();
        Workbook workbook = null;
        ByteArrayOutputStream baos = null;
        InputStream stream1 = null;
        InputStream stream2 = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            stream1 = new ByteArrayInputStream(baos.toByteArray());
            workbook = get2003Workbook(stream1);
            if (workbook == null) {
                stream2 = new ByteArrayInputStream(baos.toByteArray());
                workbook = get2007Workbook(stream2);
            }
            //目前读取一个sheet
            Sheet sheetAt = workbook.getSheetAt(0);
            int lastRowNum = sheetAt.getLastRowNum();
            if (!skipFirstRow) {
                lastRowNum++;
            }
            if (lastRowNum > rowLimit) {
                throw new RuntimeException("超范围");
            }
            for (Row row : sheetAt) {
                Map<String, String> unitData = new HashMap<>(2);
                mapList.add(unitData);
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    if (row.getRowNum() == 0) {
//						List<String> list = new ArrayList<>();
                        String stringCellValue = cell.getStringCellValue();
//						dataMap.put(stringCellValue, list);
                        columnIdxNameMap.put(columnIndex, stringCellValue);
                        if (skipFirstRow) {
                            //如果跳过第一行 删除第一个元素
                            mapList.remove(unitData);
                            continue;
                        }
                    }
                    int cellType = row.getCell(columnIndex).getCellType();
                    if (cellType == Cell.CELL_TYPE_NUMERIC) {
                        Double text = row.getCell(columnIndex).getNumericCellValue();
//						dataMap.get(columnIdxNameMap.get(columnIndex)).add("" + text.longValue());
                        unitData.put(columnIdxNameMap.get(columnIndex), "" + text.longValue());
                    } else if (cellType == Cell.CELL_TYPE_STRING) {
                        String text = row.getCell(columnIndex).getStringCellValue().trim();
//						dataMap.get(columnIdxNameMap.get(columnIndex)).add(text);
                        unitData.put(columnIdxNameMap.get(columnIndex), text);
                    } else {
                        throw new RuntimeException("第" + row.getRowNum() + "数据格式错误");
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析Excel异常");
            throw e;
        } finally {
            if (workbook != null) {
                workbook = null;
            }
            if (inputStream != null) {
                inputStream = null;
            }
            if (baos != null) {
                baos = null;
            }
            if (stream1 != null) {
                stream1 = null;
            }
            if (stream2 != null) {
                stream2 = null;
            }
        }
        log.info("excel 耗时(ms): {}", System.currentTimeMillis() - start);
        return mapList;
    }

    public static HSSFWorkbook createTemplate(List<String> columnNames) {
        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(columnNames.get(i));
        }
        return wb;
    }
}
