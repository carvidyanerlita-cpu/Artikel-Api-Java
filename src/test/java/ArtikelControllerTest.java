package com.example.artikelapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles; // <-- Pastikan terimport
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.artikelapi.repository.ArtikelRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ArtikelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtikelRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void testCreateArtikel() throws Exception {
        Artikel artikel = new Artikel("Belajar Spring Boot", "Ini adalah artikel tentang Spring Boot");

        mockMvc.perform(post("/api/artikel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(artikel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.judul", is("Belajar Spring Boot")))
                .andExpect(jsonPath("$.totalView", is(0)));
    }

    @Test
    void testReadArtikel() throws Exception {
        Artikel artikel = repository.save(new Artikel("Judul Read", "Konten Read"));

        mockMvc.perform(get("/api/artikel/" + artikel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.judul", is("Judul Read")));
    }

    @Test
    void testUpdateArtikel() throws Exception {
        Artikel artikel = new Artikel("Judul Lama", "Konten Lama");
        artikel.setTotalView(10L);
        artikel = repository.save(artikel);

        Artikel updatePayload = new Artikel("Judul Baru", "Konten Baru");

        mockMvc.perform(put("/api/artikel/" + artikel.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.judul", is("Judul Baru")))
                .andExpect(jsonPath("$.totalView", is(10)));
    }

    @Test
    void testDeleteArtikel() throws Exception {
        Artikel artikel = repository.save(new Artikel("Judul Hapus", "Konten Hapus"));

        mockMvc.perform(delete("/api/artikel/" + artikel.getId()))
                .andExpect(status().isOk());

        assertFalse(repository.existsById(artikel.getId()));
    }
}}