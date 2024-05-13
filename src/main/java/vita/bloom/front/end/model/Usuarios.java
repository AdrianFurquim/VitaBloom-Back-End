package vita.bloom.front.end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuarios {
    /**
     *  Id unico gerado automaticamente quando temos um POST de um novo usuário.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    /**
     *  Tabela ligamento do usuário para seu respectivo Carrinho.
     */
    @ManyToOne
    @JoinColumn(name = "id_carrinho")
    private Carrinho carrinho;

    /**
     *  Nome do usuário.
     */
    protected String nome;

    /**
     *  Email do usuário.
     */
    protected String email;

    /**
     *  CPF do usuário.
     */
    protected String cpf;

    /**
     *  Estado do usuário.
     */
    protected String estado;

    /**
     *  Cidade do usuário.
     */
    protected String cidade;

    /**
     *  CEP do usuário.
     */
    protected String cep;

    /**
     *  Senha do usuário.
     */
    protected String senha;

    
    /**
     *  Contrutor vazio para JDBC.
     */
    public Usuarios() {
    }

    /**
     * @param carrinho
     * @param nome
     * @param email
     * @param cpf
     * @param estado
     * @param cidade
     * @param cep
     * @param senha
     *  Contrutor do Usuário.
     */
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

    /**
     * @return
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return
     */
    public Carrinho getCarrinho() {
        return carrinho;
    }

    /**
     * @param carrinho
     */
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    /**
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @param idCarrinho
     *  Criando e configurando o ID do carrinho do usuário.
     */
    public void setIdCarrinho(Long idCarrinho){
        this.carrinho.setIdCarrinho(idCarrinho);        
    }

    /**
     * @param item
     *  Adiciona um item ao carrinho.
     */
    public void addItemCarrinho(ItemCarrinho item){
        this.carrinho.addItem(item);
    }

    /**
     * @param item
     *  Remove o item do carrinho pelo próprio Item. 
     */
    public void removeItemCarrinho(ItemCarrinho item){
        this.carrinho.removeItem(item);
    }

    /**
     * @param idItem
     *  Remove o item do carrinho pelo ID do item.
     */
    public void removeItemCarrinhoById(Long idItem){
        this.carrinho.removeItemById(idItem);
    }

    /**
     * @param quantidade
     *  Adiciona ou remove uma quantidade de quantia de determinado produto no carrinho.
     */
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