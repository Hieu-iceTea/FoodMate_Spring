package Hieu_iceTea.FoodMate_Spring.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantRestExceptionHandler {

    //Add an exception handler for EmployeeNotFoundException
    @ExceptionHandler
    public ResponseEntity<RestaurantErrorResponse> handlerException(RestaurantNotFoundException employeeNotFoundException) {

        //Create EmployeeErrorResponse
        RestaurantErrorResponse employeeErrorResponse = new RestaurantErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                employeeNotFoundException.getMessage(),
                System.currentTimeMillis()
        );

        // return ResponseEntity
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.NOT_FOUND);

    }

    // Add another exception handler ... to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<RestaurantErrorResponse> handlerException(Exception exception) {

        //Create EmployeeErrorResponse
        RestaurantErrorResponse employeeErrorResponse = new RestaurantErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        // return ResponseEntity
        return new ResponseEntity<>(employeeErrorResponse, HttpStatus.BAD_REQUEST);

    }

}
