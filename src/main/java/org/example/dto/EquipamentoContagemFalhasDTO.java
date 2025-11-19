package org.example.dto;

public class EquipamentoContagemFalhasDTO {
    private Long id;

    private String nome;

    private String numeroDeSerie;

    private String areaSetor;
    private String statusOperacional;

    public EquipamentoContagemFalhasDTO(Long id, String nome, String numeroDeSerie, String areaSetor, String statusOperacional) {
        this.id = id;
        this.nome = nome;
        this.numeroDeSerie = numeroDeSerie;
        this.areaSetor = areaSetor;
        this.statusOperacional = statusOperacional;
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

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getAreaSetor() {
        return areaSetor;
    }

    public void setAreaSetor(String areaSetor) {
        this.areaSetor = areaSetor;
    }

    public String getStatusOperacional() {
        return statusOperacional;
    }

    public void setStatusOperacional(String statusOperacional) {
        this.statusOperacional = statusOperacional;
    }
}