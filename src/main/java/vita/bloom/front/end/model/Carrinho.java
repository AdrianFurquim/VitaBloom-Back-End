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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCarrinho;

    
    @OneToMany(cascade = CascadeType.ALL)
    protected List<ItemCarrinho> itens = new ArrayList<>();

    // Construtor vazio para JDBC.
    public Carrinho() {
    }

    public Carrinho(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public void addItem(ItemCarrinho item){
        this.itens.add(item);
    }

    public void removeItem(ItemCarrinho item){
        this.itens.remove(item);
    }
    
    public void removeItemById(Long idItem){
        this.itens.removeIf(itens -> itens.getId().equals(idItem));
    }

    public void addQuantidadeProduto(int quantidade){
        ((Carrinho) this.itens).addQuantidadeProduto(quantidade);
    }

}
