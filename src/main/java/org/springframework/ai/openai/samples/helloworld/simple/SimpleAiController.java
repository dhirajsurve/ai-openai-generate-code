package org.springframework.ai.openai.samples.helloworld.simple;

import org.json.JSONException;
import org.springframework.ai.client.AiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

@RestController
public class SimpleAiController {

	private final AiClient aiClient;


	@Autowired
	public SimpleAiController(AiClient aiClient) {
		this.aiClient = aiClient;
	}

	@GetMapping("/ai/simple")
	public String completion(@RequestParam(value = "message", defaultValue = "Hellworl java program") String message) throws JSONException {


		String responseString= (new Completion(aiClient.generate(message))).getCompletion();

		codeExtractor(responseString);
		return responseString;

	}

	private void codeExtractor(String responseString) {// Parse JSON response
		// Extract code snippet
		int startIndex = responseString.indexOf("```java") + 7; // Start index after "```java" marker
		int endIndex = responseString.lastIndexOf("```");

		String codeSnippet = responseString.substring(startIndex, endIndex);

		// Write code snippet to Java file
		try {
			FileWriter writer = new FileWriter("src/main/java/org/springframework/ai/openai/samples/helloworld/generatedcode/"+extractClassName(codeSnippet) +".java");
			writer.write(codeSnippet);
			writer.close();
			System.out.println("Java file created successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}
	}

	private static String extractClassName(String codeSnippet) {
		// Extract class name from code snippet
		String defaultClassName = "DynamicClassName";
		String[] lines = codeSnippet.split("\n");
		for (String line : lines) {
			if (line.trim().startsWith("public class")) {
				String[] parts = line.trim().split("\\s+");
				if (parts.length >= 3) {
					return parts[2].trim().replaceAll("\\{", ""); // Remove '{' if present
				}
			}
		}
		// If class name is not found or in an unexpected format, return a default name
		return defaultClassName;
	}

}
