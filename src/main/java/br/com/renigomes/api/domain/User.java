package br.com.renigomes.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Data // Gera os métodos getters, setters, equals, hashCode e toString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
}
