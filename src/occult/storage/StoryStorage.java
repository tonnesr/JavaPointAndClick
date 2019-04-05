/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult.storage;

/**
 *
 * @author Tønnes-Skole-Pc2
 */
public class StoryStorage {
    public String[] currentScene;
    public String[] currentArray;
    
    public String currentWord;
    
    public StoryStorage() {       
    }
    
    public String[] scene0Storage() {                               
        currentScene = new String[] {"Nice to meet you. I'm","And your name was?","","",""};        
        return currentScene;
    }
    
    public String[] scene1Storage() {
        currentScene = new String[] {"",""};
        return currentScene;
    }
    
    public String[] getArray(int i) {
        switch(i) {
            case 0:
                currentArray = scene0Storage();
                break;
            case 1:
                currentArray = scene1Storage();
                break;
            default:
                System.out.println("Error: Array = NULL <-- StoryStorage.getArray();");
                return null;
        }
        return currentArray;
    }
}
