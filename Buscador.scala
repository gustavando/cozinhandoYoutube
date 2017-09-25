package cozinhandoYoutube

import scala.swing._

class Buscador(val palavras:List[String], val canais:List[Canal]) {
  
  val refLista= new ListaDeLinks
  
  def criaAtores():Unit={
    val nAtores= 5
    val atores= new Array[BuscadorPalavra](nAtores)
    for(i<-0 until nAtores){
      atores(i)=new BuscadorPalavra(i,refLista)
      atores(i).start
    }
    criaLinks(atores,nAtores)
    esperaAtores(nAtores,atores)
    refLista.links.foreach(String=>println(String))
    new Interface(refLista)
  }
  
  // Divide o trabalho de criar a lista de links / título / descrição entre os atores
  def criaLinks(atores:Array[BuscadorPalavra],nAtores:Int):Unit={
    for(canal<-canais){
      val atual=canal.geraInformacoes.toArray
      var k=0
      //println(atual.length)
      for(i<- 0 until atual.length){
        //println(palavras.length)
        for(j<-0 until palavras.length){
          atores(k % nAtores) ! (PosicaoMapa(atual(i),palavras(j)))
          k+=1  
        }
        
      }
    }
    
  }     
      
  
  // Manda uma mensagem de fim para todos os atores e espera responder para continuar o programa
  def esperaAtores(nAtores:Int, atores:Array[BuscadorPalavra]){
    var i=0
    while(i<nAtores) {
     i+= (atores(i)!!("fim",{case x:Int=>x}))()
    }
  }
}

case class PosicaoMapa(mapaArray:((String,String),String),palavra:String)