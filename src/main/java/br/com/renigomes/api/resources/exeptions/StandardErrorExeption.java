package br.com.renigomes.api.resources.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class StandardErrorExeption{

    private LocalDateTime timestamp;
    private Integer status;
    private String error, path;

}


