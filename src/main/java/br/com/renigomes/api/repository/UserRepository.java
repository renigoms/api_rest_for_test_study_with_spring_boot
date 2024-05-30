package br.com.renigomes.api.repository;

import br.com.renigomes.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}
