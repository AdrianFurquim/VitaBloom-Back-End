package vita.bloom.front.end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    protected String nome;
    protected String email;
    protected String cpf;
    protected String estado;
    protected String cidade;
    protected String cep;
    protected String senha;

    // Contrutor vazio para JDBC.
    public Usuarios() {
    }

    // Contrutor Usuarios.
    public Usuarios(Carrinho carrinho, String nome, String email, String cpf, String estado, String cidade, String cep,
            String senha) {
        this.carrinho = carrinho;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.senha = senha;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdCarrinho(Long idCarrinho){
        this.carrinho.setIdCarrinho(idCarrinho);
        
    }

    public void addItemCarrinho(ItemCarrinho item){
        this.carrinho.addItem(item);
    }

    public void removeItemCarrinho(ItemCarrinho item){
        this.carrinho.removeItem(item);
    }

    public void removeItemCarrinhoById(Long idItem){
        this.carrinho.removeItemById(idItem);
    }

    public void addQuantidadeProduto(int quantidade){
        this.carrinho.addQuantidadeProduto(quantidade);
    }

    @Override
    public String toString() {
        return "Usuarios [idUsuario=" + idUsuario + ", carrinho=" + carrinho + ", nome=" + nome + ", email=" + email
                + ", cpf=" + cpf + ", estado=" + estado + ", cidade=" + cidade + ", cep=" + cep + ", senha=" + senha
                + "]";
    }

}
