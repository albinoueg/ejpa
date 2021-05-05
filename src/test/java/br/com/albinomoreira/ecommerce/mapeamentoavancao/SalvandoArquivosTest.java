package br.com.albinomoreira.ecommerce.mapeamentoavancao;

import br.com.albinomoreira.ecommerce.EntityManagerTest;
import br.com.albinomoreira.ecommerce.model.NotaFiscal;
import br.com.albinomoreira.ecommerce.model.Pedido;
import br.com.albinomoreira.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarXmlNota() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregarNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertNotNull(notaFiscalVerificacao.getXml());
        Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);

/*        try {
            OutputStream out = new FileOutputStream(
                    Files.createFile(Paths.get(
                            System.getProperty("user.home") + "/xml.xml")).toFile());
            out.write(notaFiscalVerificacao.getXml());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }

    private static byte[] carregarNotaFiscal() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/nota-fiscal.xml").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void salvarFoto() throws IOException {
        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        produto.setFoto(carregarFoto());
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao.getFoto());
        Assert.assertTrue(produtoVerificacao.getFoto().length > 0);

    }

    private static byte[] carregarFoto() {
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/print.png").readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}