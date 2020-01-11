package com.cheerup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheerup.constant.ErrorMessage;
import com.cheerup.dto.ResponseMessage;

@ControllerAdvice
@RestController
public class SuperController {
	
	@ExceptionHandler({UserExistsException.class,NotFoundException.class})
	public ResponseEntity<ResponseMessage> handleAuthenticateException(Exception ex) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setStatus("FAILED");
		
		if(ex instanceof UserExistsException) {
			responseMessage.setMessageCode(ErrorMessage.CHEERUP001.toString());
		    responseMessage.setMessage(ex.getMessage());
	     }
		
		if(ex instanceof NotFoundException) {
			responseMessage.setMessageCode(ErrorMessage.CHEERUP002.toString());
		    responseMessage.setMessage(ErrorMessage.CHEERUP002.getMessage());
	     }
		
		return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.BAD_REQUEST);

}
	
	@RequestMapping("success")
	public ResponseMessage handleSuccess() {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setMessageCode(ErrorMessage.CHEERUP000.toString());
		responseMessage.setMessage(ErrorMessage.CHEERUP000.getMessage());
		responseMessage.setStatus("SUCCESS");
		return responseMessage;
	}
}

