package vita.bloom.front.end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    private int quantidade;

    // Contrutor vazio para JDBC.
    public ItemCarrinho(){

    }

    // Contrutor de ItemCarrinho
    public ItemCarrinho(Produto produto, int quantidade) {        
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void addQuantidadeProduto(int quantidade){
        this.quantidade = (this.quantidade + quantidade);
    }

}
