import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class GerenciadorLocacoes {
    
    //atributos
    private ArrayList<Locacao> locacoes;
    private GerenciadorFilmes gerenciadorFilmes;
    private GerenciadorClientes gerenciadorClientes;
    

    //construtor
    public GerenciadorLocacoes(GerenciadorFilmes gerenciadorFilmes, GerenciadorClientes gerenciadorClientes) {
        locacoes = new ArrayList<>();
        this.gerenciadorClientes = gerenciadorClientes;
        this.gerenciadorFilmes = gerenciadorFilmes;
    }

    public void inserir(Filme filme, LocalDate data, Cliente cliente) {
        locacoes.add(new Locacao(filme, data, cliente));
    }

    private Locacao getLocacao(int codCliente, int codFilme) {
        for (Locacao locacao : locacoes) {
            if (locacao.getCliente().getCodigo() == codCliente && locacao.getFilme().getCodigo() == codFilme) {
                return locacao;
            }
        }
        return null;
    }


    public void solicitaLocacao(int codCliente, int codFilme) throws IllegalArgumentException, IllegalStateException {
        Cliente cliente =  gerenciadorClientes.buscarCliente(codCliente);
        Filme filme = gerenciadorFilmes.buscarFilme(codFilme);
        LocalDate data = LocalDate.now();

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nao encontrado");
        }
        if (filme == null) {
            throw new IllegalArgumentException("Filme nao encontrado");
        }
        if (filme.getLocado()) {
            throw new IllegalStateException("Filme ja locado");
        }

        gerenciadorFilmes.alugaFilme(filme);
        inserir(filme, data, cliente);
        System.out.println("\n" + cliente.getNome() + " alugou: " + filme.getNome());
    }

    public void solicitaDevolucao(int codCliente, int codFilme) throws IllegalArgumentException, IllegalStateException {
        Locacao locacao = getLocacao(codCliente, codFilme);

        if (locacao == null) {
            throw new IllegalArgumentException("Locacao nao encontrada");
        }
        if (!locacao.getFilme().getLocado()) {
            throw new IllegalStateException("Filme ja devolvido");
        }

        gerenciadorFilmes.devolveFilme(locacao.getFilme());
        System.out.println("\n" + locacao.getCliente().getNome() + " devolveu " + locacao.getFilme().getNome());
    }

    public List<String> filmesAssistidos(int codigoCliente) throws IllegalArgumentException {
        Cliente cliente = gerenciadorClientes.buscarCliente(codigoCliente);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nao encontrado");
        }

        return  locacoes.stream()
                .filter((Locacao loc) -> loc.getCliente().getCodigo() == cliente.getCodigo())
                .map((Locacao loc) -> loc.getFilme().getNome())
                .collect(Collectors.toList());
    }

    public Long filmesTotaisAssistidos(Categoria categoria) {
        return  locacoes.stream()
                .filter((Locacao l) -> l.getFilme().getCategoria() == categoria)
                .count();
    }


    public List<String> clientesQueAssistiramFilme(int codigoFilme) throws IllegalArgumentException {
        Filme filme = gerenciadorFilmes.buscarFilme(codigoFilme);

        if (filme == null) {
            throw new IllegalArgumentException("Filme nao encontrado");
        }

        return  locacoes.stream()
                .filter((Locacao l) -> l.getFilme().getCodigo() == filme.getCodigo())
                .map((Locacao l) -> l.getCliente())
                .distinct()
                .map((Cliente c) -> c.getNome())
                .sorted()
                .collect(Collectors.toList());
    }
    
    public Map<Categoria, Long> numeroFilmesAssistidosNasCategorias() {
        Map<Categoria, Long> dicionario = new HashMap<>();

        List<Categoria> categorias = Arrays.asList(Categoria.values());
        
        categorias.stream()
        .forEach((Categoria c) -> dicionario.put(c, filmesTotaisAssistidos(c)));
        
        return dicionario;
    }

    public Set<String> filmesNuncaLocados() {
        ArrayList<Filme> listaFilmes = gerenciadorFilmes.getListaFilmes();

        List<Filme> filmes = 
        locacoes.stream()
        .map((Locacao l) -> l.getFilme())
        .collect(Collectors.toList());
        
        ArrayList<Filme> listaFilmes2 = new ArrayList<>(listaFilmes);
        listaFilmes2.removeAll(filmes);

        return Set.copyOf(listaFilmes2.stream()
        .map((Filme f) -> f.getNome())
        .collect(Collectors.toList()));       
    }

    public boolean salvarFilmesCsv(String nomeArq) {
        Path path1 = Paths.get(nomeArq);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.forName("utf8")))) {
            for(Filme filmes : gerenciadorFilmes.getListaFilmes())
                writer.format("%d;%s;%b;%s\n",
                    filmes.getCodigo(),
                    filmes.getNome(),
                    filmes.getLocado(),
                    filmes.getCategoria().toString());
        }
        catch (IOException x) {
            System.err.format("Erro ao salvar arquivo: " + x.getLocalizedMessage());
        }
        return true;
    }
}
