package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Program extends Application{
	
	private int n=45,m=35;
	private int x,y;
	private int grainN;
	private int grainNdraw=50;
	private Cell cell[][] = new Cell[n][m];
	private Grain grid[][] = new Grain[x][y];
	private AnchorPane root = new AnchorPane();
	private Pane gridPane = new Pane();
	private ScrollPane scrollPane = new ScrollPane();
	private Button startButton = new Button("Start");
	private Button restartButton = new Button("Wyczyœæ");
	private Button stopButton = new Button("Stop");
	private Button exitButton = new Button("Wyjœcie");
	private Button okButton = new Button("OK");
	private Button okButtonR = new Button("OK");
	private Button okButtonN = new Button("OK");
	private Button okMC = new Button("OK");
	private final Label infoLabel = new Label();
	private TextField textColumns = new TextField ();
	private TextField textRows = new TextField ();
	private TextField textN = new TextField ();
	private TextField textR = new TextField ();
	private TextField textMC = new TextField ();
	private MenuBar menuBar = new MenuBar();
    private Menu menuProgram = new Menu("Program");
    private Menu menuMethod = new Menu("S¹siedztwa");
    private Menu menuDraw = new Menu("Rozmieszczenie zarodków");
    private Menu bc = new Menu("Warunki brzegowe");
    private CheckMenuItem nrz = new CheckMenuItem("Naiwny rozrost ziaren");
    private CheckMenuItem gof = new CheckMenuItem("Gra w ¿ycie");
    private CheckMenuItem vonNeumann = new CheckMenuItem("vonNeumann");
    private CheckMenuItem moore = new CheckMenuItem("Moore");
    private CheckMenuItem heksagonalL = new CheckMenuItem("Heksagonal left");
    private CheckMenuItem heksagonalR = new CheckMenuItem("Heksagonal right");
    private CheckMenuItem heksagonalRand = new CheckMenuItem("Heksagonal losowe");
    private CheckMenuItem pentagonalRand = new CheckMenuItem("Pentagonal losowe");
    private CheckMenuItem rand = new CheckMenuItem("Losowe");
    private CheckMenuItem regular = new CheckMenuItem("Równomierne");
    private CheckMenuItem randR = new CheckMenuItem("Losowe z promieniem R");
    private CheckMenuItem monteCarlo = new CheckMenuItem("Monte Carlo");
	private CheckMenuItem periodic = new CheckMenuItem("Periodyczne");
	private CheckMenuItem noPeriodic = new CheckMenuItem("Nieperiodyczne");
	private int flag=0;
	private boolean runningGame;
	private boolean runningGrains;
	private int counterMC=100;
	private int selectedMethod;
	private List<Color> colorList = new ArrayList<Color>();
	private List<Color> colorListMC = new ArrayList<Color>();
	private Color c;
	private Color cMC;
	private int R=5;
	private int tmpX,tmpY,tmpN,tmpR,tmpMC;
	private Game game = new Game();
    private Neighbourhood neighbourhood = new Neighbourhood();
    private Program program=this;

	public static void main(String[] args){
		launch(args);
	}

	@Override
    public void start(Stage primaryStage) { 
		generateColors();
		
		 nrz.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) {
		         programChoice("Naiwny rozrost ziaren"); 
		         nrz.setSelected(true);
		         gof.setSelected(false);
			 }
		 });    
		 
		 gof.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) {
				 programChoice("Gra w ¿ycie"); 
				 gof.setSelected(true);
				 nrz.setSelected(false);
			 }
		 });
		 
		 vonNeumann.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(true);
		         moore.setSelected(false);
		         heksagonalL.setSelected(false);
		         heksagonalR.setSelected(false);
		         heksagonalRand.setSelected(false);
		         pentagonalRand.setSelected(false);
		         selectedMethod=1;
			 }
		 }); 
		 
		 moore.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(false);
		         moore.setSelected(true);
		         heksagonalL.setSelected(false);
		         heksagonalR.setSelected(false);
		         heksagonalRand.setSelected(false);
		         pentagonalRand.setSelected(false);
		         selectedMethod=2;
			 }
		 });
		 
		 heksagonalL.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(false);
		         moore.setSelected(false);
		         heksagonalL.setSelected(true);
		         heksagonalR.setSelected(false);
		         heksagonalRand.setSelected(false);
		         pentagonalRand.setSelected(false);
		         selectedMethod=3;
			 }
		 });
		 
		 heksagonalR.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(false);
		         moore.setSelected(false);
		         heksagonalL.setSelected(false);
		         heksagonalR.setSelected(true);
		         heksagonalRand.setSelected(false);
		         pentagonalRand.setSelected(false);
		         selectedMethod=4;
			 }
		 });
		 
		 heksagonalRand.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(false);
		         moore.setSelected(false);
		         heksagonalL.setSelected(false);
		         heksagonalR.setSelected(false);
		         heksagonalRand.setSelected(true);
		         pentagonalRand.setSelected(false);
		         selectedMethod=5;
			 }
		 });
		 
		 pentagonalRand.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         vonNeumann.setSelected(false);
		         moore.setSelected(false);
		         heksagonalL.setSelected(false);
		         heksagonalR.setSelected(false);
		         heksagonalRand.setSelected(false);
		         pentagonalRand.setSelected(true);
		         selectedMethod=6;
			 }
		 });
		 
		 rand.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         rand.setSelected(true);
		         regular.setSelected(false);
		         randR.setSelected(false);
		         monteCarlo.setSelected(false);
		         for (int i = 1; i < x-1; i++)
		         {
		             for (int j = 1; j < y-1; j++)
		             {
		             	grid[i][j].setState(false);
		             	grid[i][j].setGrainId(0);
		             	grid[i][j].setColor(Color.WHITE);
		             }
		         }
		         generateColors();
		         grainN=0;
		         randomDraw();
			 }
		 });
		 
		 regular.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         rand.setSelected(false);
		         regular.setSelected(true);
		         randR.setSelected(false);
		         monteCarlo.setSelected(false);
		         for (int i = 1; i < x-1; i++)
		         {
		             for (int j = 1; j < y-1; j++)
		             {
		             	grid[i][j].setState(false);
		             	grid[i][j].setGrainId(0);
		             	grid[i][j].setColor(Color.WHITE);
		             }
		         }
		         generateColors();
		         grainN=0;
		         regularDraw();
			 }
		 });
		 
		 randR.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         rand.setSelected(false);
		         regular.setSelected(false);
		         randR.setSelected(true);
		         monteCarlo.setSelected(false);
		         for (int i = 1; i < x-1; i++)
		         {
		             for (int j = 1; j < y-1; j++)
		             {
		             	grid[i][j].setState(false);
		             	grid[i][j].setGrainId(0);
		             	grid[i][j].setColor(Color.WHITE);
		             }
		         }
		         generateColors();
		         grainN=0;
		         //randomRdraw();
			 }
		 });
		 
		 monteCarlo.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         rand.setSelected(false);
		         regular.setSelected(false);
		         randR.setSelected(false);
		         monteCarlo.setSelected(true);
		         for (int i = 1; i < x-1; i++)
		         {
		             for (int j = 1; j < y-1; j++)
		             {
		             	grid[i][j].setState(false);
		             	grid[i][j].setGrainId(0);
		             	grid[i][j].setColor(Color.WHITE);
		             }
		         }
		         generateColorsMC();
		         grainN=0;
		         monteCarlo();
			 }
		 });
		 
		 periodic.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
		         periodic.setSelected(true);
		         noPeriodic.setSelected(false);
		         neighbourhood.periodicBoundaries();
			 }
		 });
		 
		 noPeriodic.setOnAction(new EventHandler<ActionEvent>() {
			 public void handle(ActionEvent t) { 
				 periodic.setSelected(false);
		         noPeriodic.setSelected(true);
		         neighbourhood.noPeriodicBoundaries();
			 }
		 });
		 
		 exitButton.setOnAction(new EventHandler<ActionEvent>() {
	            
	            @Override
	            public void handle(ActionEvent event) {
	            	infoLabel.setText("");
	            	runningGame=false;
	            	runningGrains=false;
	            	primaryStage.close();
	            }
	     });
		 
		menuProgram.getItems().addAll(nrz);
		menuProgram.getItems().addAll(gof);
		menuMethod.getItems().addAll(vonNeumann);
		menuMethod.getItems().addAll(moore);
		menuMethod.getItems().addAll(heksagonalL);
		menuMethod.getItems().addAll(heksagonalR);
		menuMethod.getItems().addAll(heksagonalRand);
		menuMethod.getItems().addAll(pentagonalRand);
		menuDraw.getItems().addAll(rand);
		menuDraw.getItems().addAll(regular);
		menuDraw.getItems().addAll(randR);
		menuDraw.getItems().addAll(monteCarlo);
		bc.getItems().addAll(periodic);
		bc.getItems().addAll(noPeriodic);
		menuBar.getMenus().addAll(menuProgram, menuMethod, menuDraw, bc);
		menuBar.setPrefWidth(780);
		
        scrollPane.setLayoutX(25);
        scrollPane.setLayoutY(100);
        scrollPane.setPrefSize(725, 540);
        
        infoLabel.setPrefSize(400, 30);
        infoLabel.setLayoutX(25);
        infoLabel.setLayoutY(645);
        
        startButton.setPrefSize(70, 30);
        startButton.setLayoutX(380);
        startButton.setLayoutY(50);
        
        stopButton.setLayoutX(480);
        stopButton.setLayoutY(50);
        stopButton.setPrefSize(70, 30);
        
        restartButton.setLayoutX(580);
        restartButton.setLayoutY(50);
        restartButton.setPrefSize(70, 30);
        
        exitButton.setLayoutX(680);
        exitButton.setLayoutY(50);
        exitButton.setPrefSize(70, 30);
        
        okButton.setLayoutX(200);
        okButton.setLayoutY(50);
        okButton.setPrefSize(40, 30);
        
        okButtonR.setLayoutX(700);
        okButtonR.setLayoutY(650);
        okButtonR.setPrefSize(35, 25);
        
        okButtonN.setLayoutX(570);
        okButtonN.setLayoutY(650);
        okButtonN.setPrefSize(35, 25);
        
        okMC.setLayoutX(425);
        okMC.setLayoutY(650);
        okMC.setPrefSize(35, 25);
        
        textRows.setLayoutX(25);
        textRows.setLayoutY(50);
        textRows.setPrefSize(70, 30);
        textRows.setPromptText("Wiersze");
        
        textColumns.setLayoutX(110);
        textColumns.setLayoutY(50);
        textColumns.setPrefSize(70, 30);
        textColumns.setPromptText("Kolumny");
        
        textN.setLayoutX(485);
        textN.setLayoutY(650);
        textN.setPrefSize(75, 25);
        textN.setPromptText("Iloœæ ziaren");
        
        textR.setLayoutX(630);
        textR.setLayoutY(650);
        textR.setPrefSize(60, 25);
        textR.setPromptText("Promieñ");
        
        textMC.setLayoutX(345);
        textMC.setLayoutY(650);
        textMC.setPrefSize(70, 25);
        textMC.setPromptText("Kroki MC");
        
        scrollPane.setContent(gridPane);
        root.getChildren().add(scrollPane);
        root.getChildren().add(infoLabel);
        root.getChildren().add(startButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(exitButton);
        root.getChildren().add(restartButton);
        root.getChildren().add(okButton);
        root.getChildren().add(okButtonR);
        root.getChildren().add(okButtonN);
        root.getChildren().add(okMC);
        root.getChildren().add(textColumns);
        root.getChildren().add(textRows);
        root.getChildren().add(textN);
        root.getChildren().add(textR);
        root.getChildren().add(textMC);
        root.getChildren().add(menuBar);
        
        Scene scene = new Scene(root, 770, 670);
        primaryStage.setTitle("Modelowanie wieloskalowe");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	
	public void cellInit(){
		for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
            	cell[i][j]=new Cell();
            }
        }
	}
	
	public void cellDelete(){
		for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
            	cell[i][j]=null;
            }
        }
	}
	
	public void drawCellsGrid(){
		for (int i = 1; i < n-1; i++)
        {
            for (int j = 1; j < m-1; j++)
            {
            	cell[i][j].setX((i-1)*17);
            	cell[i][j].setY((j-1)*17);
            	gridPane.getChildren().add(cell[i][j]);
            }
        }
	}
	
	public void grainInit(int x, int y){
		for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
            	grid[i][j]=new Grain(i,j);
            	grid[i][j].setProgram(program);
            	grid[i][j].setState(false);
            	grid[i][j].setGrainId(0);
            }
        }
	}
	
	public void drawGrainsGrid(int x, int y){
		for (int i = 1; i < x-1; i++)
        {
            for (int j = 1; j < y-1; j++)
            {
            	grid[i][j].setX((j-1)*6);
            	grid[i][j].setY((i-1)*6);
            	gridPane.getChildren().add(grid[i][j]);
            }
        }
	}
	
	public void programChoice(String choice){
		if(choice.equals("Gra w ¿ycie")){
			gridPane.getChildren().clear();
		    grid=null;
			infoLabel.setText(" ");
			cell=new Cell[n][m];
			cellInit();
			drawCellsGrid();
			game.setGrid(cell);
			
			vonNeumann.setDisable(true);
		    moore.setDisable(true);
		    heksagonalL.setDisable(true);
		    heksagonalR.setDisable(true);
		    heksagonalRand.setDisable(true);
		    pentagonalRand.setDisable(true);
		    rand.setDisable(true);
		    regular.setDisable(true);
		    randR.setDisable(true);
		    monteCarlo.setDisable(true);
			periodic.setDisable(true);
			noPeriodic.setDisable(true);
			
            startButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < m; j++)
                        {
                        	if(cell[i][j].getState()){
                        		flag=1;
                        	}
                        }
                    }
                	if(flag==1){
                		infoLabel.setText("");
                		runningGame=true;
                		Thread thread = new Thread() {
                            public void run() {
                                while (runningGame) {
                                    game.nextStep();
                                    try {
    									Thread.sleep(500);
    								} catch (InterruptedException e) {
    									e.printStackTrace();
    								}
                                }
                            }
                        };
                        thread.start();
                        startButton.setDisable(true);
                        gof.setDisable(true);
            			nrz.setDisable(true);
                	}else{
                		infoLabel.setText("Nie wybrano komórek!");
                	}
                }
            });
            
            stopButton.setOnAction(new EventHandler<ActionEvent>() {
     
                @Override
                public void handle(ActionEvent event) {
                	infoLabel.setText("");
                	runningGame=false;
                    startButton.setDisable(false);
                    gof.setDisable(false);
        			nrz.setDisable(false);
                }
            });
            
            restartButton.setOnAction(new EventHandler<ActionEvent>() {
     
                @Override
                public void handle(ActionEvent event) {
                	for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < m; j++)
                        {
                        	cell[i][j].setState(false);
                        }
                    }
                    infoLabel.setText("Plansza zosta³a wyczyszczona");
                    flag=0;
                    runningGame=false;
                    startButton.setDisable(false);
                    gof.setDisable(false);
        			nrz.setDisable(false);
                }
            });
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                }
            });
            okButtonR.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                }
            });
            okButtonN.setOnAction(new EventHandler<ActionEvent>() {
                
                @Override
                public void handle(ActionEvent event) {
                }
            });
            okMC.setOnAction(new EventHandler<ActionEvent>() {
    
            	@Override
            	public void handle(ActionEvent event) {
            	}
            });
		}else{
			gridPane.getChildren().clear();
		    cell=null;
			infoLabel.setText(" ");
			x=102;
			y=122;
        	grid = new Grain[x][y];
        	grainInit(x,y);
			neighbourhood.setN(x);
			neighbourhood.setM(y);
        	neighbourhood.setGrid(grid);
        	neighbourhood.initRowColumn();
            drawGrainsGrid(x,y);
            colorList.removeAll(colorList);
            grainN=0;
            generateColors();
			
			vonNeumann.setDisable(false);
		    moore.setDisable(false);
		    heksagonalL.setDisable(false);
		    heksagonalR.setDisable(false);
		    heksagonalRand.setDisable(false);
		    pentagonalRand.setDisable(false);
		    rand.setDisable(false);
		    regular.setDisable(false);
		    randR.setDisable(false);
		    monteCarlo.setDisable(false);
			periodic.setDisable(false);
			noPeriodic.setDisable(false);
			
			startButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	infoLabel.setText(" ");
        			neighbourhood.initTables();
                    neighbourhood.setNeighbourhoodCase(selectedMethod);
                    runningGrains=true;
                	Thread thread = new Thread() {
                        public void run() {
                            while (runningGrains) {
                                neighbourhood.nextStep();
                                try {
									Thread.sleep(200);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
                            }
                        }
                    };
                    thread.start();
                    startButton.setDisable(true);
                    rand.setDisable(true);
        		    regular.setDisable(true);
        		    randR.setDisable(true);
        		    monteCarlo.setDisable(true);
        		    gof.setDisable(true);
        			nrz.setDisable(true);
                }
            });
            
            stopButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	runningGrains=false;
                    startButton.setDisable(false);
                    //neighbourhood.showIDs();
                    rand.setDisable(false);
        		    regular.setDisable(false);
        		    randR.setDisable(false);
        		    monteCarlo.setDisable(false);
        		    gof.setDisable(false);
        			nrz.setDisable(false);
                }
            });
            
            restartButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	runningGrains=false;
                	for (int i = 0; i < x; i++)
                    {
                        for (int j = 0; j < y; j++)
                        {
                        	grid[i][j].setState(false);
                        	grid[i][j].setGrainId(0);
                        	grid[i][j].setColor(Color.WHITE);
                        }
                    }
                	grainN=0;
                	colorList.removeAll(colorList);
                	generateColors();
                    infoLabel.setText("Plansza zosta³a wyczyszczona");
                    startButton.setDisable(false);
                    rand.setDisable(false);
        		    regular.setDisable(false);
        		    randR.setDisable(false);
        		    monteCarlo.setDisable(false);
        		    gof.setDisable(false);
        			nrz.setDisable(false);
                }
            });
            
            okButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	infoLabel.setText("");
                	if(textColumns.getText().trim().isEmpty()|| textRows.getText().trim().isEmpty()){
                		infoLabel.setText("Pola x oraz y s¹ puste");
                	}else{
                		tmpY=Integer.parseInt(textColumns.getText())+2;
                    	tmpX=Integer.parseInt(textRows.getText())+2;
                		if(tmpX<=502&&tmpY<=502){
                			gridPane.getChildren().clear();
                		    grid=null;
                			setX(tmpX);
                			setY(tmpY);
                        	grid = new Grain[x][y];
                        	infoLabel.setText("");
                        	grainInit(x,y);
                			neighbourhood.setN(x);
                			neighbourhood.setM(y);
                        	neighbourhood.setGrid(grid);
                        	neighbourhood.initRowColumn();
                            drawGrainsGrid(x,y);
                            colorList.removeAll(colorList);
                            grainN=0;
                            generateColors();
                            rand.setDisable(true);
                		    regular.setDisable(true);
                		    randR.setDisable(true);
                		    monteCarlo.setDisable(true);
                		}else{
                			infoLabel.setText("Maksymalna wartoœæ to x=500, y=500");
                		}
                	}
                }
            });
            okButtonR.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	infoLabel.setText("");
                	if(textR.getText().trim().isEmpty()){
                		infoLabel.setText("Nie wpisano promienia!");
                	}else{
                		tmpR=Integer.parseInt(textR.getText());
                		if(tmpR<=5){
                			setR(tmpR);
                			infoLabel.setText("");
                		}else{
                			infoLabel.setText("Maksymalna wartoœæ to r=5");
                		}
                	}
                }
            });
            okButtonN.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                	infoLabel.setText("");
                	if(textN.getText().trim().isEmpty()){
                		infoLabel.setText("Nie wpisano iloœci ziaren!");
                	}else{
                		tmpN=Integer.parseInt(textN.getText());
                		if(0<tmpN&&tmpN<=1000){
                			setN(tmpN);
                			infoLabel.setText("");
                		}else{
                			infoLabel.setText("Minimalne n=1, maksymalne n=1000");
                		}
                	}
                }
            });
            okMC.setOnAction(new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent event) {
            		infoLabel.setText("");
                	if(textMC.getText().trim().isEmpty()){
                		infoLabel.setText("Nie wpisano iloœci kroków!");
                	}else{
                		tmpMC=Integer.parseInt(textMC.getText());
                		if(0<tmpMC&&tmpMC<=1000){
                			setMC(tmpMC);
                			infoLabel.setText("");
                		}else{
                			infoLabel.setText("Minimalnie 1, maksymalne 1000");
                		}
                	}
            	}
            });
		}
	}
	
	public void generateColors(){
		float r,g,b,a;
		for(int i=0; i < 1024; i++){
			r = new Random().nextFloat();
			g = new Random().nextFloat();
			b = new Random().nextFloat();
			a = new Random().nextFloat();
			colorList.add(new Color(r, g, b, a));
		}
	}
	
	public void generateColorsMC(){
		colorListMC.removeAll(colorListMC);
		float r,g,b,a;
		for(int i=0; i < grainNdraw; i++){
			r = new Random().nextFloat();
			g = new Random().nextFloat();
			b = new Random().nextFloat();
			a = new Random().nextFloat();
			colorListMC.add(new Color(r, g, b, a));
		}
	}
	
	public void setGrainPosition(int i, int j){
		if(grainN<=1000){
			grainN++;
			c = colorList.get(new Random().nextInt(colorList.size()));
			grid[i][j].setColor(colorList.get(new Random().nextInt(colorList.size())));
			grid[i][j].setState(true);
			grid[i][j].setGrainId(grainN);
			colorList.remove(c);
		}
	}
	
	public void regularDraw(){
		double pierw=(double) Math.sqrt(grainNdraw);
		int round = (int) Math.ceil(pierw);
		if(round-pierw>0.5){
			round=(int)pierw;
		}
		int gapX = x/round;
		int gapY = y/round;
		grainNdraw=(int)Math.pow(round, 2);
		infoLabel.setText("Zaokr¹glona liczba ziaren: "+grainNdraw);
        
        for(int i=gapX/2; i<x; i+=gapX)
        {
            for(int j=gapY/2; j<y; j+=gapY)
            {       
            	grainN++;
            	c = colorList.get(new Random().nextInt(colorList.size()));
            	grid[i][j].setState(true);
            	grid[i][j].setGrainId(grainN);
            	grid[i][j].setColor(c);
            	colorList.remove(c);
            }
        }
	}
	
	public void randomRdraw(){
	}
	
	public void randomDraw(){
		Random random = new Random();
        boolean randomPosition[][] = new boolean[x][y];
       
        for(int i=0;i<grainNdraw;i++)
        {
        	randomPosition[random.nextInt(x)][random.nextInt(y)] = true;
        }
        for(int i=1;i<x-1;i++)
        {
            for(int j=1;j<y-1;j++)
            {
                if(randomPosition[i][j]){
                	grainN++;
                	c = colorList.get(random.nextInt(colorList.size()));
                	grid[i][j].setState(true);
            		grid[i][j].setGrainId(grainN);
            		grid[i][j].setColor(c);
            		colorList.remove(c);
                }
            }
        }
	}
	
	public void initMonteCarlo(){
		Random random = new Random();
		int id=0;
		for(int i=1;i<x-1;i++)
        {
            for(int j=1;j<y-1;j++)
            {
            	cMC = colorListMC.get(id=random.nextInt(colorListMC.size()));
            	grid[i][j].setState(true);
        		grid[i][j].setGrainId(id+1);
        		grid[i][j].setColor(cMC);
            }
        }
	}
	
	public void monteCarlo(){
		Random random = new Random();
		int id, e, eNext, currentId;
		Color currentColor;
		int tmpCounter;
		initMonteCarlo();
		tmpCounter=counterMC;
		while (tmpCounter>0) {
        	for(int i=1;i<x-1;i++)
            {
                for(int j=1;j<y-1;j++)
                {
                	e=energy(i,j);
                	currentColor=grid[i][j].getColor();
                	currentId=grid[i][j].getGrainId();
                	cMC = colorListMC.get(id=random.nextInt(colorListMC.size()));
                	grid[i][j].setColor(cMC);
        			grid[i][j].setGrainId(id+1);
            		eNext=energy(i,j);
            		if((eNext-e)<=0){
            			//pozostaw nowy kolor ziarna
            		}else{
            			grid[i][j].setColor(currentColor);
            			grid[i][j].setGrainId(currentId);
            		}
                }
            }
        	tmpCounter--;
		}
		tmpCounter=counterMC;
	}
	
	public int energy(int i, int j){
		int E=0;
		if(grid[i-1][j-1].getColor()!=grid[i][j].getColor() && grid[i-1][j-1].getState()!=false)
        {
			E++;
        }
        
        if(grid[i-1][j].getColor()!=grid[i][j].getColor() && grid[i-1][j].getState()!=false)
        {  
        	E++;
        }
        
        if(grid[i-1][j+1].getColor()!=grid[i][j].getColor() && grid[i-1][j+1].getState()!=false)
        {   
        	E++;
        }
        
        if(grid[i][j-1].getColor()!=grid[i][j].getColor() && grid[i][j-1].getState()!=false)
        {
        	E++;
        }
        
        if(grid[i][j+1].getColor()!=grid[i][j].getColor() && grid[i][j+1].getState()!=false)
        {
        	E++;
        }
        
        if(grid[i+1][j-1].getColor()!=grid[i][j].getColor() && grid[i+1][j-1].getState()!=false)
        {
        	E++;
        }
        
        if(grid[i+1][j].getColor()!=grid[i][j].getColor() && grid[i+1][j].getState()!=false)
        {
        	E++;
        }
        
        if(grid[i+1][j+1].getColor()!=grid[i][j].getColor() && grid[i+1][j+1].getState()!=false)
        {
        	E++;
        }
        return E;
	}
	
	public void setR(int r){
		this.R=r;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setN(int n){
		this.grainNdraw=n;
	}
	public void setMC(int mc){
		this.counterMC=mc;
	}
	
}
