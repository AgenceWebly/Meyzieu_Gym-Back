package webly.meyzieu_gym.back.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import webly.meyzieu_gym.back.common.exception.custom.RoleNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.UserAlreadyExistsException;
import webly.meyzieu_gym.back.common.exception.custom.UserNotFoundException;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<MessageResponse> handleRoleNotFoundException(RoleNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new MessageResponse("An unexpected error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return new ResponseEntity<>(new MessageResponse("You do not have permission to access this resource"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(new MessageResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
