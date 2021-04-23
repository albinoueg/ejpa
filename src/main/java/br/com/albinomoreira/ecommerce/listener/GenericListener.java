package br.com.albinomoreira.ecommerce.listener;

import javax.persistence.PostLoad;

public class GenericListener {

    @PostLoad
    public void logar(Object obj){
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
    }
}
