package vita.bloom.front.end.model;

import java.util.List;
import java.util.Optional;

public class ItemCarrinhoDTO {
    private Long produtoId;
    private int quantidade;
    private Optional<Produto> produto;

    // Contrutor vazio pelo JDBC.
    public ItemCarrinhoDTO(){
        
    }

    // Contrutor de ItemCarrinhoDTO.
    public ItemCarrinhoDTO(Long produtoId, int quantidade, Optional<Produto> produto) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Optional<Produto> getProduto() {
        return produto;
    }

    public void setProduto(Optional<Produto> optional) {
        this.produto = optional;
    }
    
}
