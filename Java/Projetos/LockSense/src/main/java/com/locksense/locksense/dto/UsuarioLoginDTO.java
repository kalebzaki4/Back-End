package com.locksense.locksense.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO para login de usuário.
 */
public class UsuarioLoginDTO {

    @Email
    @NotBlank
    private String gmail;

    @NotBlank
    private String senha;

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
