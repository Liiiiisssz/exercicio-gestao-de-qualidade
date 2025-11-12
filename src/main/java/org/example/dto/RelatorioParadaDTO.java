package org.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelatorioParadaDTO {
    private Long falhaId;
    private String descricaoFalha;
    private String criticidade;
    private String statusFalha;

    private Long equipamentoId;
    private String nomeEquipamento;
    private String numeroDeSerie;
    private String areaSetor;

    private LocalDateTime dataHoraOcorrencia;
    private LocalDateTime dataHoraInicioCorrecao;
    private LocalDateTime getDataHoraFimCorrecao;

    private String responsavelCorrecao;
    private String descricaoAreaCorrecao;

    private BigDecimal tempoParadaHoras;

    public RelatorioParadaDTO(Long falhaId, String descricaoFalha, String criticidade,
                              String statusFalha, Long equipamentoId, String nomeEquipamento,
                              String numeroDeSerie, String areaSetor, LocalDateTime dataHoraOcorrencia,
                              LocalDateTime dataHoraInicioCorrecao, LocalDateTime getDataHoraFimCorrecao,
                              String responsavelCorrecao, String descricaoAreaCorrecao,
                              BigDecimal tempoParadaHoras) {
        this.falhaId = falhaId;
        this.descricaoFalha = descricaoFalha;
        this.criticidade = criticidade;
        this.statusFalha = statusFalha;
        this.equipamentoId = equipamentoId;
        this.nomeEquipamento = nomeEquipamento;
        this.numeroDeSerie = numeroDeSerie;
        this.areaSetor = areaSetor;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.dataHoraInicioCorrecao = dataHoraInicioCorrecao;
        this.getDataHoraFimCorrecao = getDataHoraFimCorrecao;
        this.responsavelCorrecao = responsavelCorrecao;
        this.descricaoAreaCorrecao = descricaoAreaCorrecao;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Long getFalhaId() {
        return falhaId;
    }

    public void setFalhaId(Long falhaId) {
        this.falhaId = falhaId;
    }

    public String getDescricaoFalha() {
        return descricaoFalha;
    }

    public void setDescricaoFalha(String descricaoFalha) {
        this.descricaoFalha = descricaoFalha;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatusFalha() {
        return statusFalha;
    }

    public void setStatusFalha(String statusFalha) {
        this.statusFalha = statusFalha;
    }

    public Long getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(Long equipamentoId) {
        this.equipamentoId = equipamentoId;
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

    public LocalDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public LocalDateTime getDataHoraInicioCorrecao() {
        return dataHoraInicioCorrecao;
    }

    public void setDataHoraInicioCorrecao(LocalDateTime dataHoraInicioCorrecao) {
        this.dataHoraInicioCorrecao = dataHoraInicioCorrecao;
    }

    public LocalDateTime getGetDataHoraFimCorrecao() {
        return getDataHoraFimCorrecao;
    }

    public void setGetDataHoraFimCorrecao(LocalDateTime getDataHoraFimCorrecao) {
        this.getDataHoraFimCorrecao = getDataHoraFimCorrecao;
    }

    public String getResponsavelCorrecao() {
        return responsavelCorrecao;
    }

    public void setResponsavelCorrecao(String responsavelCorrecao) {
        this.responsavelCorrecao = responsavelCorrecao;
    }

    public String getDescricaoAreaCorrecao() {
        return descricaoAreaCorrecao;
    }

    public void setDescricaoAreaCorrecao(String descricaoAreaCorrecao) {
        this.descricaoAreaCorrecao = descricaoAreaCorrecao;
    }

    public BigDecimal getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(BigDecimal tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }
}
