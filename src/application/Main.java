package application;
	
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Main extends Application {
	 public static void main(String[] args) 
	    {
	        launch(args);
	    }

	    @Override
	    public void start(Stage theStage) 
	    {
	        theStage.setTitle( "Поїхали" );

	        Group root = new Group();
	        Scene theScene = new Scene( root );
	        theStage.setScene( theScene );

	        Canvas canvas = new Canvas( 1024, 1024 );
	        root.getChildren().add( canvas );

	        GraphicsContext gc = canvas.getGraphicsContext2D();

	        Image one = new Image( "1.jpg" );
	        Image car = new Image( "car.png" );
	        
	        ArrayList<String> input = new ArrayList<String>();

	        theScene.setOnKeyPressed(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString(); 
	                    if ( !input.contains(code) )
	                        input.add( code );
	                }
	            });

	        theScene.setOnKeyReleased(
	            new EventHandler<KeyEvent>()
	            {
	                public void handle(KeyEvent e)
	                {
	                    String code = e.getCode().toString();
	                    input.remove( code );
	                }
	            });
	        
	        new AnimationTimer()
	        {
	        	long x = 512;
	        	double t = 0;
	        	double s = 10;
	            public void handle(long currentNanoTime)
	            {
	            	if (t>1024) {
	            		t=0;
	            	}else {
	            		t=Math.abs(t+s);
	            	}
	            	gc.drawImage(one, 0, t );
	            	gc.drawImage(one, 0, t-1024 );
	            	if (input.contains("LEFT")) {
	            		if (x-10<0) {
	            			x=0;
	            		}else {
	            			x=x-10;
	            		}
	                }
	            	
	                if (input.contains("RIGHT")) {
	                	if (x+10>896) {
	            			x=896;
	            		}else {
	            			x=x+10;
	            		}
	                }

	            	if (input.contains("DOWN")) {
	            		if (s-0.3<0) {
	            			s=0;
	            		}else {
	            			s=s-0.3;
	            		}
	                }
	            	
	                if (input.contains("UP")) {
	                	s=s+0.3;
	                }
	            	gc.drawImage( car, x, 512 );
	                
	            }

	        }.start();
	        theStage.show();
	    }
}
