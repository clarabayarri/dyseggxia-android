package dyseggxia.domainControllers;

import java.util.List;

import dyseggxia.domainModel.Level;
import dyseggxia.providers.LevelProviderI;

public class LevelController {

	private LevelProviderI provider;
	
	public LevelController(LevelProviderI provider) {
		this.provider = provider;
	}

	public Level getLevel(int levelNumber, String language) {
		return provider.findLevel(levelNumber, language);
	}
	
	public List<Level> getAllLevels() {
		return provider.findAllLevelBasicData();
	}

}
