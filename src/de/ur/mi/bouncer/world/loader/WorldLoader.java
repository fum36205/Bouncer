package de.ur.mi.bouncer.world.loader;
import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.ur.mi.bouncer.world.TwoDimensionalWorld;
import de.ur.mi.bouncer.world.builders.xml.XmlWorldBuilder;

public class WorldLoader {
	private static final String ASSET_FOLDER = "../assets/";
	private static final String WEB_URL = "http://132.199.139.24/~baa56852/data/bouncer/maps/";
	
	public TwoDimensionalWorld loadLocalMap(String mapName) {
		try {
			File mapFile = new File(ASSET_FOLDER + mapName + ".xml");
			Document doc = Jsoup.parse(mapFile, "UTF-8");
			return XmlWorldBuilder.fromXmlDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public TwoDimensionalWorld loadOnlineMap(String mapName) {
		try {
			Document doc = Jsoup.connect(WEB_URL + mapName + ".xml").get();
			return XmlWorldBuilder.fromXmlDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
