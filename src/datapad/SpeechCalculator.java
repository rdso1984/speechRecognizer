package datapad;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;

public class SpeechCalculator {
    
    //Variables to start the programm
    Logger logger = Logger.getLogger(getClass().getName()); //logger to show massage
    String result; //result to make a action
    //MakeDecision newCalc = new MakeDecision(); //Colocar o objeto no início
    double lastResult = 0;

    Thread resourcesThread; //check if the resources -microphone- is ready
    Thread speechThread; //load and run speech recognizer

    LiveSpeechRecognizer recognizerCalculator; //create
    
    //Cronstructor
    public SpeechCalculator(){
        
        startSpeechRecognize();
     
    }
    
    //method to configure and recognize speech
    private void startSpeechRecognize() {
            
        logger.log(Level.INFO, "Loading Calculator...\n");

        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us"); //show the path to Acoustic
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict"); //show the path to Dictionary
        configuration.setGrammarPath("resource:/grammars"); //show path to grammar
        configuration.setGrammarName("grammar"); //show the name of the grammar to use
        configuration.setUseGrammar(true); //indica que quer utilizar a grammar

        try {
            recognizerCalculator = new LiveSpeechRecognizer(configuration);
        }
        catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        recognizerCalculator.startRecognition(true);

        startResourcesThread(); //Check if microphone is ready
        startSpeechThread(); //Run speech recognize
    }
    
    //method to check if microphone is ready
    private void startResourcesThread() { //check if resources is ready

        if(resourcesThread != null && resourcesThread.isAlive()){
            return;
        }

        resourcesThread = new Thread(() -> {
            try { //Check if microphone is available
                if(AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
                    logger.log(Level.INFO, "Microphone is available. \n");
                }
                else{
                    logger.log(Level.INFO, "Microphone is not available. \n");
                }
                Thread.sleep(350);
            }
            catch (InterruptedException ex) {
                logger.log(Level.WARNING, null, ex);
                resourcesThread.interrupt();
            }
        });
        resourcesThread.start(); //start      
    }

    //method to run speech recognize
    private void startSpeechThread(){
        
        if(speechThread != null && speechThread.isAlive()) {
            return;
        }

        speechThread = new Thread(() -> {
            logger.log(Level.INFO, "You can start to speak... \n");
            //String lastResult=null;
            
            try {
                while(true){
                    SpeechResult speechResult = recognizerCalculator.getResult();
                    
                    
                    if(speechResult != null) {
                        result = speechResult.getHypothesis();
                        System.out.println("You said: "+ result + "\n");
                        
                        /*newCalc.stringToNumber(result); //Define number1, number2, which operation will be calculte
                        System.out.println("O valor do OPTION é: " + newCalc.option);
                        System.out.println("Result =  " + newCalc.lastResult(newCalc.getOption(), newCalc.getNumber1(), newCalc.getNumber2()));*/
                        
                    }
                    else {
                        logger.log(Level.INFO, "I can't understand what you choose \n");
                    }
                }
            }
            catch (Exception ex) {
                logger.log(Level.WARNING, null, ex);
            }
        });
        speechThread.start();
    }
}