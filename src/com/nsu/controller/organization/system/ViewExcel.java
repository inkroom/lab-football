package com.nsu.controller.organization.system;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class ViewExcel extends AbstractExcelView {


    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        String fileName = "赛事信息表excel.xls";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));
        OutputStream outputStream = response.getOutputStream();

        List<Map<String, Object>> list = (List<Map<String, Object>>) model.get("list");
        // 产生Excel表头
        HSSFSheet sheet = workbook.createSheet("赛程基本信息");
        HSSFRow header = sheet.createRow(0);



        // 产生标题列
        header.createCell(0).setCellValue("主队");
        header.createCell(1).setCellValue("赛程开始时间");
        header.createCell(2).setCellValue("赛程结束时间");
        header.createCell(3).setCellValue("地区");
        header.createCell(4).setCellValue("客队");
        header.createCell(5).setCellValue("现场管理员账号");
        header.createCell(6).setCellValue("现场管理员密码");

        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));
        int rowNumber = 1;

        for (int i = 0; i < list.size(); i++) {
            //产生标题列
            HSSFRow row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue((String) list.get(i).get("R_H_TEAM_NAME"));//**//*主队ID*//**//*
            row.createCell(1).setCellValue((String) list.get(i).get("R_START_TIME"));//**//*赛程开始时间*//**//*
            row.createCell(2).setCellValue((String) list.get(i).get("R_END_TIME"));//**//*赛程开始时间*//**//*
            row.createCell(3).setCellValue((String) list.get(i).get("R_REGION"));//**//*赛程开始时间*//**//*
            row.createCell(4).setCellValue((String) list.get(i).get("R_V_TEAM_NAME"));//**//*赛程结束时间*//**//*
            row.createCell(5).setCellValue((String) list.get(i).get("A_USERNAME"));//**//*现场管理员账号*//**//*
            row.createCell(6).setCellValue((String) list.get(i).get("A_PASSWORD"));//**//*现场管理员密码*//**//*

        }

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}




