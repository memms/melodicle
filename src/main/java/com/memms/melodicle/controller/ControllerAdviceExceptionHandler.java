//package com.memms.melodicle.controller;
//
//import com.memms.melodicle.domain.dto.ErrorResponseDTO;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.List;
//import java.util.Map;
//
//@ControllerAdvice
//public class ControllerAdviceExceptionHandler {
//
////    @ExceptionHandler({InvalidTransactionException.class, InvalidRequestException.class})
////    public ResponseEntity<ErrorResponseDTO> exceptionHandlerInvalidRequest(Exception ex) {
////        ErrorResponseDTO error = new ErrorResponseDTO();
////        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
////        error.setMessage(ex.getMessage());
////        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
////    }
////
////    @ExceptionHandler(TransactionNotFoundException.class)
////    public ResponseEntity<ErrorResponseDTO> exceptionHandlerTransactionNotFound(Exception ex) {
////        ErrorResponseDTO error = new ErrorResponseDTO();
////        error.setErrorCode(HttpStatus.NOT_FOUND.value());
////        error.setMessage(ex.getMessage());
////        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
////    }
//
//    //  The exception thrown when @Validated fails
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, List<String>>> exceptionHandlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).toList();
//        return new ResponseEntity<>(Map.of("An Error Occurred:", errors), HttpStatus.BAD_REQUEST);
//    }
//
//    //  Thrown if exception during database access
//    @ExceptionHandler(DataAccessException.class)
//    public ResponseEntity<ErrorResponseDTO> exceptionHandlerDataAccess(DataAccessException ex) {
//        ErrorResponseDTO error = new ErrorResponseDTO();
//        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.setMessage("An error occurred during database access.");
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponseDTO> exceptionHandlerInvalidFormat(InvalidFormatException ex) {
//        ErrorResponseDTO error = new ErrorResponseDTO();
//        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//        error.setMessage("Invalid request body format, Please check and try again.");
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception ex) {
//        ErrorResponseDTO error = new ErrorResponseDTO();
//        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.setMessage("An error occurred. Please try again.");
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
