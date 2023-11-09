import java.util.ArrayList;

public class GerenciadorFilmes {
    
    //atributos
    private ArrayList<Filme> lista;

    //construtor
    public GerenciadorFilmes() {
        lista = new ArrayList<>();
    }

    public void inserir(Filme filme) {
        lista.add(filme);
    }

    public ArrayList<Filme> getListaFilmes() {
        return lista;
    }

    public void inserir(int codigo, String nome, Categoria categoria) {
        lista.add(new Filme(codigo, nome, categoria));
    }

    public Filme buscarFilme(int codigoFilme) {
        for (Filme filme: lista) {
            if (filme.getCodigo() == codigoFilme)
                return filme;
        }
        return null;
    }

    public void alugaFilme(Filme filme) {
        filme.setLocado(true);
    }

    public void devolveFilme(Filme filme) {
        filme.setLocado(false);
    }

    //tostring
    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder("\nFilmes Cadastrados\n");
        for (Filme filme : lista) {
            aux.append(filme.toString() + "\n");
        }
        return aux.toString();
    }
}
