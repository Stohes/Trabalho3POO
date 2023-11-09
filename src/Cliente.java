public class Cliente {
    
    //atributos
    private int codigo;
    private String nome;

    //construtor
    public Cliente(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    //getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    //tostring
    @Override
    public String toString() {
        return "Nome do Cliente = " + nome + "\nCodigo = " + codigo ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } 
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) obj;
        return cliente.getCodigo() == this.getCodigo();
    }

}
