package app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@SuppressWarnings("javadoc") // TODO
public class ParentFactory {

	public final static String LOGIN = "../app/login/login.fxml";
	public final static String GAME = "../app/gameScreen/GameScene.fxml";

	private final Map<String, Parent> map = new HashMap<>();

	public ParentFactory() {
		super();
	}

	public void removeParent(String name) {
		if(this.map.containsKey(name))
			this.map.remove(name);
	}

	public Parent getGameParent(String path) {
		return this.getGameParent(path, null);
	}

	public Parent getGameParent(String path, Object Controller) {

		if(!this.map.containsKey(path))
			this.map.put(path, this.createParent(path, Controller));

		return this.map.get(path);
	}

	private Parent createParent(String path, Object Controller) {
		FXMLLoader fl =
			new FXMLLoader(
				this.getClass().getResource(path));

		if(Controller != null)
			fl.setController(Controller);

		Parent p = null;

		try {
			p = fl.load();
		} catch (IOException e) {	e.printStackTrace(); }

		return p;
	}
}
