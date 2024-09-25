package composants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Line2D;

import nicellipse.component.NiEllipse;
import nicellipse.component.NiLabel;
import nicellipse.component.NiPolyLine;
import nicellipse.component.NiRectangle;
import nicellipse.component.NiShape;
import nicellipse.component.NiSpace;

public class Main {

	public Main() {
		NiSpace space = new NiSpace("Zoo", new Dimension(500, 500));

		NiRectangle container = new NiRectangle();
		container.setBackground(Color.white);
		container.setLocation(new Point(20, 20));
		container.setSize(new Dimension(400, 380));
		space.add(container);

		NiEllipse subContainer = new NiEllipse();
		subContainer.setBackground(Color.red);
		subContainer.setSize(300, 300);
		container.add(subContainer);
		subContainer.setCenter(container.getCenter());

		NiEllipse cercle = new NiEllipse();
		cercle.setSize(60,60);
		cercle.setBackground(Color.yellow);
		subContainer.add(cercle);
		cercle.setCenter(subContainer.getCenter());

		NiLabel s = new NiLabel();
		s.setText("Click inside this circle");
		s.setFontSize(18);
		s.setForeground(Color.white);
		s.setLocation(20, 80);
		subContainer.add(s);

		Line2D line = new Line2D.Double(20,30,350,30);
		NiShape shape7 = new NiShape(line);
		shape7.setStrokeWidth(10);
		container.add(shape7);
		
		NiPolyLine pline = new NiPolyLine();
		pline.setStrokeWidth(8);
		pline.addPoint(new Point(5, 140));
		pline.addPoint(new Point(50, 180));
		pline.addPoint(new Point(70, 130));
		pline.addPoint(new Point(95, 180));
		pline.addPoint(new Point(150, 130));
		pline.addPoint(new Point(170, 180));
		container.add(pline);

		space.openInWindow();
	}
}
