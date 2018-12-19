package com.ljb.utils;

import com.ljb.model.ExportParams;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/24<br>
 * 描述: <br>
 */
public class ExcelUtils {

    public static byte[] exportFile(List<ExportParams.Prop> props, List<Map<String,Object>> dataList){
        List<String> keys=new ArrayList<>();
        for(ExportParams.Prop prop :props){
            keys.add(prop.getKey());
        }
        try{
            int index=0;//行标
            XSSFWorkbook workbook = new XSSFWorkbook();                        // 创建工作簿对象
            XSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setVerticallyCenter(true);
            sheet.setDefaultRowHeight((short) (22*20));
            XSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);//获取列头样式对象
            XSSFCellStyle style = getStyle(workbook);                    //单元格样式对象
            int columnNum = props.size();
            XSSFRow rowRowName = sheet.createRow(index++);                // 在索引1的位置创建行(最顶端的行开始的第二行)
            // 将列头设置到sheet的单元格中
            for(int n=0;n<columnNum;n++){
                sheet.autoSizeColumn(n);//设置自动列宽
                XSSFCell cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
                cellRowName.setCellType(CellType.valueOf("STRING"));                //设置列头单元格的数据类型
                XSSFRichTextString text = new XSSFRichTextString(props.get(n).getLabel());
                cellRowName.setCellValue(text);                                    //设置列头单元格的值
                cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
            }
            //将查询出的数据设置到sheet对应的单元格中
            for(int i=0;i<dataList.size();i++){
                Map<String,Object> map = dataList.get(i);//遍历每个对象
                XSSFRow row = sheet.createRow(index++);//创建所需的行数
                for(int j=0;j<columnNum;j++){
                    String value=map.getOrDefault(props.get(j).getKey(),"").toString();
                    XSSFCell  cell = null;   //设置单元格的数据类型
                    cell = row.createCell(j,CellType.valueOf("STRING"));
                    cell.setCellValue(value);
                    cell.setCellStyle(style);
                }
            }
            if(workbook !=null){
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                workbook.write(os);
                return os.toByteArray();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*
     * 列头单元格样式
     */
    public static XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("宋体");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(false);
        return style;

    }

    /*
     * 列数据信息单元格样式
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("宋体");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setWrapText(false);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;

    }
}
