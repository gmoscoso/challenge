package com.test.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.test.interfaces.RouteNetworkProvider;

public class FileStreamer implements RouteNetworkProvider {

	@Override
	public Stream<String> stream(String nameOfFile) {
		this.isValid(nameOfFile);
		
		Path path = Paths.get(nameOfFile);
		try {
			return Files.lines(path);
		} catch (IOException e) {
			throw new IllegalStateException("An error occurred when reading the file " + nameOfFile);
		}
	}

	private void isValid(String nameOfFile) {
		File f = new File(nameOfFile);
		
		if(!f.exists() || f.isDirectory()) { 
			throw new IllegalArgumentException("Cannot find file: " + nameOfFile);   
		}
	}

}
