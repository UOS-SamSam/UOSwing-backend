package uos.samsam.wing.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler
 * 컨트롤러에서 발생하는 Exception을 처리하는 클래스입니다.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    /**
     * BadRequest에 대한 Exception을 handling합니다.
     * @param e Exception
     * @return 오류 메시지
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Map<String, String> handleException(IllegalArgumentException e) {
        Map<String, String> map = new HashMap<>();
        map.put("errMsg", e.getMessage());
        return map;
    }
}
