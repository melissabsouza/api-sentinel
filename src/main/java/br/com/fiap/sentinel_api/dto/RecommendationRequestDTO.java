package br.com.fiap.sentinel_api.dto;

import lombok.Data;

@Data
public class RecommendationRequestDTO {
    private String city;
    private boolean accessibility;
    private boolean acceptPets;
    private boolean withChildren;
    private boolean elderly;
    private boolean emergency;
}

