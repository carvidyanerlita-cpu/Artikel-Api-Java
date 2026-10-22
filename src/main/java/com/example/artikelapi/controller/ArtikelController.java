package com.example.artikelapi.controller;

import com.example.artikelapi.entity.Artikel;
import com.example.artikelapi.repository.ArtikelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artikel")
public class ArtikelController {

    private final ArtikelRepository repository;

    public ArtikelController(ArtikelRepository repository) {
        this.repository = repository;
    }

    // 1. Create Artikel
    @PostMapping
    public Artikel create(@RequestBody Artikel artikel) {
        artikel.setTotalView(0L);
        return repository.save(artikel);
    }

    // 2. Read Semua Artikel
    @GetMapping
    public List<Artikel> getAll() {
        return repository.findAll();
    }

    // 3. Read Artikel Berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Artikel> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Update Artikel (totalView tidak berubah)
    @PutMapping("/{id}")
    public ResponseEntity<Artikel> update(@PathVariable Long id, @RequestBody Artikel data) {
        return repository.findById(id).map(artikel -> {
            artikel.setJudul(data.getJudul());
            artikel.setArtikel(data.getArtikel());
            return ResponseEntity.ok(repository.save(artikel));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. Delete Artikel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}