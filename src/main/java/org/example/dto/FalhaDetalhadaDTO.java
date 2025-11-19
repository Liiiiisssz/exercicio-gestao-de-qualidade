package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FalhaDetalhadaDTO {
    private Long idFalha;
    private LocalDateTime dataHoraOcorrencia;
    private String descricao;
    private String criticidade;
    private String status;
    private BigDecimal tempoParadaHoras;

    private Long idEquipamento;
    private String nomeEquipamento;
    private String numeroDeSerie;
    private String areaSetor;
    private String statusOperacional;

    private Long idAcao;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String responsavel;
    private String descricaoAcao;

    public FalhaDetalhadaDTO(Long idFalha, LocalDateTime dataHoraOcorrencia,
                             String descricao, String criticidade, String status,
                             BigDecimal tempoParadaHoras, Long idEquipamento,
                             String nomeEquipamento, String numeroDeSerie, String areaSetor,
                             String statusOperacional, Long idAcao, LocalDateTime dataHoraInicio,
                             LocalDateTime dataHoraFim, String responsavel, String descricaoAcao) {
        this.idFalha = idFalha;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
        this.idEquipamento = idEquipamento;
        this.nomeEquipamento = nomeEquipamento;
        this.numeroDeSerie = numeroDeSerie;
        this.areaSetor = areaSetor;
        this.statusOperacional = statusOperacional;
        this.idAcao = idAcao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoAcao = descricaoAcao;
    }

    public Long getIdFalha() {
        return idFalha;
    }

    public void setIdFalha(Long idFalha) {
        this.idFalha = idFalha;
    }

    public LocalDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(BigDecimal tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(Long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
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

    public Long getIdAcao() {
        return idAcao;
    }

    public void setIdAcao(Long idAcao) {
        this.idAcao = idAcao;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }
}