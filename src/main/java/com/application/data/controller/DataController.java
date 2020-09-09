package com.application.data.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Header;
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
		List<Data> allData = dataRepo.findAll();
		
		
		System.out.println(allData);
				 try {  
            String fileName = "C:\\test\\sample.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
	            HSSFSheet sheet = workbook.createSheet();
	            int rownum =0;
	       
	            
	            
	            HSSFRow header = sheet.createRow(0);
	                header.createCell(0).setCellValue("Id");
	                	                 
	                header.createCell(1).setCellValue("UserName");
	                header.createCell(2).setCellValue("Description");
	               
	                int rowCount = 1;
	                for (Data adata : allData) {
	                    HSSFRow aRow = sheet.createRow(rowCount++);
	                    aRow.createCell(0).setCellValue(adata.getId());
	                    aRow.createCell(1).setCellValue(adata.getName());
	                    aRow.createCell(2).setCellValue(adata.getDescription());
	                   
	                }
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

