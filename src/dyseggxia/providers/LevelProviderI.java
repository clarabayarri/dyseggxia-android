package dyseggxia.providers;

import java.util.List;

import dyseggxia.domainModel.Level;

public interface LevelProviderI {

	public List<Level> findAllLevelBasicData();
	public Level findLevel(int levelNumber);
}
