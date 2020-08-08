package com.CucumberCraft.supportLibraries;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private Workbook workbook = null;
	private Sheet sheet = null;

	// create new excel file
	public Workbook creatExcelFile(String filePath) throws Exception {
		workbook = new XSSFWorkbook();
		return workbook;
	}

	public Sheet createNewSheet(Workbook workbook) throws Exception {
		Sheet sheet = workbook.createSheet();
		return sheet;
	}

	public Sheet createNewSheet(Workbook workbook, String sheetName) throws Exception {
		Sheet sheet = workbook.createSheet(sheetName);
		return sheet;
	}

	public void createNewRow(int rowIndex) throws Exception {
		sheet.createRow(rowIndex);
	}

	public Cell createNewCell(Row row, int cellIndex) throws Exception {
		Cell cell = row.createCell(cellIndex);
		return cell;
	}

	// write file excel from excelFilePath, file exists
	public Workbook getWorkbook(String excelFilePath, int SheetIndex) throws IOException {

		try {
			if (excelFilePath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(excelFilePath);
			} else if (excelFilePath.endsWith("xls")) {
				workbook = new HSSFWorkbook();
			} else {
				throw new IllegalArgumentException("The specified file is not Excel file");
			}
			sheet = workbook.getSheetAt(SheetIndex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	// Get Workbook
	public void setWorkbook(InputStream inputStream, String excelFilePath, int SheetIndex) throws IOException {
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		sheet = workbook.getSheetAt(SheetIndex);
	}

	// Create CellStyle for header
	public CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	// Write footer
	public void writeFooter(Sheet sheet, int rowIndex, int colIndex) {
		// Create row
		Row row = sheet.createRow(rowIndex);
		Cell cell = row.createCell(colIndex, CellType.FORMULA);
		cell.setCellFormula("SUM(E2:E6)");
	}

	// Auto resize column width
	public void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	// Create output file
	public void createOutputFile(String excelFilePath) throws IOException {
		OutputStream os = new FileOutputStream(excelFilePath);
		try {
			workbook.write(os);
			workbook.close();
			os.close();
		} catch (Exception e) {
			os.close();
			System.out.println(e.getMessage());
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public String getCellData(int rowNum, int colNum) throws Exception {
		try {
			String cellData = "";
			Cell cell = sheet.getRow(rowNum).getCell(colNum);
			DataFormatter dataFormatter = new DataFormatter();
			cellData = dataFormatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	public String getCellData(Cell cell) throws Exception {
		try {
			String cellData = "";
			DataFormatter dataFormatter = new DataFormatter();

			// cell.setCellType(CellType.STRING);
			cellData = dataFormatter.formatCellValue(cell); // cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	public int getRowNum() throws Exception {
		int rowNum = sheet.getLastRowNum();
		return rowNum;
	}

	public int getColNum() throws Exception {
		int colNum = sheet.getRow(0).getLastCellNum();
		return colNum;
	}

	public void setCellValue(String value, int rowNum, int colNum) {
		try {
			Cell cell = sheet.getRow(rowNum).getCell(colNum);
			// CellType cellType = cell.getCellType();
			cell.setCellType(CellType.STRING);
			cell.setCellValue(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Could not set cell value: Row " + String.valueOf(rowNum) + " Col "
					+ String.valueOf(colNum));
		}
	}

	public void setCellValue(Cell cell, String value) throws Exception {
		cell.setCellType(CellType.STRING);
		cell.setCellValue(value);
	}

	public Row getRow(Sheet excelWSheet, int rowNum) throws Exception {
		try {
			Row row = excelWSheet.getRow(rowNum);
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setCellStyle(Sheet excelWSheet, int rowNum, int colNum, String colorCell) {
		Row row = null;
		Cell cell = null;
		try {
			row = excelWSheet.getRow(rowNum);
			cell = row.getCell(colNum);
			CellStyle style = excelWSheet.getWorkbook().createCellStyle();
			switch (colorCell) {
			case "GREEN":
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				break;
			case "RED":
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				break;
			case "GREY":
				style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				break;
			}

			style.setBorderTop(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);

			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			cell.setCellStyle(style);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void setCellStyle(Sheet excelWSheet, Cell cell, String colorCell) {
		try {

			CellStyle style = excelWSheet.getWorkbook().createCellStyle();
			switch (colorCell) {
			case "GREEN":
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				break;
			case "RED":
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				break;
			case "GREY":
				style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				break;
			}

			style.setBorderTop(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);

			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// Create font
			Font font = excelWSheet.getWorkbook().createFont();
			font.setFontName("Arial");
			font.setFontHeightInPoints((short) 8); // font size
			style.setFont(font);
			cell.setCellStyle(style);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Cell> getValueOfColumn(int indexCol) throws Exception {
		int rowNum = getRowNum();
		List<Cell> listCell = new ArrayList<Cell>();
		for (int i = 0; i < rowNum; i++) {
			Cell cell = sheet.getRow(i).getCell(indexCol);
			listCell.add(cell);
		}
		return listCell;
	}

	public HashMap<String, HashMap<Integer, String>> mapColumnBetweenWebAndExcel(Sheet excelWSheet,
			List<String> columnsOnWeb, List<String> colMap) throws Exception {
		HashMap<String, HashMap<Integer, String>> mapColumn = new HashMap<String, HashMap<Integer, String>>();
		try {
			int rowIndex = 0;
			for (int i = 0; i < colMap.size(); i++)// This index is the same index columnsOnWeb
			{
				for (int j = 0; j < getColNum(); j++) {
					String cellValue = getCellData(rowIndex, j);
					if (cellValue.contains(colMap.get(i))) {
						HashMap<Integer, String> indexAndNameCol = new HashMap<Integer, String>();
						indexAndNameCol.put(j, cellValue);
						mapColumn.put(columnsOnWeb.get(i), indexAndNameCol);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapColumn;
	}

	public int getIndexColInExcelByName(String colName) throws Exception {
		int rowIndex = 0;
		for (int i = 0; i < getColNum(); i++) {
			String cellValue = getCellData(rowIndex, i);
			if (cellValue.equals(colName)) {
				return i;
			}
		}
		return -1;
	}

	public String toString(String expectValue, String actualValue) throws Exception {
		return expectValue + " # " + actualValue;
	}

	public int getRowCount() {
		try {
			return sheet.getPhysicalNumberOfRows();
			// return ExcelWSheet.getLastRowNum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public int getPhysicalRowCount() throws Exception {
		int rowCount = sheet.getPhysicalNumberOfRows();
		String value = null;
		for (int i = 1; i < rowCount; i++) {
			value = getCellData(i, 0);
			if (value.trim().equals(""))
				return i;
		}
		return -1;
	}

	public int getLastColumn(int rownum) {
		try {
			return sheet.getRow(rownum).getLastCellNum();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return -1;
	}

	public Map<String, String> getDataSetByRowIndex(int rowIndex) throws Exception {
		Map<String, String> dataset = new HashMap<String, String>();
		int colCount = getLastColumn(0);
		for (int i = 0; i < colCount; i++) {
			dataset.put(getCellData(0, i), getCellData(rowIndex, i));
		}
		return dataset;
	}
}
