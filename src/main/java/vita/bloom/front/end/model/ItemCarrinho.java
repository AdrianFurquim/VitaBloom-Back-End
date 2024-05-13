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
     *  Id do ItemCarrinho gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *  Tabela com chaves segundarias para ligamento do ItemCarrinho com o Produto.
     */
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    /**
     *  Quantidade de determinado produto.
     */
    private int quantidade;

    /**
     *  Construtor vazia para JDBC.
     */
    public ItemCarrinho(){
    }

    /**
     * @param produto
     * @param quantidade
     *  Construtor do ItemCarrinho.
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
     *  Função para adicionar ou diminuir a quantidade do produto.
     */
    public void addQuantidadeProduto(int quantidade){
        this.quantidade = (this.quantidade + quantidade);
    }

}
