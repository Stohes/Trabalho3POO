import java.time.LocalDate;

public class Locacao {
    
    //atributos
    private Filme filme;
    private LocalDate data;
    private Cliente cliente;

    //construtor
    public Locacao(Filme filme, LocalDate data, Cliente cliente) {
        this.filme = filme;
        this.data = data;
        this.cliente = cliente;
    }

    //getters
    public Filme getFilme() {
        return filme;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }


    //tostring
    @Override
    public String toString() {
        return "Locacao \nCliente = " + cliente + "\nData da Locacao = " + data + "\nFilme locado = " + filme;
    }


    
   

    

}
