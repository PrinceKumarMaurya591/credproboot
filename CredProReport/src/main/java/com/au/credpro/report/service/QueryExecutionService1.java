package com.au.credpro.report.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.au.credpro.report.confguration.DatabaseProperties;
import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.exception.UnauthorizedException;
import com.au.credpro.report.repository.QueryListRepository;



@Service
public class QueryExecutionService1 {

	@Autowired
	private QueryListRepository queryListRepository;

	 @Autowired
	    private DatabaseProperties databaseProperties;

	    @Autowired
	    private DataSource dataSource;

	    public Flux<ServerSentEvent<ByteArrayResource>> executeQueryAndGetExcelAsStream(Long queryId, Long userAuId) {
	        QueryList queryList = queryListRepository.findByQueryId(queryId);
//	                .orElseThrow(() -> new NotFoundException("Query not found"));

	      
	            String query = queryList.getQuery();
	            return Flux.create(sink -> {
	                try (Connection connection = dataSource.getConnection();
	                     Statement statement = connection.createStatement();
	                     ResultSet resultSet = statement.executeQuery(query)) {

	                    int columnCount = resultSet.getMetaData().getColumnCount();
	                    Workbook workbook = new XSSFWorkbook();
	                    Sheet sheet = workbook.createSheet("QueryResult");

	                    // Create headers
	                    Row headerRow = sheet.createRow(0);
	                    for (int i = 1; i <= columnCount; i++) {
	                        String columnName = resultSet.getMetaData().getColumnName(i);
	                        Cell cell = headerRow.createCell(i - 1);
	                        cell.setCellValue(columnName);
	                    }

	                    int rowIndex = 1;
	                    while (resultSet.next()) {
	                        Row dataRow = sheet.createRow(rowIndex);
	                        for (int i = 1; i <= columnCount; i++) {
	                            Object columnValue = resultSet.getObject(i);
	                            Cell cell = dataRow.createCell(i - 1);
	                            cell.setCellValue(columnValue != null ? columnValue.toString() : "");
	                        }

	                        rowIndex++;
	                    }

	                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	                    workbook.write(byteArrayOutputStream);
	                    byte[] excelBytes = byteArrayOutputStream.toByteArray();

	                    HttpHeaders headers = new HttpHeaders();
	                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	                    headers.setContentDisposition(ContentDisposition.attachment().filename("query_result.xlsx").build());

	                    ByteArrayResource resource = new ByteArrayResource(excelBytes);
	                    ServerSentEvent<ByteArrayResource> sse = ServerSentEvent.builder(resource)
	                            .event("excel-chunk")
	                            .build();

	                    sink.next(sse);
	                    sink.complete();

	                } catch (Exception e) {
	                    sink.error(e);
	                }
	            });
	        }

}
    
    
    
    


