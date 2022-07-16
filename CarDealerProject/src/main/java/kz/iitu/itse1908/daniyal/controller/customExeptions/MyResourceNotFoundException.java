package kz.iitu.itse1908.daniyal.controller.customExeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MyResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3156815846745801694L;

    private transient ResponseEntity<ApiResponse> apiResponse;

    public MyResourceNotFoundException(ResponseEntity<ApiResponse> apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ResponseEntity<ApiResponse> getApiResponse() {
        return apiResponse;
    }
}
