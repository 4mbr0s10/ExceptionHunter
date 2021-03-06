package view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import modelado.*;



public class ViewManager {
    
  private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private boolean sbs;
    private final static int MENU_BOTONES_INICIAL_X=100;
    private final static int MENU_BOTONES_INICIAL_Y=600;
    private ExcSubEscenas subScenaCreditos;
    private ExcSubEscenas subScenaAyuda;
    
    private ExcSubEscenas subElegirJuego;
    private ExcSubEscenas ocultarSubScena;
    private ExcSubEscenas subEscenas;
    List<JuegoBotones> botonesMenu;
    public ViewManager(){
        botonesMenu = new ArrayList<>();
        mainPane= new AnchorPane();
        mainScene = new Scene (mainPane, WIDTH, HEIGHT );
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        crearImagenFondoExtra();
        crearSubScenas();
        crearBoton();
        crearFondo();
        
        
       
    }
    
    private void noSolaparSubScenas(ExcSubEscenas subScene){
        if(ocultarSubScena != null){
            ocultarSubScena.moverSubScene();
        }
        subScene.moverSubScene();
        ocultarSubScena= subScene;
    }
    
    private void crearSubScenas(){
        subScenaCreditos = new ExcSubEscenas("File:ayuda.png");
        mainPane.getChildren().add(subScenaCreditos);
        
        subScenaAyuda = new ExcSubEscenas("File:creditos.png");
        mainPane.getChildren().add(subScenaAyuda);
        
//        subElegirJuego = new ExcSubEscenas("");
//        mainPane.getChildren().add(subElegirJuego);
    }
    
    
    public Stage getMainStage(){
        
        return mainStage;
    }
    private void a??adirBotonesMenu(JuegoBotones boton){
        boton.setLayoutX(MENU_BOTONES_INICIAL_X + botonesMenu.size()*200);
        boton.setLayoutY(MENU_BOTONES_INICIAL_Y);
        botonesMenu.add(boton);
        mainPane.getChildren().add(boton);
        
    }
    
    
     private void crearBoton(){
     crearBotonInicio();
     crearBotonAyuda();
     crearBotonCreditos();
     crearBotonSalir();
     crearBotonExtraPong();
     crearBotonExtraPong2();
    }
    private void crearBotonInicio (){
        JuegoBotones botonInicio = new JuegoBotones("JUGAR");
        a??adirBotonesMenu(botonInicio);
        
        botonInicio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                 noSolaparSubScenas(subElegirJuego);
                 GameManager gm = new GameManager();
                 gm.inicializarJuego();
            }
        });
        
    }
     private void crearBotonAyuda (){
        JuegoBotones botonAyuda = new JuegoBotones("AYUDA");
      
        a??adirBotonesMenu(botonAyuda);
        
        botonAyuda.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
      
                 noSolaparSubScenas(subScenaAyuda);
            }
        });
    }
      private void crearBotonCreditos (){
        JuegoBotones botonCreditos = new JuegoBotones("CREDITOS");
     
        a??adirBotonesMenu(botonCreditos);
        
        botonCreditos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  
       
               noSolaparSubScenas(subScenaCreditos);
            }
        });
         
    }
       private void crearBotonSalir (){
        JuegoBotones botonSalir = new JuegoBotones("SALIR");
        a??adirBotonesMenu(botonSalir);
        
        botonSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              mainStage.close();
            }
        });
        
    }
       
            private void crearBotonExtraPong (){
        JuegoBotones botonExtraPong = new JuegoBotones("PONG SP");
       botonExtraPong.setLayoutX(281);
       botonExtraPong.setLayoutY(680);
       botonExtraPong.setScaleX(1.2);
       botonExtraPong.setStyle("-fx-background-color: transparent; -fx-background-image: url('/modelado/recursos/yellow_button00.png');");
         mainPane.getChildren().add(botonExtraPong);
        botonExtraPong.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              PongInd pi = new PongInd();
              pi.inicializarJuegoPong();
            }
        });
        
    }
         private void crearBotonExtraPong2 (){
        JuegoBotones botonExtraPong2 = new JuegoBotones("PONG MP");
    
      botonExtraPong2.setLayoutX(521);
      botonExtraPong2.setLayoutY(680);
      botonExtraPong2.setScaleX(1.2);
      botonExtraPong2.setStyle("-fx-background-color: transparent; -fx-background-image: url('/modelado/recursos/yellow_button00.png');");
        mainPane.getChildren().add(botonExtraPong2);
        
        botonExtraPong2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              PongMulti pm = new PongMulti();
              pm.inicializarJuegoPong();
            }
        });
        
    }
   private void crearFondo(){
        BackgroundImage myBI= new BackgroundImage(new Image("file:fondoMenu.jpg",1024,768,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        mainPane.setBackground(new Background(myBI));
       
   }
   private void crearImagenFondoExtra (){
     
       Image img3 = new Image("File:beta.png");
    
       ImageView iv3 = new ImageView(img3);
//       mainPane.getChildren().add(iv1);
//       mainPane.getChildren().add(iv2);
       mainPane.getChildren().add(iv3);
  
       iv3.setLayoutX(200);
       iv3.setLayoutY(160);
       iv3.setScaleX(0.7);
       iv3.setScaleY(0.7);
   }
}
