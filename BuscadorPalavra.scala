package cozinhandoYoutube
import scala.actors.Actor

// O ator recebe a descri��o e a palvra buscada
class BuscadorPalavra(val n:Int,val listaLinks:ListaDeLinks) extends Actor{
  def act()={
    receive{
      /* O ator recebe uma inst�ncia da classe ListaDeLinks. Se o ator encontrar a palavra chave,
       * ele ir� adicionar na ListaDeLinks o link a descri��o e o t�tulo do v�deo*/
      case (PosicaoMapa(descricao,palavra))=>
        if(descricao._1._2.contains(palavra)){
          listaLinks.addLink("http://www.youtube.com/watch?v="+refinaLink(descricao._2))
          listaLinks.addTitulo(descricao._1._1)
          listaLinks.addDescricao(descricao._1._2)
        }
        println("sou o ator " + n)
        act
      // Esse caso serve para verificar se o ator j� terminou a execu��o. O resultado s� � retornado depois que todos os atores d�o essa resposta
      case "fim"=> reply(1)
    }
  }
  
  def refinaLink(link:String):String={
    link.slice(42, 53)
  }
}