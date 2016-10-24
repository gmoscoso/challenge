package com.test.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.test.interfaces.RouteNetworkProvider;
import com.test.services.FileStreamer;

public class FileStreamerShould {

	@Rule
	public TemporaryFolder testFolder = new TemporaryFolder();
	
	private File busRoutesFile;
	private RouteNetworkProvider routesProvider;
	 
	@Before
	public void createTempFile() throws IOException{
		busRoutesFile = testFolder.newFile("busRoutes.txt");
		routesProvider = new FileStreamer();
	}
	
	@Test
	public void acceptAValidFileName() {
		String validFileName = busRoutesFile.getAbsolutePath();

		routesProvider.stream(validFileName);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void thowAnExceptionIfFileDoesNotExist() {
		String fileThatDoesNotExist = busRoutesFile.getAbsolutePath() + "/doesNotExist";

		routesProvider.stream(fileThatDoesNotExist);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void thowAnExceptionIfGivenAFolder() throws IOException {
		String folder = testFolder.newFolder("folder").getAbsolutePath();
		
		routesProvider.stream(folder);
	}
	
	@Test
	public void streamThroughFileContents(){
		String GOEURO_BUS_FILE = "./src/test/resources/busRoutes.txt";
		
		Stream<String> stream = routesProvider.stream(GOEURO_BUS_FILE);
		
		List<String> lines = stream.collect(Collectors.toList());
		
		assertThat(lines)
			.containsSequence(
				"3",
				"0 0 1 2 3 4",
				"1 3 1 6 5",
				"2 0 6 4");
	}
		
}
