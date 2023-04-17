package com.gabriel.valstore.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "skin")
public class Skin {

    public static final int TIPO_PISTOLA = 0;
    public static final int TIPO_FUZIL = 1;
    public static final int TIPO_RIFLE = 2;
    public static final int TIPO_FACA = 3;
    public static final int TIPO_SNIPER = 4;

    public static final int RARIDADE_COMUM = 0;
    public static final int RARIDADE_INCOMUM = 1;
    public static final int RARIDADE_RARO = 2;
    public static final int RARIDADE_EPICO = 3;
    public static final int RARIDADE_LENDARIO = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nonnull
    @Column(nullable = false, length = 100)
    private String nome;

    @Nonnull
    @Column(nullable = false)
    private Double valorantPoints;

    @Nonnull
    @Column(nullable = false)
    private Integer tipo;

    @Nonnull
    @Column(nullable = false)
    private Integer raridade;

    @Nonnull
    @Lob
    private String urlImagem;

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Transient
    private MultipartFile imagem;


    public MultipartFile getImagem() {
        return imagem;
    }

    public void setImagem(MultipartFile imagem) {
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorantPoints() {
        return valorantPoints;
    }

    public void setValorantPoints(Double valorantPoints) {
        this.valorantPoints = valorantPoints;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getRaridade() {
        return raridade;
    }

    public void setRaridade(Integer raridade) {
        this.raridade = raridade;
    }
    

}
