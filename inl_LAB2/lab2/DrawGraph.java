import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
DrawGraph har används till många uppgifter och skrivs om lite varje gång
utan att jag tar tag i helheten, det är en samling fixar med andra ord....
*/
/**
* A class for drawing a graph.
* ( it's really only drawing text and lines and have no idea what it is drawing)
* Original map drawing is thanks to Tommi Kerola and Marcus Johansson IT students 2010
* But not much is left of their code
* @author rewritten 2011 by Jesper Lloyd IT
* @author rewritten by Erland Holmström 2011-2015
* @author rewritten by Fredrik Lindblad 2016
*/
public class DrawGraph extends JFrame {
	
	private GraphLayer base;
	private GraphLayer overlay1;
	private GraphLayer overlay2;
	private int windowWidth = 500;
	private int windowHeight = 500; 
	private int scalex = 1;
	private int scaley = 1;
	private int pointNbr = 0;
		
	public enum Layer{BASE, OVERLAY1, OVERLAY2};
	
	public DrawGraph() {
		this(500, 500); 
	}
	
	public DrawGraph(int width, int height) {
		if ( Math.abs(windowWidth - width) < 100 ) {
			windowWidth = width;
		} else {
			scalex = windowWidth/width;
		}
		if ( Math.abs(windowHeight - height) < 100 ) { 
			windowHeight = height;
		} else {
			scaley = windowHeight/height;
		}
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 500); // flyttas	
		Dimension windSize = new Dimension(windowWidth, windowHeight);
		setPreferredSize(windSize);
		
		base = new GraphLayer();
		base.setSize(windSize);
		base.setLayout(null);
		
		overlay1 = new GraphLayer();
		overlay1.setOpaque(false);
		overlay1.setSize(windSize);
		overlay1.setLayout(null);

		overlay2 = new GraphLayer();
		overlay2.setOpaque(false);
		overlay2.setSize(windSize);
		overlay2.setLayout(null);
		
		getContentPane().add(base);
		base.add(overlay1);
		base.add(overlay2);
		pack();
		setVisible(true);
	}
	
	public void drawString(String text, int x, int y, Layer l) {
		x = scaleX(x)+10;
		y = scaleY(y)-10;
		JLabel newLabel = new JLabel(text);
		newLabel.setSize(150, 18);
		newLabel.setLocation(x, y);
		newLabel.setFont(newLabel.getFont().deriveFont(8));//.deriveFont(Font.BOLD));
		switch (l) {
		 case BASE:
  			base.add(newLabel);
  			break;
  	case OVERLAY1:
  			overlay1.add(newLabel);
  			break;
  	case OVERLAY2:
  			overlay2.add(newLabel);
  			break;
		}
	}
	
	public void drawLine(int x1, int y1, int x2, int y2, Color color, double width, Layer l) {
		int x11 = scaleX(x1);
		int y11 = scaleY(y1);
		int x22 = scaleX(x2);
		int y22 = scaleY(y2);
		int shift = l == Layer.OVERLAY2 ? 1 : 0;
		CWLine2D line = new CWLine2D(x11 + shift, y11 + shift, x22 + shift, y22 + shift, width, color);
		if(l == Layer.BASE) {
			//base.shapes.add(0,line); //Make sure lines are not drawn over stops
			base.shapes.add(line);
			base.shapes.add(new CWEllipse2D(x11-1, y11-2, 6, 6, width, color));
			base.shapes.add(new CWEllipse2D(x22-1, y22-2, 6, 6, width, color));
			drawString(""+x1+","+y1, x1, y1, l); 
			drawString(""+x2+","+y2, x2, y2, l);
		} else {
		 switch (l) {
		  case OVERLAY1:
  			overlay1.shapes.add(line);
  		 break;
  		case OVERLAY2:
  		 overlay2.shapes.add(line);
  		 break;
  	}
			//overlay.shapes.add(new CWEllipse2D(x11-1, y11-2, 3, 3, width, color));
		}	
	}
	
	public void clearLayer(Layer l){
		switch (l) {
		 case BASE:
  			base.shapes.clear();
  			break;
  	case OVERLAY1:
  			overlay1.shapes.clear();
  			break;
  	case OVERLAY2:
  			overlay2.shapes.clear();
  			break;
		}
	}
	
	private int scaleX(int x) {
		return (x*scalex);
	}
	
	private int scaleY(int y) {
		return (y*scaley);
	}
	
	//Fixing repainting of paths and nodes.
	private class GraphLayer extends JPanel{

		private List<SpecShape> shapes =  new LinkedList<SpecShape>();
		
		@Override
		protected void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D)g;
			for(SpecShape s: this.shapes) {
				g2.setColor(s.getColor());
				g2.setStroke(new BasicStroke((float)s.getWidth()));
				g2.draw(s);
			}
		}
	}
	
	//Line shape knowing color and width (stupid solution)
	private class CWLine2D extends Line2D.Float implements SpecShape{
		
		private Color color;
		private double lwidth;
		
		CWLine2D(float x1, float y1, float x2, float y2, double width, Color c){
			super(x1, y1, x2, y2);
			color = c;
			this.lwidth = width;
		}

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public double getWidth() {
			return lwidth;
		}
		
	}

	//Ellipse knowing color and width (stupid solution)
	private class CWEllipse2D extends Ellipse2D.Float implements SpecShape{
		
		private Color color;
		private double lwidth;
		
		CWEllipse2D(float x, float y, float width, float height, double lwidth, Color c){
			super(x, y, width, height);
			color = c;
			this.lwidth = lwidth;
		}

		@Override
		public Color getColor() {
			return color;
		}

		@Override
		public double getWidth() {
			return lwidth;
		}
		
	}
	
	//Shape width color and line width;
	private interface SpecShape extends Shape{
		
		public Color getColor();
		public double getWidth();
	}
	
}
