package app.gameScreen.questionPanel.text;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Polygon;

import math.funktionen.Point;

@SuppressWarnings("javadoc")
public class PointyBackGround extends Polygon
{

	private DoubleProperty width = new SimpleDoubleProperty(0);
	public DoubleProperty widthProperty() { return this.width; }
	public double getWidth() { return this.width.get(); }
	public void setWidth(double value) { this.width.set(value); }

	private DoubleProperty height = new SimpleDoubleProperty(0);
	public DoubleProperty heightProperty() { return this.height; }
	public double getHeight() { return this.height.get(); }
	public void setHeight(double value) { this.height.set(value); }

	private Point PointSpitze;
	private Point PointEcke;
	private Double[] PointsArray;

	public PointyBackGround() {
		super();

		this.width.addListener(
			(ov, newVal, oldVal) -> {
				this.setPolygonCorners();
		});

		this.height.addListener(
			(ov, newVal, oldVal) -> {
				this.setPolygonCorners();
		});
	}

	private void setPolygonCorners() {
		this.buildPoints();
		this.buildArray();
		this.getPoints().setAll(this.PointsArray);
	}

	private void buildArray() {
		this.PointsArray = new Double[] {
				this.PointSpitze.getX(), this.PointSpitze.getY(),
				this.PointEcke.getX(), this.PointEcke.getY(),
				-this.PointEcke.getX(), this.PointEcke.getY(),

				-this.PointSpitze.getX(), -this.PointSpitze.getY(),
				-this.PointEcke.getX(), -this.PointEcke.getY(),
				this.PointEcke.getX(), -this.PointEcke.getY()
		};
	}

	private void buildPoints() {
		int border = (int) this.getStrokeWidth() * 2;
		int  width = (int) this.width.get() -border -1;
		int height = (int) this.height.get() -border;

		this.PointSpitze = new Point(width/2, 0d);
		this.PointEcke = new Point(width/2 * 0.9, height /2);
	}

}
