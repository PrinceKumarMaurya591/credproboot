package com.au.credpro.report.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.au.credpro.report.entity.QueryList;
import com.au.credpro.report.entity.User;
import com.au.credpro.report.exception.NotFoundException;
import com.au.credpro.report.exception.UnauthorizedException;
import com.au.credpro.report.repository.QueryListRepository;

import com.au.credpro.report.service.QueryExecutionService1;
import com.au.credpro.report.service.UserService;

import reactor.core.publisher.Flux;


import org.springframework.core.io.Resource;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/credpro/user")
public class QueryExecutionController1 {

	@Autowired
	private QueryExecutionService1 queryExecutionService;
	@Autowired
	private UserService userService;

	@Autowired
	QueryListRepository queryListRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(QueryExecutionController1.class);
//
//	@GetMapping("/{userAuId}/execute-query/{queryId}")
//	public ResponseEntity<?> executeAssignedQueryAndDownloadExcel(@PathVariable Long userAuId,
//			@PathVariable Long queryId) throws IOException, UnauthorizedException {
//		try {
//			 logger.info("Executing executeAssignedQueryAndDownloadExcel for userAuId: {}, queryId: {}", userAuId, queryId);
//			User user = userService.getUserByUserAuId(userAuId);
//			Long userId = user.getUserId();
//			QueryList queryList = queryListRepository.findById(queryId)
//					.orElseThrow(() -> new NotFoundException("Query not found"));
//			// Check if the user is assigned to this query
//			if (!queryList.getUser().contains(user) ) {
//				throw new UnauthorizedException("You are not authorized to execute this query.");
//			}
//			Workbook workbook = queryExecutionService.executeAssignedQueryAndGetExcel(userId, queryId, null);
//
//			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//			workbook.write(byteArrayOutputStream);
//			workbook.close();
//
//			byte[] excelBytes = byteArrayOutputStream.toByteArray();
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//			headers.setContentDispositionFormData("attachment", "query_result.xlsx");
//			return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
//		} catch (UnauthorizedException e) {
//			 logger.warn("Unauthorized error occurred: {}", e.getMessage());
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
//		} catch (NotFoundException e) {
//			logger.warn("Query not found error occurred: {}", e.getMessage());
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query not found: " + e.getMessage());
//		} catch (IOException e) {
//			// Handle IO-related errors
//			logger.error("IO Error occurred: {}", e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IO Error: " + e.getMessage());
//		} catch (Exception e) {
//			 logger.error("Internal server error occurred: {}", e.getMessage());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Internal server error: " + e.getMessage());
//		}
//	}
	
	
	
	
	
	
	
	
//	 @GetMapping("/excel")
//	    public ResponseEntity<StreamingResponseBody> downloadDataAsExcel(@RequestParam String query) {
//	        return ResponseEntity.ok()
//	                .header("Content-Disposition", "attachment; filename=data.xlsx")
//	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//	                .body(queryExecutionService.generateExcelStream(query));
//	    }

//	  @GetMapping("/excel")
//	    public ResponseEntity<StreamingResponseBody> downloadDataAsExcel(@RequestParam Long queryId) {
		 
	 
	 @GetMapping("/{userAuId}/execute-query/{queryId}")
	 public ResponseEntity<Resource> executeQueryByUser(@PathVariable Long userAuId, @PathVariable Long queryId) {
	     Flux<ServerSentEvent<ByteArrayResource>> sseFlux = queryExecutionService.executeQueryAndGetExcelAsStream(queryId, userAuId);

	     // Collect SSE chunks and assemble the complete byte array
	     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	     sseFlux.map(ServerSentEvent::data)
	            .map(ByteArrayResource::getByteArray)
	            .doOnNext(t -> {
					try {
						byteArrayOutputStream.write(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})
	            .doOnError(error -> byteArrayOutputStream.reset())
	            .blockLast();  // Block to ensure all data is collected

	     byte[] completeExcelBytes = byteArrayOutputStream.toByteArray();

	     ByteArrayResource resource = new ByteArrayResource(completeExcelBytes);

	     HttpHeaders headers = new HttpHeaders();
	     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	     headers.setContentDisposition(ContentDisposition.attachment().filename("query_result.xlsx").build());

	     return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	 }

	 
	


	 
	 
	 
	 

	 
	 
	 
	 
	}
	
	
	
	
	
	
