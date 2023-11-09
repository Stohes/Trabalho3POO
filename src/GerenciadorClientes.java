import java.util.ArrayList;

public class GerenciadorClientes {
    
    //atributos
    private ArrayList<Cliente> lista;

    //construtor
    public GerenciadorClientes() {
        lista = new ArrayList<>();
    }

    public void inserir(Cliente cliente) {
        lista.add(cliente);
    }

    public void inserir(int codigo, String nome) {
        lista.add(new Cliente(codigo, nome));
    }

    public Cliente buscarCliente(int codigoCliente) {
        for (Cliente cliente: lista) {
            if (cliente.getCodigo() == codigoCliente)
                return cliente;
        }
        return null;
    }

    //tostring
    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder("\nClientes Cadastrados\n");
        for (Cliente cliente : lista) {
            aux.append(cliente.toString() + "\n");
        }
        return aux.toString();
    }

    
}
