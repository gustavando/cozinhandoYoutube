package cozinhandoYoutube
import scala.swing._

object Principal{
  
  def main(args:Array[String]): Unit ={
    println("Digite os ingredientes separados por ;")
    val palavras=readLine()
    val tempoIni=System.currentTimeMillis()
    val buscador = new Buscador(palavras.toLowerCase.split(";").toList, criaCanaisValidos)
    buscador.criaAtores
    val tempoFinal=System.currentTimeMillis()
    //println(tempoFinal-tempoIni)
  }
  
  // Lê um txt com todos os canais que serão buscados
  def criaCanaisValidos():List[Canal]={
    val lista=scala.io.Source.fromFile("canaisValidos.txt")
    val listaCanais = lista.getLines
    (for(canal<-listaCanais) yield{
      new Canal(canal)
    }).toList
  } 
}