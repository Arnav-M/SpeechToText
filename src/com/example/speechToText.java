package com.example;

// imports the files used by the converter() for conversion.
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

/* Converts Speech to Text using sphinx4 API and runs a find and replace module as per the user's
 * requirements. */
	public class speechToText {      
		private String[] arr;
		private Scanner sc;
		private String index;
		
		// Constructs the actual speechToText converter.
		public speechToText() {
			arr = null;
			sc = new Scanner(System.in);
			index = "";
		}
		
		/* Searches for a given keyword in the hypothesis and returns the number of occurences
		 * of the word.
		 */
	    public int searchEngine(String key) {
	    	int occurences = 0;
	    	for(int i = 0; i < arr.length; i++) {
	    		if(arr[i].equalsIgnoreCase(key)) {
	    			occurences++;
	    			index+= i;
	    		}
	    	}
	    	return occurences;
	    }
	    
	    /* Converts the audio file to text using .jar files.
	     * Throws any and all Exceptions that may arise. */
	    public void converter() throws Exception {
	    	String words = "";
	    	Configuration configuration = new Configuration();	        

	        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
	        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
	        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

	        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
	        InputStream stream = new FileInputStream(new File("test.wav"));

	        recognizer.startRecognition(stream);
	        SpeechResult result;
	        while ((result = recognizer.getResult()) != null) {
	        	words += result.getHypothesis();
	        	arr = words.split(" ");
	        	System.out.format("Hypothesis: %s\n", result.getHypothesis());
	        }
	        recognizer.stopRecognition();
	    }
	    
	    /* Finds any given keyword in the text that the user wants and outputs the occurences.
	     *  Also uses the replace() method to replace any keyword. Displays the text document
	     *  after each change in an array format. */
	    public void find() {
	    	boolean play = true;
    		String key = "";
    		String response2 = "";
    		int occurences;
    		
    		System.out.println("The text document looks like this: " + Arrays.toString(arr));
    		System.out.println("Would you like to find a keyword in the text ? Y/N");
    		String response = sc.next();
    		if(response.equalsIgnoreCase("Y")) {
    			while(play) {
    				System.out.println("Enter keyword to be searched: ");
    				key = sc.next();
    				occurences = searchEngine(key);
    				System.out.println("The word *" + key + "* is used a total of " + occurences + " times in the speech");
    				System.out.println("The finished document looks like this: " + replace(occurences, key, index, 0));
    				System.out.println("Do you want to find another keyword? Y/N");
    				response2 = sc.next();
    				if(!response2.equalsIgnoreCase("Y")) {
    					play = false;
    				}
    			}	
	    	}
	    }
	    
	    /* Replaces each given instance of the keyword specified by the user by another word
	     * also specified by the user and returns a string. It replaces words till all occurences
	     * of the word are cycled through.*/
	    public String replace(int occurences, String key, String index, int i) {	    	
	    	String response = "";
	    	String word = "";
	    	if(occurences != 0) {
	    		System.out.println("The document looks like this" + Arrays.toString(arr));
	    		System.out.println("Do you want to replace this word: " + key + " Y/N ?");
	 	    	response = sc.next();
	    	}
	    	if(occurences == 0) {
	   			return Arrays.toString(arr);
	   		}
	    	else if(response.equalsIgnoreCase("Y")) {
	    		System.out.println("Enter word to replace the other word: ");
	    		word = sc.next();
    			char a = index.charAt(i);
    			arr[Character.getNumericValue(a)] = word;
	    		return replace(occurences - 1, key, index, i + 1);
	    	}
	   		else {
	    		return replace(occurences - 1, key, index, i + 1);
	    	}
	    }
	}
