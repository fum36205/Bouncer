package de.ur.mi.bouncer.world.builders.xml;

import de.ur.mi.bouncer.world.FieldColor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.ur.mi.bouncer.world.TwoDimensionalWorld;

public class XmlWorldBuilder {

	public static TwoDimensionalWorld fromXmlDocument(Document doc) {
		TwoDimensionalWorld world = TwoDimensionalWorld.emptyWorld();
		setupWorld(world, doc);
		return world;
	}

	private static void setupWorld(TwoDimensionalWorld world, Document doc) {
		setupObstacles(world, doc);
		setupColors(world, doc);
		setupBouncer(world, doc);
	}

	private static void setupBouncer(TwoDimensionalWorld world, Document doc) {
		Element bouncer = doc.select("bouncer").first();
		if (bouncer == null) {
			return;
		}
		int x = Integer.valueOf(bouncer.attr("x"));
		int y = Integer.valueOf(bouncer.attr("y"));
		world.placeBouncerAt(x, y);
	}

	private static void setupColors(TwoDimensionalWorld world, Document doc) {
		Elements colors = doc.select("color");
		for (int i = 0; i < colors.size(); i++) {
			Element color = colors.get(i);
			int x = Integer.valueOf(color.attr("x"));
			int y = Integer.valueOf(color.attr("y"));
			String value = color.attr("value");

			if ("RED".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.RED);
			} else if ("GREEN".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.GREEN);
			} else if ("BLUE".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.BLUE);
			}
		}
	}

	private static void setupObstacles(TwoDimensionalWorld world, Document doc) {
		Elements obstacles = doc.select("obstacle");
		for (int i = 0; i < obstacles.size(); i++) {
			Element obstacle = obstacles.get(i);
			int x = Integer.valueOf(obstacle.attr("x"));
			int y = Integer.valueOf(obstacle.attr("y"));
			world.placeObstacleAt(x, y);
		}
	}
}
