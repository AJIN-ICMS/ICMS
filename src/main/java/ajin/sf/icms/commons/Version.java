package ajin.sf.icms.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Version {

	private static final Logger logger = LoggerFactory.getLogger(Version.class);
	
	private Properties prop = null;
	
	static Version _inst = null;
	
	public static synchronized Version getInst() {
		if(_inst == null) {
			_inst = new Version();
		}
		
		return _inst;
	}
	
	private Version() {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
		this.prop = new Properties();
		
		try {
			this.prop.load(resourceAsStream);
		} catch (IOException e) {
			logger.info("error");
		}
	}
	
	public String getVersion() {
		return prop.getProperty("pom.version");
	}

	public String getName() {
		return prop.getProperty("pom.name");
	}

	public String getGroupId() {
		return prop.getProperty("pom.groupId");
	}

	public String getArtifactId() {
		return prop.getProperty("pom.artifactId");
	}

	public String getModelVersion() {
		return prop.getProperty("model.version");
	}
}