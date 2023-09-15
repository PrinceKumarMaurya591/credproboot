//package com.au.credpro.report.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//import javax.persistence.EntityNotFoundException;
//
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.au.credpro.report.entity.QueryList;
//import com.au.credpro.report.entity.User;
//import com.au.credpro.report.exception.NotFoundException;
//import com.au.credpro.report.exception.UnauthorizedException;
//import com.au.credpro.report.repository.QueryListRepository;
//import com.au.credpro.report.service.QueryExecutionService;
//import com.au.credpro.report.service.UserService;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/credpro/user")
//public class QueryExecutionController {
//
//	@Autowired
//	private QueryExecutionService queryExecutionService;
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	QueryListRepository queryListRepository;
//	
//	 private static final Logger logger = LoggerFactory.getLogger(QueryExecutionController.class);
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
//			Workbook workbook = queryExecutionService.executeAssignedQueryAndGetExcel(userId, queryId);
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
//	
//	
//	
//	
//	
//	
//	
//	
//	@GetMapping("/{adminAuId}/admin/execute-query/{queryId}")
//	public ResponseEntity<?> executeQueryByAdmin(@PathVariable Long adminAuId, @PathVariable Long queryId) {
//	    try {
//	        User admin = userService.getUserByUserAuId(adminAuId);
//	        
//	        if (!admin.getIsAdmin()) {
//	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to execute queries.");
//	        }
//
//	        Workbook workbook = queryExecutionService.executeAssignedQueryAndGetExcel(admin.getUserId(), queryId);
//
//	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//	        workbook.write(byteArrayOutputStream);
//	        workbook.close();
//
//	        byte[] excelBytes = byteArrayOutputStream.toByteArray();
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	        headers.setContentDispositionFormData("attachment", "query_result.xlsx");
//	        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
//	    } catch (EntityNotFoundException e) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Query not found: " + e.getMessage());
//	    } catch (UnauthorizedException e) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
//	    }
//	}
//
//	
//	
//	
//	
//	
//	
//	
//}