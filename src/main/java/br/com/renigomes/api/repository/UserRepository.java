package br.com.renigomes.api.repository;

import br.com.renigomes.api.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer>{
    Optional<Users> findByEmail(String email);
}
