package com.se.courses.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.se.courses.entity.Student;
import com.se.courses.exception.CourseException;

public class ExcelUtils {
	// 学号应为大于4位的整数
	private static final Pattern numberP = Pattern.compile("^\\d{4,}$");
	public static List<Student> getExcel(InputStream is) {
		try {
			Workbook workbook = WorkbookFactory.create(is);
			return getRow(workbook.getSheetAt(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CourseException("文件操作错误", e);
		}
	}

	private static List<Student> getRow(Sheet sheet) {
		List<Student> students = new ArrayList<>();
		// 从第6行开始读取
		for (int rowIndex = 5; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				break;
			}
			Cell number = row.getCell(1);
			number.setCellType(CellType.STRING);
			// 第3列是学号
			if (!numberP.matcher(number.getStringCellValue()).matches()) {
				continue;
			}
			
			Student student = new Student();
			student.setNumber(number.getStringCellValue());
			
			Cell nameCell = row.getCell(2);
			nameCell.setCellType(CellType.STRING);
			student.setName(nameCell.getStringCellValue());
			Cell clazz = row.getCell(4);
			clazz.setCellType(CellType.STRING);
			student.setClazz(clazz.getStringCellValue());
			students.add(student);
		}
		return students;
	}

}
