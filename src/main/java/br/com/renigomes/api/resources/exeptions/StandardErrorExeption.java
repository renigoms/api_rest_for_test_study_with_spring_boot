package br.com.renigomes.api.resources.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardErrorExeption{

    private LocalDateTime timestamp;
    private Integer status;
    private String error, path;

}


