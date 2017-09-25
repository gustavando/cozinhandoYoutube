package cozinhandoYoutube
//import scala.swing._
import swing.Swing._
import swing._
import BorderPanel.Position._
import event.{ButtonClicked, WindowClosing,SelectionChanged,FocusGained}

class Interface(lista:ListaDeLinks) extends Frame{
  
  val comboBox=new ComboBox(lista.titulos)
  val areaDeTexto=new TextArea()
  val textLink=new TextField("link")
  val botaoOk=new Button{text="Ok"}
  val botaoDescricao=new Button{text="Descricao"}
  
    visible=true
    title="Vídeos localizados"
    contents = new BorderPanel{
      layout+= new BorderPanel{
        layout+= new GridPanel(1,2){
          layout+= new BorderPanel{
            layout+=new Label("Título do vídeo: ")->Center
            
          }->West
          layout+= new BorderPanel{
            layout+=comboBox->Center
          }->Center
          layout+= new BorderPanel{
            layout+=botaoOk->East
          }->East
        }->North
        
      } -> North
      layout+=new ScrollPane(areaDeTexto) -> Center
      layout+= new BorderPanel{
        layout+=textLink->Center
      }->South
      
    }
    listenTo(botaoOk)
    
    // Quando o botão for clicado a reação pe disparada
    
    reactions +={
      case WindowClosing(e) => System.exit(0)
      case ButtonClicked(botaoOk) => {
        // retorna o índice com o objetivo de recuperar o link e a descrição do vídeo
        val indice=lista.pegaIndice(comboBox.item)
        areaDeTexto.text=lista.descricoes(indice)
        textLink.text=lista.links(indice)
      }
    }
    size=new Dimension(800,600)
    centerOnScreen
  }
