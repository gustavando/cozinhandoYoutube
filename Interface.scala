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
    title="V�deos localizados"
    contents = new BorderPanel{
      layout+= new BorderPanel{
        layout+= new GridPanel(1,2){
          layout+= new BorderPanel{
            layout+=new Label("T�tulo do v�deo: ")->Center
            
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
    
    // Quando o bot�o for clicado a rea��o pe disparada
    
    reactions +={
      case WindowClosing(e) => System.exit(0)
      case ButtonClicked(botaoOk) => {
        // retorna o �ndice com o objetivo de recuperar o link e a descri��o do v�deo
        val indice=lista.pegaIndice(comboBox.item)
        areaDeTexto.text=lista.descricoes(indice)
        textLink.text=lista.links(indice)
      }
    }
    size=new Dimension(800,600)
    centerOnScreen
  }
