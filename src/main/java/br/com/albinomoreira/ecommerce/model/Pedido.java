package br.com.albinomoreira.ecommerce.model;

import br.com.albinomoreira.ecommerce.enums.StatusPedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDateTime dataPedido;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    private BigDecimal total;
    @Enumerated(EnumType.STRING)

    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedidos;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;
}
