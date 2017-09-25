package cozinhandoYoutube

// Esse objeto foi criado para que os atores modifiquem as listas
class ListaDeLinks {
	var links=List[String]()
	var titulos=List[String]()
	var descricoes=List[String]()
	
	def addLink(link:String):Unit={
	  links= link::links
	}
	
	def addTitulo(titulo:String):Unit={
	  titulos=titulo::titulos
	}
	
	def addDescricao(descricao:String):Unit={
	  descricoes=descricao::descricoes
	}
	
	/* Retorna o valor do índice da palavra buscada 
	 * (a palavra buscada vem de um comboBox que é preenchdo usando a lista, portanto, a palavra com certeza está na lista)*/
	def pegaIndice(valor:String):Int={
	  for (i<-0 until titulos.length)
	    if(valor==titulos(i)) return i
	  0
	}
}