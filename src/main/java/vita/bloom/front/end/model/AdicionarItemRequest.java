package vita.bloom.front.end.model;
import java.util.List;
import java.util.Optional;

public class AdicionarItemRequest {
    private Long idCarrinho;
    private List<ItemCarrinhoDTO> itens;

    // Construtor vazio para JDBC.
    public AdicionarItemRequest(){

    }

    // Contrutor do AdicionarItemRequest.
    public AdicionarItemRequest(Long idCarrinho, List<ItemCarrinhoDTO> itens) {
        this.idCarrinho = idCarrinho;
        this.itens = itens;
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }
    
    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    // Get da quantidade de itens pelo ID
    public int getQuantidadeOutro(int id) {
        return itens.get(id).getQuantidade();
    }

}
