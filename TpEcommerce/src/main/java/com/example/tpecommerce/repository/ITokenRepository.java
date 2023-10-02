package com.example.tpecommerce.repository;


import com.example.tpecommerce.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ITokenRepository extends JpaRepository<Token, Integer> {

  @Query(value = """
      select t from Token t inner join Client u\s
      on t.client.id = u.id\s
      where u.id = :id and (t.isExpired = false or t.isRevoked = false)\s
      """)
  List<Token> findAllValidTokenByUser(Integer id);

  Optional<Token> findByUserToken(String token);


}
