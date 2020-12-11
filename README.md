# SpeechToText
a SpeechToText converter for our final project created by Arnav Mathur and Saul Andres Romero
1) Go to the github repository and download all the files.
2) Go to folder src and open main.java and compile and run in any compiler 
3) The main method automatically uses the sphinx4 API to do the textToSpeech conversion we just need to take note of a few things:
	1. The audio file must be named test.wav and be 12000 kilohertz in frequency.
	2. You will need to replace the text.wav already present with your own for tests.
	3. The textToSpeech conversion is not always perfect especially if the audio file is unclear. This is a common problem in text to speech conversion unless you have servers which feed the API a larger directory of words to make it more precise. The directory of words we are using is the common one provided by sphinx4 so if the conversion seems like 	              gibberish there is very little that can be done to change it.
	4. After the conversion the compiler will ask if a find and replace is to be instantiated, make sure to only input correct inputs otherwise the program may not work.
	5. The program is not case sensitive so "y" will work equally as well as "Y" if you are answering the scanner.
	6. The program asks the user to find a key word and also returns the occurences of the keyword in the text. If the keyword exists the user can replace all of its occurences one by one or if you choose not to replace one instance then it will simply move on to the next.
	7. WARNING if you decide to use a huge .wav file the textToSpeech conversion will take up a lot of time so try to use smaller .wav files or if you like watching the compiler run use a big file :)
	8. The output for the conversion is shown as "hypothesis: " but it is also shown all together using an array. The output also shows the information behind the conversion of the speech but that can be disregarded.
