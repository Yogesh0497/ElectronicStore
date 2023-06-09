package com.yogesh.electronicStore.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;

    private boolean success;

    //private HttpStatus status;
}
