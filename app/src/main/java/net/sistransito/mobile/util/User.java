package net.sistransito.mobile.util;
import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by GaziRimon on 9/5/2015.
 *
 */
public class User implements InstanceCreator<User> {

    /*
    * user login information this class contain the user data when he login
    * if status is 1 master user
    * if status is 2 normal user
    *
    * */

    // unicque id is matricula
    private String nome;
    private String matricula;
    private String nome_orgao;
    private String codigo_orgao;
    private String status;
    private String success;
    private String profile_image;

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
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

    public String getNome_orgao() {
        return nome_orgao;
    }

    public void setNome_orgao(String nome_orgao) {
        this.nome_orgao = nome_orgao;
    }

    public String getCodigo_orgao() {
        return codigo_orgao;
    }

    public void setCodigo_orgao(String codigo_orgao) {
        this.codigo_orgao = codigo_orgao;
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
        return nome+" "+matricula+" "+nome_orgao+" "+codigo_orgao+" "+status+" "+success+" "+profile_image;
    }
}
