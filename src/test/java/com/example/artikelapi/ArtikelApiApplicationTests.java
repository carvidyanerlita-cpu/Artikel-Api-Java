package com.example.artikelapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles; // <-- Tambahkan import ini

@SpringBootTest(classes = ArtikelApiApplication.class)
@ActiveProfiles("test")
class ArtikelApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
