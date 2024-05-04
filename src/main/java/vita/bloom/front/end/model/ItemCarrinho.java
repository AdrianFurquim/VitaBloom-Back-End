package vita.bloom.front.end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCarrinho {
    /**
     * Id para cada item composto no carrinho para organização.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Para tabela segundaria de muitos para um.
     */
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    /**
     * Quantidade do produto.
     */
    private int quantidade;

    // Contrutor vazio para JDBC.
    public ItemCarrinho(){

    }

    /**
     * @param produto
     * @param quantidade
     *  Contrutor de ItemCarrinho
     */
    public ItemCarrinho(Produto produto, int quantidade) {        
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @param quantidade
     *  Funcionalidade para adicionar ou remover a quantidade de um item do carrinho.
     */
    public void addQuantidadeProduto(int quantidade){
        this.quantidade = (this.quantidade + quantidade);
    }

}
