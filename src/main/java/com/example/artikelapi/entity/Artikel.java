package com.example.artikelapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "artikel")
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String judul;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String artikel;

    private Long totalView = 0L;

    public Artikel() {}

    public Artikel(String judul, String artikel) {
        this.judul = judul;
        this.artikel = artikel;
        this.totalView = 0L;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getArtikel() { return artikel; }
    public void setArtikel(String artikel) { this.artikel = artikel; }

    public Long getTotalView() { return totalView; }
    public void setTotalView(Long totalView) { this.totalView = totalView; }
}