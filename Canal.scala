package cozinhandoYoutube
import scala.xml._
import java.net._
import scala.collection.mutable.Map
import java.io.FileNotFoundException

class Canal(user:String) {

  val numVideos = countVideos
  
  // Pega links de 50 em 50 do canal respectivo (O número máximo de vídeos que podem ser mostrados de uma vez)
  def geraInformacoes():Map[(String,String),String] = {
    var ctb = 0
    var info = List[scala.xml.Elem]()
    while(math.abs(numVideos-ctb)!=0){
      if(ctb+50<numVideos){
        info= (XML.load(new URL(Links.canalQuery(user, ctb+1)))) :: info
        ctb+=50
      }else{
        info= (XML.load(new URL(Links.canalQuery(user, math.abs(numVideos-ctb))))) :: info
        ctb = numVideos
      }
    }
    map(info)
  }
  
  def map(inform:List[scala.xml.Elem]):Map[(String,String),String]={
    // Cria um mapa (Nome do vídeo, Descrição) -> Link
    val aux = Map[(String,String),String]()
    for(elemento <-inform){
      val listaLink = pegalink(elemento)
      val listaDes = pegaDescricao(elemento)  
      val titulo = pegaTitulo(elemento)
      for(i <-0 until listaLink.length){
        aux+=((titulo(i),listaDes(i)) -> listaLink(i))
      }
    } 
    aux
  }
  
  def pegaTitulo(variavel:scala.xml.Elem):scala.collection.immutable.Seq[String]={
    (variavel \\ "channel" \ "item" \ "title" ).map(_.text)
  }
  
  def pegaDescricao(variavel:scala.xml.Elem):scala.collection.immutable.Seq[String]={
    (variavel \\ "channel" \ "item" \ "description" ).map(_.text)
  }
  
  def pegalink(variavel:scala.xml.Elem):scala.collection.immutable.Seq[String]={
    (variavel \\ "channel" \ "item" \ "guid").map(_.text)
  }
  
  def countVideos():Int={
    val tudo = XML.load(new URL(Links.canalCountVideos(user)))
    val aux = (tudo \\ "totalResults").map(_.text)
    aux(0).toInt
  }
  
}