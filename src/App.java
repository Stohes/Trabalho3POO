public class App {
 
    public static void main(String[] args) throws Exception {

        Locadora locadora = new Locadora();

        locadora.alugaFilme(789, 123);      //Mario - Missao impossivel - Acao

        locadora.devolveFilme(789, 123);    //Mario devolve Missao impossivel

        locadora.alugaFilme(124, 123);      //cliente nao encontrado
        locadora.alugaFilme(123, 123);      //Joao - Missao impossivel - Acao
        locadora.alugaFilme(123, 456);      //Joao - A culpa e das estrelas - Romance
        locadora.alugaFilme(123, 321);      //Joao - Vingadores - Acao
        locadora.alugaFilme(456, 1283);     //filme nao encontrado
        locadora.alugaFilme(789, 123);      //Erro - filme ja locado - Mario - Missao impossivel - Acao

        locadora.devolveFilme(123, 123);    //Joao devolve Missao impossivel
        locadora.alugaFilme(789, 123);      //Mario - Missao impossivel - Acao

        locadora.filmesAlugadosPeloCliente(123);    //filmes assistidos por Joao
        locadora.filmesAlugadosPeloCliente(456);    //filmes assistidos por Alberto
        locadora.filmesAlugadosPeloCliente(823);    //cliente nao encontrado

        locadora.filmesTotaisAssistidos(Categoria.Acao);
        locadora.filmesTotaisAssistidos(Categoria.Romance);
        locadora.filmesTotaisAssistidos(Categoria.Drama);    
        locadora.filmesTotaisAssistidos(Categoria.Comedia);

        locadora.clientesQueAssistiramFilme(123);   //todos clientes que assistiram Missao impossivel
        locadora.clientesQueAssistiramFilme(456);   //todos clientes que assistiram A culpa e das estrelas
        locadora.clientesQueAssistiramFilme(938);   //filme nao existe
        locadora.clientesQueAssistiramFilme(283);   //filme que ninguem assistiu

        locadora.numeroFilmesAssistidosNasCategorias(); //numero de filmes assistidos em cada uma das categorias
        
        locadora.filmesNuncaLocados();  //nome dos filmes que nunca foram locados

        locadora.salvarFilmesCsv();     //salva todos os dados de todos os filmes no arquivo .csv

    }
}
