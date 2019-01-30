package net.sistran.mobile.util;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;


public class User implements InstanceCreator<User> {

    private String nome;
    private String matricula;
    private String nomeOrgao;
    private String codigoOrgao;
    private String status;
    private String success;
    private String profileImage;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNomeOrgao() {
        return nomeOrgao;
    }

    public void setNomeOrgao(String nomeOrgao) {
        this.nomeOrgao = nomeOrgao;
    }

    public String getCodigoOrgao() {
        return codigoOrgao;
    }

    public void setCodigoOrgao(String codigoOrgao) {
        this.codigoOrgao = codigoOrgao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public User createInstance(Type type) {
        return new User();
    }

    @Override
    public String toString() {
        return nome+" "+matricula+" "+ nomeOrgao +" "+ codigoOrgao +" "+status+" "+success+" "+ profileImage;
    }
}
