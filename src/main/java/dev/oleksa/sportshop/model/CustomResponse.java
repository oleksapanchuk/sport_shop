package dev.oleksa.sportshop.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomResponse {
    protected String timeStamp;
    protected String path;
    protected String requestMethod;
    protected HttpStatus status;
    protected Integer statusCode;
    protected String message;
    protected String errorMessage;
    protected Map<?, ?> data;
}
