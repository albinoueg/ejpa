package br.com.albinomoreira.ecommerce.listener;

import br.com.albinomoreira.ecommerce.model.Pedido;
import br.com.albinomoreira.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido){
        if(pedido.isPago() && pedido.getNotaFiscal() == null){
            notaFiscalService.gerar(pedido);
        }
    }
}
