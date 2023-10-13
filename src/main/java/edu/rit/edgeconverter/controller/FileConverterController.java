package edu.rit.edgeconverter.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileConverterController {
    private static final Logger logger = LogManager.getLogger(FileConverterController.class);

    public FileConverterController() {
        logger.info("FileConverterController instantiated");
        // Calling methods within the constructor
        convertFile("exampleInputFile.txt", "exampleOutputFile.txt");
    }

    private void convertFile(String inputFilePath, String outputFilePath) {
        logger.info("Converting file: {} to: {}", inputFilePath, outputFilePath);
        try {
            // TODO: Implement file conversion logic
            logger.debug("File conversion completed successfully");
        } catch (Exception e) {
            logger.error("Error converting file: {} to: {}", inputFilePath, outputFilePath, e);
        }
    }

    // Example usage
    
}