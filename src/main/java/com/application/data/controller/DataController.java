package com.application.data.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.data.model.Data;
import com.application.data.repo.DataRepo;


@RestController
@RequestMapping("/data")
public class DataController {
	@Autowired
	 private DataRepo dataRepo;
	
	
	@PostMapping
	@RequestMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public String add(@RequestBody Data data) {
		System.out.println(data);
		dataRepo.save(data);
		
		
        return "Item Added To Catalog";
	}
	@GetMapping("/getall")
	public Iterable<Data> getall(){
		System.out.println("In controller");
		Iterable<Data> allData = dataRepo.findAll();
		System.out.println(allData);
		String value=String.valueOf(allData) ;
		 try {  
            String fileName = "C:\\test\\sample.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet sheet = workbook.createSheet();
	            int rownum =0;
	       
	                HSSFRow row = sheet.createRow(rownum++);
	                short cellNum = 0;
	                HSSFCell cell1 = row.createCell(cellNum++);
	                cell1.setCellValue(value);
		            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
		            workbook.write(fileOutputStream); 
		            fileOutputStream.close();
		            System.out.println("Sample.xls created successfully on disk."); 
		        }catch (Exception e) { 
		            e.printStackTrace(); 
		        }
		return dataRepo.findAll(); 
		
		
	}
	@GetMapping("/getbyid/{id}")
	public Optional<Data> getbyid(@PathVariable  int id){
		return dataRepo.findById(id);
		
		
	}}
//	   @RequestMapping(value="/writeData", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<String> writeJSONDataToFile(@RequestBody(required=true) Map<String, String> input){
//	        try {  
//	            String fileName = "C:\\test\\sample.xls";
//	            HSSFWorkbook workbook = new HSSFWorkbook();
//	            HSSFSheet sheet = workbook.createSheet();
//	            int rownum =0;
//	            for (Entry<String, String> entry : input.entrySet()) {
//	                HSSFRow row = sheet.createRow(rownum++);
//	                short cellNum = 0;
//	                HSSFCell cell1 = row.createCell(cellNum++);
//	                cell1.setCellValue(entry.getKey());
//	                HSSFCell cell2 = row.createCell(cellNum++);
//	                cell2.setCellValue(entry.getValue());
//	            }
//	            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
//	            workbook.write(fileOutputStream); 
//	            fileOutputStream.close();
//	            System.out.println("Sample.xls created successfully on disk."); 
//	        }catch (Exception e) { 
//	            e.printStackTrace(); 
//	        }
//	        return new ResponseEntity<String>("Create", HttpStatus.CREATED);
//	    }
//
//
//	    @RequestMapping(value="/writeData", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<String> updateJSONDataToFile(@RequestBody(required=true) Map<String, String> input){
//	        try {  
//	            String fileName = "C:\\test\\sample.xls";
//	            HSSFWorkbook workbook = null;
//	            FileInputStream fileInputStream = null;
//	            HSSFSheet sheet = null;
//	            File file = new File(fileName);
//	            if(file.exists()){
//	                fileInputStream = new FileInputStream(file); 
//	                workbook = new HSSFWorkbook(fileInputStream); //Reading from file
//	                sheet = workbook.getSheetAt(0);
//	            }else{
//	                return new ResponseEntity<String>("File doesnt Exists", HttpStatus.BAD_REQUEST);
//	            }
//	            int rownum =sheet.getLastRowNum();
//	            for (Entry<String, String> entry : input.entrySet()) {
//	                HSSFRow row = sheet.createRow(++rownum);
//	                short cellNum = row.getLastCellNum();
//	                HSSFCell cell1 = row.createCell(++cellNum);
//	                cell1.setCellValue(entry.getKey());
//	                HSSFCell cell2 = row.createCell(++cellNum);
//	                cell2.setCellValue(entry.getValue());
//	            }
//	            if(fileInputStream!=null)
//	                fileInputStream.close();
//
//	            FileOutputStream fileOutputStream = new FileOutputStream(file);
//	            workbook.write(fileOutputStream); 
//	            fileOutputStream.close();
//	            System.out.println("Sample.xls written/Updated successfully on disk."); 
//	        }catch (Exception e) { 
//	            e.printStackTrace(); 
//	        }
//	        return new ResponseEntity<String>("updated successfully", HttpStatus.OK);
//	    }
//	}

	

