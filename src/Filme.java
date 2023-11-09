public class Filme {
    
    //atributos
    private int codigo;
    private String nome;
    private boolean locado;
    private Categoria categoria;

    //construtor
    public Filme(int codigo, String nome, Categoria categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.locado = false;
        this.categoria = categoria;
    }

    //getters e setters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setLocado(boolean locado) {
        this.locado = locado;
    }

    public boolean getLocado() {
        return locado;
    }

    //tostring
    @Override
    public String toString() {
        String a =  "\nNome do Filme = " + nome + "\nCategoria = " +  categoria + "\nCodigo = " + codigo;
        String b;
        if (locado) {
            b = "\nStatus = Locado";
        }
        else {
            b = "\nStatus = Disponivel";
        }
        return a + b;
    }

    

}
