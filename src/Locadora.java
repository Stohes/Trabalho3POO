import java.util.List;

public class Locadora {
    
    private GerenciadorClientes gerenciadorClientes;
    private GerenciadorFilmes gerenciadorFilmes;
    private GerenciadorLocacoes gerenciadorLocacoes;

    public Locadora() {

        gerenciadorClientes = new GerenciadorClientes();
        gerenciadorFilmes = new GerenciadorFilmes();
        gerenciadorLocacoes = new GerenciadorLocacoes(gerenciadorFilmes, gerenciadorClientes);

        criaClientes();
        criaFilmes();
    }

    private void criaClientes() {
        gerenciadorClientes.inserir(123, "Joao");
        gerenciadorClientes.inserir(456, "Alberto");
        gerenciadorClientes.inserir(789, "Mario");
    }

    private void criaFilmes() {
        gerenciadorFilmes.inserir(123, "Missao impossivel", Categoria.Acao);
        gerenciadorFilmes.inserir(321, "Vingadores", Categoria.Acao);
        gerenciadorFilmes.inserir(456, "A culpa e das estrelas", Categoria.Romance);
        gerenciadorFilmes.inserir(789, "Mamma mia", Categoria.Comedia);
        gerenciadorFilmes.inserir(283, "O menino do pijama listrado", Categoria.Drama);
    }

    public void alugaFilme(int codigoCliente, int codigoFilme) {
        try {
            gerenciadorLocacoes.solicitaLocacao(codigoCliente, codigoFilme);

        } catch (Exception e) {
            System.out.println("\nErro ao alugar filme: " + e.getLocalizedMessage());
        }
    }

    public void devolveFilme(int codigoCliente, int codigoFilme) {
        try {
            gerenciadorLocacoes.solicitaDevolucao(codigoCliente, codigoFilme);

        } catch (Exception e) {
            System.out.println("\nErro ao devolver filme: " + e.getLocalizedMessage());
        }
    }

    public void filmesAlugadosPeloCliente(int codigoCliente) {
        try {
            List<String> lista = gerenciadorLocacoes.filmesAssistidos(codigoCliente);

            if (lista.isEmpty()) {
                System.out.println("\n" + gerenciadorClientes.buscarCliente(codigoCliente).getNome() + " nao assistiu nenhum filme");
            } else {
            
                System.out.println("\nFilmes assistidos por " + gerenciadorClientes.buscarCliente(codigoCliente).getNome() + ":");

                for (String filme : lista) {
                    System.out.println(filme); 
                }
            }
        } catch (Exception e) {
            System.out.println("\nErro ao gerar lista: " + e.getLocalizedMessage());
            
        }
    }

    public void filmesTotaisAssistidos(Categoria categoria) {
        Long total = gerenciadorLocacoes.filmesTotaisAssistidos(categoria);

        if (total == 0) {
            System.out.println("\nNenhum filme de " + categoria + " foi assistido");
        } else {
            System.out.println("\nNumero total de filmes de " + categoria + " assistidos: " + total);
        }
    }

    public void clientesQueAssistiramFilme(int codigoFilme) {
        try {
            List<String> lista = gerenciadorLocacoes.clientesQueAssistiramFilme(codigoFilme);
            if (lista.isEmpty()) {
                System.out.println("\nNinguem assistiu " + gerenciadorFilmes.buscarFilme(codigoFilme).getNome());
            } else {
                System.out.println("\nClientes que assistiram " + gerenciadorFilmes.buscarFilme(codigoFilme).getNome() + ":");
                lista.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("\nErro ao gerar lista: " + e.getLocalizedMessage());
        }
    }

    public void numeroFilmesAssistidosNasCategorias() {
        System.out.println("\nNumero de filmes locados para cada categoria: ");
        gerenciadorLocacoes.numeroFilmesAssistidosNasCategorias().entrySet().forEach(System.out::println);
    }

    public void filmesNuncaLocados() {
        System.out.println("\nFilmes que nunca foram locados: ");
        gerenciadorLocacoes.filmesNuncaLocados().forEach(System.out::println);

    }


    public void salvarFilmesCsv() {
        gerenciadorLocacoes.salvarFilmesCsv("FilmesSalvos.csv");
    }


    
    
}


