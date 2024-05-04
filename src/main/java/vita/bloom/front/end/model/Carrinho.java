package vita.bloom.front.end.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrinho {

    /**
     * Id do carrinho gerado automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCarrinho;
    
    /**
     * List do tipo Item Carrinho para que cada carrinho tenha sua lista de produtos.
     * Dado que um carrinho possuo varios itens.
     */
    @OneToMany(cascade = CascadeType.ALL)
    protected List<ItemCarrinho> itens = new ArrayList<>();

    /**
     * Construtor vazio para JDBC.
     */
    public Carrinho() {
    }

    /**
     * @param itens
     */
    public Carrinho(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    /**
     * @return
     */
    public Long getIdCarrinho() {
        return idCarrinho;
    }

    /**
     * @param idCarrinho
     */
    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    /**
     * @return
     */
    public List<ItemCarrinho> getItens() {
        return itens;
    }

    /**
     * @param itens
     */
    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    /**
     * @param item
     */
    public void addItem(ItemCarrinho item){
        this.itens.add(item);
    }

    /**
     * @param item
     */
    public void removeItem(ItemCarrinho item){
        this.itens.remove(item);
    }
    
    /**
     * @param idItem
     *  Funcionalidade para remover um item pelo Id, com verificação se o Id existe.
     */
    public void removeItemById(Long idItem){
        this.itens.removeIf(itens -> itens.getId().equals(idItem));
    }

    /**
     * @param quantidade
     *  Funcionalidade para adicionar ou remover uma quantia de determinado produto.
     */
    public void addQuantidadeProduto(int quantidade){
        ((Carrinho) this.itens).addQuantidadeProduto(quantidade);
    }

}
