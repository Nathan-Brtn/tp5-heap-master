package svg;

public class AppSVG {

	public static void main(String[] args) {
		
		//Exercice 12
		/*
		Rectangle   rect0 = new Rectangle(0, 0, 1000, 1000);
		Style       styl0 = new Style();
		styl0.setFill("lightblue");
		rect0.setStyle(styl0);
		
		Rectangle   rect2 = new Rectangle(100, 100, 200, 200);
		Style       styl2 = new Style();
		styl2.setFill("#080");
		rect2.setStyle(styl2);
		
		Rectangle   rect3 = new Rectangle(200, 200, 400, 400);
		Style       styl3 = new Style();
		styl3.setFill("#0C0");
		rect3.setStyle(styl3);
		
		Rectangle   rect5 = new Rectangle(100, 100, 200, 200);
		Style       styl5 = new Style();
		styl5.setFill("#800");
		rect5.setStyle(styl5);
		
		Rectangle   rect6 = new Rectangle(200, 200, 400, 400);
		Style       styl6 = new Style();
		styl6.setFill("#C00");
		rect6.setStyle(styl6);
		
		Group       grou4 = new Group();
		Style       styl4 = new Style();
		styl4.addTransform(new Translate(0, 700));
		styl4.addTransform(new Rotate(-45, 100, 50));
		grou4.setStyle(styl4);
		grou4.add(rect5);
		grou4.add(rect6);
		
		Group       grou1 = new Group();
		Style       styl1 = new Style();
		styl1.setStroke("black");
		styl1.setStrokeWidth(20.0);
		styl1.setFillOpacity(0.8);
		grou1.setStyle(styl1);
		grou1.add(rect2);
		grou1.add(rect3);
		grou1.add(grou4);
		
		SVG svg = new SVG(1000,1000);
		svg.add(rect0);
		svg.add(grou1);
		svg.saveAsFile("question_12.svg");
		 */
		
		//Exercice 13
		/*
		Circle  circleR = new Circle(500, 650, 300);
		Style   styleR  = new Style();
		circleR.setStyle(styleR);
		styleR.setFill("red");
		styleR.setFillOpacity(0.5);
		styleR.setStrokeWidth(10.0);
		styleR.setStroke("black");
		
		Circle  circleG = new Circle(350, 400, 300);
		Style   styleG  = new Style();
		circleG.setStyle(styleG);
		styleG.setFill("green");
		styleG.setFillOpacity(0.5);
		styleG.setStrokeWidth(10.0);
		styleG.setStroke("black");
		
		Circle  circleB = new Circle(630, 400, 300);
		Style   styleB  = new Style();
		circleB.setStyle(styleB);
		styleB.setFill("blue");
		styleB.setFillOpacity(0.5);
		styleB.setStrokeWidth(10.0);
		styleB.setStroke("black");

		Line l1 = new Line(200,200,300,300);
		Style styleL = new Style();
		styleL.setStroke("black");
		l1.setStyle(styleL);
		
		SVG svg = new SVG(1000,1000);
		svg.add(circleR);
		svg.add(circleG);
		svg.add(circleB);
		svg.add(l1);

		Circle c = new Circle(50,50,10);
		svg.add(c);

		Text txt = new Text("bonjou",100,200, 55);
		svg.add(txt);
		svg.saveAsFile("question_13.svg");


		Tree t2 = new Tree();
		t2.setVal(2);
		t2.setFilsD(new Tree());
		t2.setFilsG(new Tree());

		Tree t3 = new Tree();
		t3.setVal(3);
		t3.setFilsD(t2);
		t3.setFilsG(new Tree());

		Tree fullt = Tree.buildFullBinary(5);
		SVG svgtree = fullt.toSVG();
		svgtree.saveAsFile("tree.svg");*/
	}
}
