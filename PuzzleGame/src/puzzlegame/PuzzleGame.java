
package puzzlegame;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PuzzleGame extends Application {
    private ArrayList<Integer>list=new ArrayList<>();
    private ArrayList<User> userList=new ArrayList<>();
    private String pMain=new String("file:"+System.getProperty("user.dir")+"\\src\\puzzlegame\\image\\");
    private int clicked=0;
    private int b1,b2,b;
    private Stage stage1=new Stage();
    private Stage stageLevel1=new Stage();
    private Stage stage2=new Stage();
    private Stage stageEnd1=new Stage();
    private Stage stageLevel2=new Stage();
    private Stage stageLevel3=new Stage();
    private Stage stage3=new Stage();
    private Stage stage4=new Stage();
    private Stage stageEnd2=new Stage();
    private Stage stageEnd3=new Stage();
    private Stage stageSignIn=new Stage();
    private Stage stageSignUp=new Stage();
    private Stage stageScore=new Stage();
    private Stage stageChoice=new Stage();
    File f1=new File("userInfo.dat");   
    private long startTime1=0;
    private long endTime1=0;
    private long score1=0;
    private long startTime2=0;
    private long endTime2=0;
    private long score2=0;
    private long startTime3=0;
    private long endTime3=0;
    private long score3=0;
    private int userNo;
              
     
     public void onClicked(ImageView[][] imgView,int[] swp1,int[]swp2,int b) throws IOException{
         if(clicked==0){
                    b1=b;
                    clicked=1;
                    getImageView(b1,imgView).setOpacity(.6);
                }
                else if(clicked==1){
                    b2=b;
                    clicked=0;
                    getImageView(b1,imgView).setOpacity(1);
                    for(int i=0;i<swp1.length;i++){
                        if((b1==swp1[i]&&b2==swp2[i])||(b2==swp1[i]&&b1==swp2[i])){
                            swap(getImageView(b1,imgView),getImageView(b2,imgView));
                                if(checkMain(imgView)==true){
                                    if(imgView.length==3){
                                       endLevel1();
                                    }
                                    else if(imgView.length==4){
                                        endLevel2();
                                    }
                                    else if(imgView.length==5){
                                        endLevel3();
                                    }
                                }
                            }
                        }
                    }  
    }
     
     public ImageView getImageView(int b,ImageView[][] imgView){
      
        int iIndex=0,jIndex=0;
        for(int i=0;i<imgView.length;i++){
            for(int j=0;j<imgView[0].length;j++){
                if(b==(i+j*imgView.length)){
                    iIndex=i;
                    jIndex=j;
                    break;
                }
            }
        }
        return imgView[iIndex][jIndex];
    }
     
    public void endLevel1() throws FileNotFoundException, IOException{
        endTime1=System.currentTimeMillis();
        score1=endTime1-startTime1;
        userList.clear();
             
        try{
            FileInputStream inputStream=new FileInputStream(f1);
            ObjectInputStream input=new ObjectInputStream(inputStream);  
            Object user;
            while((user=input.readObject())!=null){
                userList.add((User)user);
            }
            input.close();
            inputStream.close();
        }catch(Exception ex){
            
        }
        
        userList.get(userNo).setScore1(score1);
        
        FileOutputStream outputStream=new FileOutputStream(f1,false);
        ObjectOutputStream output=new ObjectOutputStream(outputStream);
        for (User userList1 : userList) {
            output.writeObject(userList1);
        }
        output.close();
        outputStream.close();
        
         Label labelEnd1=new Label();
         labelEnd1.setText("CONGRATULATION!!! YOU WIN LEVEL-1");
         labelEnd1.setPrefWidth(600);
         labelEnd1.setPrefHeight(50);
         labelEnd1.setAlignment(Pos.CENTER);
         Button btnNext=new Button();
         btnNext.setText("NEXT");
         btnNext.setMinWidth(250);
         Button back=new Button();
         back.setText("Go To Main Menu");
         back.setMinWidth(350);
         btnNext.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                list.clear();
                next1();
            }
        });
         back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                try {
                    list.clear();
                    start(stage1);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
         Image imgEnd1=new Image(pMain+"congo.jpg");
         ImageView imgVend1=new ImageView(imgEnd1);
        imgVend1.setFitWidth(600);
        imgVend1.setFitHeight(550);
         
         BorderPane rootEnd1=new BorderPane();
       
        
         VBox boxNext=new VBox();
         HBox boxHnext=new HBox();
         boxHnext.getChildren().addAll(btnNext,back);
         boxNext.getChildren().addAll(labelEnd1,imgVend1,boxHnext);
         rootEnd1.setCenter(boxNext);
         rootEnd1.setPadding(new Insets(20,20,20,20));
         rootEnd1.setBackground(Background.EMPTY);
         Scene sceneEnd1=new Scene(rootEnd1,640,670,Color.LIGHTBLUE);
         
         stageEnd1.setTitle("RESULT");
         stageEnd1.setScene(sceneEnd1);
         stageEnd1.show();
         stage2.close();
        
     }
    public void endLevel2() throws FileNotFoundException, IOException{
        endTime2=System.currentTimeMillis();
        score2=endTime2-startTime2;
        userList.clear();
             
        try{
            FileInputStream inputStream=new FileInputStream(f1);
            ObjectInputStream input=new ObjectInputStream(inputStream);  
            Object user;
            while((user=input.readObject())!=null){
                userList.add((User)user);
            }
            input.close();
            inputStream.close();
        }catch(Exception ex){
           
        }
        
        userList.get(userNo).setScore2(score2);
        
        FileOutputStream outputStream=new FileOutputStream(f1,false);
        ObjectOutputStream output=new ObjectOutputStream(outputStream);
        for (User userList1 : userList) {
            output.writeObject(userList1);
        }
        output.close();
        outputStream.close();
        
         Label labelEnd2=new Label();
         labelEnd2.setText("CONGRATULATION!!! YOU WIN LEVEL-2");
         labelEnd2.setPrefWidth(600);
         labelEnd2.setPrefHeight(50);
         labelEnd2.setAlignment(Pos.CENTER);
         Button btnNext=new Button();
         btnNext.setText("NEXT");
         btnNext.setMinWidth(250);
         Button back=new Button();
         back.setText("Go To Main Menu");
         back.setMinWidth(350);
         btnNext.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                list.clear();
                next2();
            }
        });
         back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                try {
                    list.clear();
                    start(stage1);
                } catch (Exception ex) {
                    
                }
            }
        });
         Image imgEnd2=new Image(pMain+"congo.jpg");
         ImageView imgVend2=new ImageView(imgEnd2);
        imgVend2.setFitWidth(600);
        imgVend2.setFitHeight(550);
        BorderPane rootEnd2=new BorderPane();
       
         VBox boxNext=new VBox();
         HBox boxHnext=new HBox();
         boxHnext.getChildren().addAll(btnNext,back);
         boxNext.getChildren().addAll(labelEnd2,imgVend2,boxHnext);
         rootEnd2.setCenter(boxNext);
         rootEnd2.setPadding(new Insets(20,20,20,20));
         rootEnd2.setBackground(Background.EMPTY);
         Scene sceneEnd2=new Scene(rootEnd2,640,670,Color.LIGHTBLUE);
         stageEnd2.setTitle("RESULT");
         stageEnd2.setScene(sceneEnd2);
         stageEnd2.show();
         stage3.close();
        
     }
    public void endLevel3() throws FileNotFoundException, IOException{
        endTime3=System.currentTimeMillis();
        score3=endTime3-startTime3;
        userList.clear();
             
        try{
            FileInputStream inputStream=new FileInputStream(f1);
            ObjectInputStream input=new ObjectInputStream(inputStream);  
            Object user;
            while((user=input.readObject())!=null){
                userList.add((User)user);
            }
            input.close();
            inputStream.close();
        }catch(Exception ex){
            
        }
        
        userList.get(userNo).setScore3(score3);
        
        FileOutputStream outputStream=new FileOutputStream(f1,false);
        ObjectOutputStream output=new ObjectOutputStream(outputStream);
        for (User userList1 : userList) {
            output.writeObject(userList1);
        }
        output.close();
        outputStream.close();
        
         Label labelEnd3=new Label();
         labelEnd3.setText("CONGRATULATION!!! YOU WIN LEVEL-3");
         labelEnd3.setPrefWidth(600);
         labelEnd3.setPrefHeight(50);
         labelEnd3.setAlignment(Pos.CENTER);
         Button back=new Button();
         back.setText("Go To Main Menu");
         back.setMinWidth(350);
         Button close=new Button();
         close.setText("End Game");
         close.setMinWidth(250);
         HBox boxEnd=new HBox();
         boxEnd.getChildren().addAll(back,close);
         back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                list.clear();
                try {
                    stageEnd3.close();
                    start(stage1);
                } catch (Exception ex) {
                   System.out.println(ex.getMessage());
                }
            }
        });
         close.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                stageEnd3.close();
            }
        });
         
         Image imgEnd3=new Image(pMain+"congo.jpg");
         ImageView imgVend3=new ImageView(imgEnd3);
         imgVend3.setFitWidth(600);
         imgVend3.setFitHeight(550);
         
         BorderPane rootEnd3=new BorderPane();
         VBox boxVend=new VBox();
         boxVend.getChildren().addAll(imgVend3,labelEnd3,boxEnd);
         rootEnd3.setCenter(boxVend);
         rootEnd3.setPadding(new Insets(20,20,20,20));
         rootEnd3.setBackground(Background.EMPTY);
         Scene sceneEnd3=new Scene(rootEnd3,640,670,Color.LIGHTBLUE);
         
     
         stageEnd3.setTitle("RESULT");
         stageEnd3.setScene(sceneEnd3);
         stageEnd3.show();
         stage4.close();
        
     }
     
    
    public boolean checkMain(ImageView[][] imgView){
         
         int c=0;
         for(int i=0;i<imgView.length;i++){
             int s=i;
             for(int j=0;j<imgView[0].length;j++){
                 if(imgView[i][j].getId().equals("id"+s)){
                     c++;
                     s=s+imgView.length;
                 }
             }
         }
          if(c==imgView.length*imgView.length){
              return true;
         }
          else{
              return false;
          }
     }
     
    public void swap(ImageView b1,ImageView b2){
        
       ImageView imgTemp=new ImageView();
       imgTemp.setImage(b2.getImage());
       imgTemp.setId(b2.getId());
       b2.setImage(b1.getImage());
       b2.setId(b1.getId());
       b1.setImage(imgTemp.getImage());
       b1.setId(imgTemp.getId());
       
    } 
    public void next1(){
  
        stageLevel2.setTitle("Puzzle");
        Button next=new Button();
        next.setText("START Level-2");
        
        next.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                list.clear();
                startLevel2();
            }
        });
        Image imgLevel2=new Image(pMain+"real2.jpg");
        ImageView imgViewLevel2=new ImageView(imgLevel2);
        imgViewLevel2.setFitWidth(600);
        imgViewLevel2.setFitHeight(550);
        
        VBox boxLevel2=new VBox();
        boxLevel2.getChildren().add(next);
        boxLevel2.setAlignment(Pos.BOTTOM_CENTER);
        Label labelLevel2=new Label();
        labelLevel2.setText("EXPECTED IMAGE");
        labelLevel2.setPrefWidth(600);
        labelLevel2.setPrefHeight(50);
        labelLevel2.setAlignment(Pos.CENTER);
        GridPane rootLevel2 = new GridPane();
        
        rootLevel2.add(labelLevel2,1,0);     
        rootLevel2.add(imgViewLevel2,1,1);
          
        rootLevel2.add(boxLevel2,1,2);
        rootLevel2.setBackground(Background.EMPTY);
        rootLevel2.setPadding(new Insets(20,20,20,20));
        Scene sceneLevel2 = new Scene(rootLevel2, 640, 670,Color.DARKSALMON);
        
        stageLevel2.setTitle("Image Puzzle Game");
        stageLevel2.setScene(sceneLevel2);
        stageLevel2.show(); 
        stageEnd1.close();
        stageChoice.close();
        startTime2=System.currentTimeMillis();
    }
    public void startLevel2(){
        ImageView[][] imgView2=new ImageView[4][4];
        int[] swp1={0,0,1,1,2,2,3,4,4,5,5,6,6,7,8,8,9,9,10,10,11,12,13,14};
        int[] swp2={1,4,2,5,3,6,7,5,8,6,9,7,10,11,9,12,10,13,11,14,15,13,14,15}; 
       
        stage3.setTitle("LEVEL-2");
        Button reShuffle=new Button();
        reShuffle.setMinWidth(150);
        reShuffle.setText("RE-SHUFFLE");
        Button back=new Button();
        back.setText("BACK");
        back.setMinWidth(150);
        String[] path=new String[16];
        
        for(int i=0;i<path.length;i++){
            path[i]=(pMain+"Level2\\"+(i+1)+".PNG");
        }
        for(int i=0;i<16;i++){
            list.add(i);
        }
        Collections.shuffle(list);
           
        Image[] img1=new Image[16];
          
        for(int i=0;i<img1.length;i++){
            img1[i]=new Image(path[list.get(i)]);
        }
           
            
        for(int i=0;i<imgView2.length;i++){
            for(int j=0;j<imgView2[0].length;j++){
                imgView2[i][j]=new ImageView(img1[i*4+j]);
                imgView2[i][j].setFitWidth(150);
                imgView2[i][j].setFitHeight(150);
                imgView2[i][j].setId("id"+list.get(i*4+j));
            }
        }
          
        GridPane gridPane=new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
             
        for(int i=0;i<imgView2.length;i++){
            for(int j=0;j<imgView2[0].length;j++){
                gridPane.add(imgView2[i][j], i, j);
            }
        }
        reShuffle.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                stage3.close();
                list.clear();
                startLevel2();
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
               stage3.close();
               next1();
            }
        });
            
        imgView2[0][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=0;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[1][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                   b=1;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
        imgView2[2][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=2;
                try { 
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[3][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=3;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[0][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=4;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[1][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=5;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        });
        imgView2[2][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=6;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[3][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=7;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[0][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=8;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[1][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=9;
                try {  
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[2][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=10;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[3][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=11;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[0][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=12;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[1][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=13;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[2][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
               b=14;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView2[3][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=15;
                try {
                    onClicked(imgView2,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        gridPane.add(reShuffle, 1, 4);
        gridPane.add(back, 2, 4);
        Scene scene3 = new Scene(gridPane, 610, 650);
        stage3.setScene(scene3);
        stage3.show();
        stageLevel2.hide();
    }
     public void next2(){
  
        stageLevel3.setTitle("Puzzle");
        Button next=new Button();
        next.setText("START Level-3");
        
        next.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                list.clear();
                startLevel3();
            }
        });
        Image imgLevel3=new Image(pMain+"real3.jpg");
        ImageView imgViewLevel3=new ImageView(imgLevel3);
        imgViewLevel3.setFitWidth(600);
        imgViewLevel3.setFitHeight(600);
        
        VBox boxLevel3=new VBox();
        boxLevel3.getChildren().add(next);
        boxLevel3.setAlignment(Pos.BOTTOM_CENTER);
        Label labelLevel3=new Label();
        labelLevel3.setText("EXPECTED IMAGE");
        labelLevel3.setPrefWidth(500);
        labelLevel3.setAlignment(Pos.CENTER);
        GridPane rootLevel3 = new GridPane();
        
        rootLevel3.add(labelLevel3,1,0);     
        rootLevel3.add(imgViewLevel3,1,1);
          
        rootLevel3.add(boxLevel3,1,2);
        rootLevel3.setPadding(new Insets(20,20,20,20));
        rootLevel3.setBackground(Background.EMPTY);
        Scene sceneLevel3 = new Scene(rootLevel3, 640, 670,Color.MEDIUMSEAGREEN);
        
        stageLevel3.setTitle("Image Puzzle Game");
        stageLevel3.setScene(sceneLevel3);
        stageLevel3.show(); 
        stageEnd2.close();
        stageChoice.close();
        startTime3=System.currentTimeMillis();
    }
    public void startLevel3(){
        ImageView[][] imgView3=new ImageView[5][5];
        int[] swp1={0,0,1,1,2,2,3,3,4,5,5,6,6,7,7,8,8,9,10,10,11,11,12,12,13,13,14,15,15,16,16,17,17,18,18,19,20,21,22,23};
        int[] swp2={1,5,2,6,3,7,4,8,9,6,10,7,11,8,12,9,13,14,11,15,12,16,13,17,14,18,19,16,20,17,21,18,22,19,23,24,21,22,23,24}; 
       
        stage3.setTitle("LEVEL-3");
        Button reShuffle=new Button();
        reShuffle.setMinWidth(120);
        reShuffle.setText("RE-SHUFFLE");
        Button back=new Button();
        back.setText("BACK");
        back.setMinWidth(120);
        String[] path=new String[25];
        
        for(int i=0;i<path.length;i++){
            path[i]=(pMain+"Level3\\"+(i+1)+".PNG");
        }
        for(int i=0;i<25;i++){
            list.add(i);
        }
        Collections.shuffle(list);
           
        Image[] img1=new Image[25];
          
        for(int i=0;i<img1.length;i++){
            img1[i]=new Image(path[list.get(i)]);
        }
           
            
        for(int i=0;i<imgView3.length;i++){
            for(int j=0;j<imgView3[0].length;j++){
                imgView3[i][j]=new ImageView(img1[i*5+j]);
                imgView3[i][j].setFitWidth(120);
                imgView3[i][j].setFitHeight(120);
                imgView3[i][j].setId("id"+list.get(i*5+j));
            }
        }
          
        GridPane gridPane=new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
             
        for(int i=0;i<imgView3.length;i++){
            for(int j=0;j<imgView3[0].length;j++){
                gridPane.add(imgView3[i][j], i, j);
            }
        }
        reShuffle.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                stage4.close();
                list.clear();
                startLevel3();
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
               stage4.close();
               next2();
            }
        });
            
        imgView3[0][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=0;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[1][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                   b=1;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
        imgView3[2][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=2;
                try { 
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[3][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=3;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[4][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=4;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[0][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=5;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[1][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=6;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
        });
        imgView3[2][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=7;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[3][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=8;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[4][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=9;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[0][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=10;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[1][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=11;
                try {  
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[2][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=12;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[3][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=13;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[4][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=14;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[0][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=15;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[1][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=16;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[2][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
               b=17;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[3][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                b=18;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[4][3].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=19;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[0][4].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=20;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[1][4].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=21;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[2][4].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=22;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[3][4].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=23;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView3[4][4].setOnMouseClicked(new EventHandler<MouseEvent>(){
            
            @Override
            public void handle(MouseEvent event) {
                 b=24;
                try {
                    onClicked(imgView3,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        gridPane.add(reShuffle, 1, 5);
        gridPane.add(back, 3, 5);
        Scene scene4 = new Scene(gridPane, 610, 650);
        stage4.setScene(scene4);
        stage4.show();
        stageLevel3.hide();
    }
    public void startLevel1(){
        ImageView[][] imgView1=new ImageView[3][3];
        int[] swp1={0,0,1,1,2,3,3,4,4,5,6,7};
        int[] swp2={1,3,2,4,5,4,6,5,7,8,7,8}; 
  
        stage2.setTitle("LEVEL-1");
        Button reShuffle=new Button();
        reShuffle.setText("RE-SHUFFLE");
        reShuffle.setMinWidth(150);
        Button back=new Button();
        back.setText("BACK");
        back.setMinWidth(150);
        String[] path=new String[9];
        
        for(int i=0;i<path.length;i++){
            path[i]=(pMain+"Level1\\"+(i+1)+".PNG");
        }
        for(int i=0;i<9;i++){
            list.add(i);
        }
        Collections.shuffle(list);
           
        Image[] img=new Image[9];
          
        for(int i=0;i<img.length;i++){
            img[i]=new Image(path[list.get(i)]);
        }
        for(int i=0;i<imgView1.length;i++){
            for(int j=0;j<imgView1[0].length;j++){
                imgView1[i][j]=new ImageView(img[i*3+j]);
                imgView1[i][j].setFitWidth(150);
                imgView1[i][j].setFitHeight(150);
                imgView1[i][j].setId("id"+list.get(i*3+j));
            }
        }
          
        GridPane gridPane=new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
             
        for(int i=0;i<imgView1.length;i++){
            for(int j=0;j<imgView1[0].length;j++){
                gridPane.add(imgView1[i][j], i, j);
            }
        }
        reShuffle.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                stage2.close();
                list.clear();
                startLevel1();
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>(){
                
            @Override
            public void handle(ActionEvent event){
                stage2.close();
                startButton();
            }
        });
            
        imgView1[0][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) {
                 b=0;       
                try {
                    onClicked(imgView1,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        imgView1[1][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
            @Override
            public void handle(MouseEvent event) { 
                b=1;
                try {
                    onClicked(imgView1,swp1,swp2,b);
                } catch (IOException ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
            imgView1[2][0].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=2;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[0][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=3;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[1][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=4;
                    try {  
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[2][1].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=5;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[0][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=6;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[1][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=7;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            imgView1[2][2].setOnMouseClicked(new EventHandler<MouseEvent>(){
                 
                @Override
                public void handle(MouseEvent event) {
                     b=8;
                    try {
                        onClicked(imgView1,swp1,swp2,b);
                    } catch (IOException ex) {
                        Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
          
        gridPane.add(reShuffle, 0, 4);
        gridPane.add(back, 2, 4);
        Scene scene2 = new Scene(gridPane, 460, 500);
        stage2.setScene(scene2);
        stage2.show();
        stageLevel1.hide();
       }
    public void startButton(){
        Button Level1= new Button();
        Level1.setText("START LEVEL-1");
        VBox boxLevel1=new VBox();
        Label labelLevel1=new Label();
        labelLevel1.setText("EXPECTED IMAGE");
        labelLevel1.setPrefWidth(600);
        labelLevel1.setPrefHeight(50);
        labelLevel1.setAlignment(Pos.CENTER);
       
        Image imgLevel1= new Image(pMain+"real.jpg");
        ImageView imgViewLevel1 = new ImageView(imgLevel1);
        imgViewLevel1.setFitWidth(600);
        imgViewLevel1.setFitHeight(550);
       
        Level1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              startLevel1();
            }
        });
        
        GridPane rootLevel1= new GridPane();
        boxLevel1.getChildren().add(Level1);
        boxLevel1.setAlignment(Pos.BOTTOM_CENTER);
        rootLevel1.add(labelLevel1,1,0);     
        rootLevel1.add(imgViewLevel1,1,1);
        rootLevel1.add(boxLevel1,1,2);
        rootLevel1.setPadding(new Insets(20,20,20,20));
        rootLevel1.setBackground(Background.EMPTY);
        Scene sceneLevel1 = new Scene(rootLevel1, 640, 670,Color.PLUM);
        stageLevel1.setTitle("Image Puzzle Game");
        stageLevel1.setScene(sceneLevel1);
        stageLevel1.show(); 
        stage1.hide();
        stageSignIn.close();
        stageChoice.hide();
        startTime1=System.currentTimeMillis();
    }
    public void signUp(){
        GridPane signUpgrid=new GridPane();
        Label upId=new Label();
        upId.setText("ENTER YOUR ID");
        upId.setPrefWidth(160);
        Label upName=new Label();
        upName.setText("ENTER YOUR NAME");
        upName.setPrefWidth(160);
        Label upAge=new Label();
        upAge.setText("ENTER YOUR AGE");
        upAge.setPrefWidth(160);
        Label upGender=new Label();
        upGender.setText("ENTER YOUR GENDER");
        upGender.setPrefWidth(160);
        Label upPass=new Label();
        upPass.setText("ENTER YOUR PASSWORD");
        upPass.setPrefWidth(160);
        TextField id1=new TextField();
        id1.setMinWidth(200);
        TextField name1=new TextField();
        name1.setMinWidth(200);
        TextField age1=new TextField();
        age1.setMinWidth(200);
        TextField gender1=new TextField();
        gender1.setMinWidth(200);
        TextField pass1=new TextField();
        pass1.setMinWidth(200);
        signUpgrid.add(upId,0,0);
        signUpgrid.add(upName,0,1);
        signUpgrid.add(upAge,0,2);
        signUpgrid.add(upGender,0,3);
        signUpgrid.add(upPass,0,4);
        signUpgrid.add(id1,1,0);
        signUpgrid.add(name1,1,1);
        signUpgrid.add(age1,1,2);
        signUpgrid.add(gender1,1,3);
        signUpgrid.add(pass1,1,4);
        
        Button btnSignUp=new Button();
        btnSignUp.setText("SIGN UP");
        btnSignUp.setMinWidth(150);
        Button back=new Button();
        back.setText("BACK TO THE MAIN MENU");
        back.setMinWidth(210);
        HBox boxUp=new HBox();
        VBox boxVup=new VBox();
        boxUp.getChildren().addAll(btnSignUp,back);
        boxVup.getChildren().addAll(signUpgrid,boxUp);
        boxVup.setPadding(new Insets(20, 30, 30, 40));
        boxVup.setSpacing(10);
        btnSignUp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event){
                try {
                    userList(id1,name1,age1,gender1,pass1);
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
                userList.clear();
                signIn();
               
            }
        });
       
         back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                    stageSignUp.close();
                try {
                    start(stage1);
                } catch (Exception ex) {
                   System.out.println(ex.getMessage());
                }
            }
        });
         
           
        BorderPane upPane=new BorderPane();
        upPane.setCenter(boxVup);
        upPane.setBackground(Background.EMPTY);
        Scene sceneSignUp = new Scene(upPane, 440, 190,Color.LIGHTSTEELBLUE);
        stageSignUp.setTitle("Sign up");
        stageSignUp.setScene(sceneSignUp);
        stageSignUp.show();
        stage1.hide();
    }
    public void userList(TextField id1,TextField name1,TextField age1,TextField gender1,TextField pass1) throws FileNotFoundException, IOException, ClassNotFoundException{
            User user1=new User(id1.getText(),name1.getText(),Integer.parseInt(age1.getText()),gender1.getText(),pass1.getText());
            userList.clear();
             
            try{
                FileInputStream inputStream=new FileInputStream(f1);
                ObjectInputStream input=new ObjectInputStream(inputStream);  
                Object user;
               while((user=input.readObject())!=null){
                   userList.add((User)user);
               }
               input.close();
               inputStream.close();
                }catch(Exception ex){
                   
                }
            
            userList.add(user1);
               
            FileOutputStream outputStream=new FileOutputStream(f1,false);
            ObjectOutputStream output=new ObjectOutputStream(outputStream);
            for (User userList1 : userList) {
                output.writeObject(userList1);
            }
            output.close();
            outputStream.close();
                  
        id1.setText(" ");
        name1.setText(" ");
        age1.setText(" ");
        gender1.setText(" ");
        pass1.setText(" ");         
    }
    public void signIn(){
        GridPane signIngrid=new GridPane();
        Label inId=new Label();
        inId.setText("ENTER YOUR ID");
        inId.setPrefWidth(160);
        Label inPass=new Label();
        inPass.setText("ENTER YOUR PASSWORD");
        inPass.setPrefWidth(160);
        TextField id2=new TextField();
        id2.setMinWidth(200);
        TextField pass2=new TextField();
        pass2.setMinWidth(200);
        signIngrid.add(inId,0,0);
        signIngrid.add(inPass,0,1);
        signIngrid.add(id2,1,0);
        signIngrid.add(pass2,1,1);
        
        Button btnSignIn=new Button();
        btnSignIn.setText("Log In");
        btnSignIn.setMinWidth(150);
        Button back=new Button();
        back.setText("BACK TO THE MAIN MENU");
        back.setMinWidth(210);
        Label match=new Label();
        HBox boxIn=new HBox();
        VBox boxVin=new VBox();
        
        btnSignIn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                try {
                    if(matchUser(id2.getText(),pass2.getText())!=-1){
                        userNo=matchUser(id2.getText(),pass2.getText());
                        choice();
                    }
                    else{
                        match.setText("**could not match id or password!!**");
                        match.setMinWidth(350);
                        match.setAlignment(Pos.CENTER);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                   System.out.println(ex.getMessage());
                } catch (ClassNotFoundException ex) {
                     System.out.println(ex.getMessage());
                }
            }
        });
         back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                try {
                    stageSignIn.close();
                    start(stage1);
                } catch (Exception ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        boxIn.getChildren().addAll(btnSignIn,back);
        boxVin.getChildren().addAll(signIngrid,match,boxIn);
        boxVin.setPadding(new Insets(20, 30, 30, 40));
        boxVin.setSpacing(10);
        BorderPane inPane=new BorderPane();
        inPane.setCenter(boxVin);
        inPane.setBackground(Background.EMPTY);
        Scene sceneSignIn = new Scene(inPane, 450, 140,Color.LIGHTSEAGREEN);
        stageSignIn.setTitle("Sign In");
        stageSignIn.setScene(sceneSignIn);
        stageSignIn.show();
        stageSignUp.close();
        stage1.hide();
    }
    public int matchUser(String id,String pass) throws FileNotFoundException, IOException, ClassNotFoundException{
        userList.clear();
        try{
              FileInputStream inputStream=new FileInputStream(f1);
               ObjectInputStream input=new ObjectInputStream(inputStream);
               Object user = input.readObject();
               while(user!=null){
                   userList.add((User)user);
                   user = input.readObject();
               }
              input.close();
              inputStream.close();
        }
        catch(Exception ex){
            
        }
       
        int index=-1;
        for(int i=0;i<userList.size();i++){
            if(userList.get(i).getId().equals(id)){
                if(userList.get(i).getPassword().equals(pass)){
                    index=i;
                    break;
                }
            }
        }
        return index;
    }
    public void scoreBoard() throws FileNotFoundException, IOException{
         userList.clear();
         FileInputStream inputStream=new FileInputStream(f1);
         ObjectInputStream input=new ObjectInputStream(inputStream);
        try{ 
            Object user;
            while((user=input.readObject())!=null){
                userList.add((User)user);
            }
        }
        catch(Exception ex){
           
        }
        input.close();
        inputStream.close();

        GridPane boxScore=new GridPane();
        boxScore.setHgap(4);
        boxScore.setVgap(4);
        Label[] labelUser=new Label[userList.size()];
        Label[] labelScore1=new Label[userList.size()];
        Label[] labelScore2=new Label[userList.size()];
        Label[] labelScore3=new Label[userList.size()];
        Label info=new Label();
        info.setText("PLAYER INFO");
        info.setMinWidth(500);
        info.setAlignment(Pos.CENTER);
        Label score1=new Label();
        score1.setText("LEVEL 1 SCORE");
        score1.setMinWidth(150);
        score1.setAlignment(Pos.CENTER);
        Label score2=new Label();
        score2.setText("LEVEL 2 SCORE");
        score2.setMinWidth(150);
        score2.setAlignment(Pos.CENTER);
        Label score3=new Label();
        score3.setText("LEVEL 3 SCORE");
        score3.setMinWidth(150);
        score3.setAlignment(Pos.CENTER);
        
        boxScore.add(info,0,0);
        boxScore.add(score1,1,0);
        boxScore.add(score2,2,0);
        boxScore.add(score3,3,0);
        
        for(int i=0;i<labelScore1.length;i++){
            labelUser[i]=new Label();
            labelUser[i].setText("player no("+(i+1)+"):  "+userList.get(i).toString());
            labelUser[i].setMinWidth(500);
            
            labelScore1[i]=new Label();
            double sc1=userList.get(i).getScore1()/1000.0;
            if(sc1!=0){
               labelScore1[i].setText(" "+sc1+" sec ");
            }
            else{
                labelScore1[i].setText("not played yet");
            }
            labelScore1[i].setMinWidth(150);
            labelScore1[i].setAlignment(Pos.CENTER);
            
            labelScore2[i]=new Label();
            double sc2=userList.get(i).getScore2()/1000.0;
            if(sc2!=0){
               labelScore2[i].setText(" "+sc2+" sec ");
            }
            else{
                labelScore2[i].setText("not played yet");
            }
            labelScore2[i].setMinWidth(150);
            labelScore2[i].setAlignment(Pos.CENTER);
            
            labelScore3[i]=new Label();
            double sc3=userList.get(i).getScore3()/1000.0;
            if(sc3!=0){
               labelScore3[i].setText(" "+sc3+" sec ");
            }
            else{
                labelScore3[i].setText("not played yet");
            }
            labelScore3[i].setMinWidth(150);
            labelScore3[i].setAlignment(Pos.CENTER);
            
            boxScore.add(labelUser[i],0,i+1);
            boxScore.add(labelScore1[i],1,i+1);
            boxScore.add(labelScore2[i],2,i+1);
            boxScore.add(labelScore3[i],3,i+1);
        }
        BorderPane scorePane=new BorderPane();
        scorePane.setPadding(new Insets(20,20,20,30));
        Button back=new Button();
        back.setText("BACK TO THE MAIN MENU");
        back.setMinWidth(960);
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                try {
                    userList.clear();
                    stageScore.close();
                    start(stage1);
                } catch (Exception ex) {
                    
                }
            }
        });
        scorePane.setCenter(boxScore);
        scorePane.setBottom(back);
        scorePane.setBackground(Background.EMPTY);
        Scene sceneScore = new Scene(scorePane, 1020, 700,Color.LAVENDER);
        stageScore.setTitle("Score Board");
        stageScore.setScene(sceneScore);
        stageScore.show();
        stage1.hide();
    }
    public void choice(){
        Button stg1=new Button();
        stg1.setText("PLAY LEVEL-1");
        stg1.setMinWidth(300);
        stg1.setMinHeight(70);
        
        Button stg2=new Button();
        stg2.setText("PLAY LEVEL-2");
        stg2.setMinWidth(300);
        stg2.setMinHeight(70);
        
        Button stg3=new Button();
        stg3.setText("PLAY LEVEL-3");
        stg3.setMinWidth(300);
        stg3.setMinHeight(70);
        
        Button back=new Button();
        back.setText("BACK TO MAIN MENU");
        back.setMinWidth(300);
        back.setMinHeight(70);
        
        stg1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              startButton();
            }
        });
        
        stg2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              next1();
            }
        });
        
        stg3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              next2();
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                try {
                    start(stage1);
                } catch (Exception ex) {
                    Logger.getLogger(PuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        VBox boxChoice=new VBox();
        boxChoice.getChildren().addAll(stg1,stg2,stg3,back);
        boxChoice.setPadding(new Insets(100,100,100,100));
        boxChoice.setBackground(Background.EMPTY);
 
        Scene sceneChoice = new Scene(boxChoice, 500, 470,Color.PURPLE);
        
        stageChoice.setScene(sceneChoice);
        stageChoice.show(); 
        stageSignIn.close();
        stage1.hide();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage1=primaryStage;
        
        Button mode1 = new Button();
        mode1.setText("Play on guest mode");
        mode1.setMinWidth(150);
        Button mode2 = new Button();
        mode2.setText("Create new account");
        mode2.setMinWidth(150);
        Button mode3 = new Button();
        mode3.setText("Sign In");
        mode3.setMinWidth(150);
        Button mode4 = new Button();
        mode4.setText("Score Board");
        mode4.setMinWidth(150);
        
        Image imgStart= new Image(pMain+"picmain.PNG");
        ImageView imgViewStart = new ImageView(imgStart);
        imgViewStart.setFitWidth(600);
        imgViewStart.setFitHeight(600);
       
        mode1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              choice();
            }
        });
        mode2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                
                    signUp();
                
            }
        });
        mode3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
              signIn();
            }
        });
        mode4.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event){
                try {
                    scoreBoard();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        HBox boxStart=new HBox();
        VBox boxVstart=new VBox();
      
       boxStart.getChildren().addAll(mode1,mode2,mode3,mode4);
       boxVstart.getChildren().addAll(boxStart,imgViewStart);
        BorderPane pane=new BorderPane();
        pane.setCenter(boxVstart);
        pane.setPadding(new Insets(20,20,20,20));
        pane.setBackground(Background.EMPTY);
        Scene sceneStart = new Scene(pane, 640, 650,Color.DARKBLUE);
        stage1.setTitle("Image Puzzle Game");
        stage1.setScene(sceneStart);
        stage1.show();    
        stageEnd1.close();
        stageEnd2.close();
        stageEnd3.close();
        stageChoice.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}