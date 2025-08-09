package com.GestorTareas.GestorTareas.dto;

import java.util.List;

public class ErrorDTO {
    private String description;
    private List<String> reasons;

    public List<String> getReasons() {
        return reasons;
    }

    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public ErrorDTO(String description, List<String> reasons){
        this.description = description;
        this.reasons = reasons;
    }
}
