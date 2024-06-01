package br.com.renigomes.api.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
//    @JsonIgnore //Ignora os gets desse atributo
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // libera o acesso para escrita
    private String password;
}
