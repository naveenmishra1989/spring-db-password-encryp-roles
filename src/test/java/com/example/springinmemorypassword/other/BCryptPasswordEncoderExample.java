package com.example.springinmemorypassword.other;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;

@Log4j2
public class BCryptPasswordEncoderExample {

  private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y);

  @ParameterizedTest
  @ValueSource(strings = {"sonu", "naveen", "mishra"})
  void encode(String password) {
    final String encodePassword = bCryptPasswordEncoder.encode(password);
    final boolean matches = bCryptPasswordEncoder.matches(password, encodePassword);
    log.info(password+": encoded :"+encodePassword);
    assertTrue(matches);
  }

}
