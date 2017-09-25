package cozinhandoYoutube
import scala.actors.Actor

// O ator recebe a descrição e a palvra buscada
class BuscadorPalavra(val n:Int,val listaLinks:ListaDeLinks) extends Actor{
  def act()={
    receive{
      /* O ator recebe uma instância da classe ListaDeLinks. Se o ator encontrar a palavra chave,
       * ele irá adicionar na ListaDeLinks o link a descrição e o título do vídeo*/
      case (PosicaoMapa(descricao,palavra))=>
        if(descricao._1._2.contains(palavra)){
          listaLinks.addLink("http://www.youtube.com/watch?v="+refinaLink(descricao._2))
          listaLinks.addTitulo(descricao._1._1)
          listaLinks.addDescricao(descricao._1._2)
        }
        println("sou o ator " + n)
        act
      // Esse caso serve para verificar se o ator já terminou a execução. O resultado só é retornado depois que todos os atores dão essa resposta
      case "fim"=> reply(1)
    }
  }
  
  def refinaLink(link:String):String={
    link.slice(42, 53)
  }
}