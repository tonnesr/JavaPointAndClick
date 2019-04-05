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
public class CharacterStorage { 
    public String[][] characters;
    public String[][] testArray;
    
    public CharacterStorage() {
        characters = new String[][] {{"Sherlock","Holmes","42","Detective","England","Male","SherlockPortrait"},
                                      {"John","Watson","45","Physician","England","Male","DrWatsonPortrait"},
                                      {"Hercules","Poirot","45","Detective","Belgium","Male","HerculesPoirotPortrait"},
                                      {"Amelia","Earhart","36","Pilot","USA","Female","AmiliaEarhartPortrait"},
                                      {"Nancy","Drew","21","Student","USA","Female","NancyDrewPortrait"},                                     
                                      {"Houtarou","Oreki","16","Student","Japan","Male","HoutarouOrekiPortrait"},
                                      {"Howard","Lovecraft","40","Author","USA","Male","HPLovecraftPortrait"},
                                      {"Roald","Amundsen","50","Explorer","Norway","Male","RoaldAmundsenPortrait"},
                                      {"Charles","Dickens","52","Author","England","Male","CharlesDickensPortrait"}, 
                                      {"Victoria","Wettin","75","Monarch","England","Female","QueenVictoriaIIPortrait"},
                                      {"Vincent","van Gogh","35","Painter","Netherlands","Male","VincentvanGoghPortrait"},
                                      {"Snorri","Sturluson","30","Viking","Iceland","Male","SnorriSturlusonPortrait"},
                                      {"Ragnar","Lodbrok","33","Viking","Denmark","Male","RagnarLodbrokPortrait"},
                                      {"Yury","Gagarin","35","Austronaut","Russia","Male","YuryGagarinPortrait"},
                                      {"Jackie","Chan","46","Actor","China","Male","JackieChanPortrait"},
                                      {"Charles","Darwin","60","Scientist","England","Male","CharlesDarwinPortrait"},
                                      {"Duchess","Unknown","55","Duchess","Unknown","Female","DuchessPortrait"},
                                      //Icons needed:
                                      //{"Mary","Shelly","45","Author","England","Female","MaryShellyPortrait"}
                                      //{"Napoleon","Boneparde","Monarch","France","Male","NapoleonBonepardePortrait"}                                      
                                      };
    }
    public String[][] getArray() {        
        return characters;
    }
}
