package com.rzanetti.liquid.democracy.topic;

import jakarta.persistence.Embeddable;

@Embeddable
public class Topic {
    private String saude;
    private String educacao;
    private String seguranca;
    private String tecnologia;
    private String meioambiente;
}

