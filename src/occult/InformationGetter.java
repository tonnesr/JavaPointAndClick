/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult;

/**
 *
 * @author Tønnes-Skole-Pc2
 */
public class InformationGetter {    
    occult.storage.CharacterStorage charaStore;
    occult.storage.StoryStorage storyStore;
    occult.storage.ItemStorage itemStore;
    
    public String firstName;
    public String lastName;
    public String age;
    public String occupation;
    public String country;
    public String gender;
    public String portrait; 
    public String countryImg;
    public String currentSentence;
    public String currentIntro;
    public String currentItem;
    public String currentItemImage;
    public String fullName;
    
    public int ageInt;      
        
    public String[][] charaterArray;
    public String[] storyArray;
    public String[][] itemArray;
    
    StringBuilder stringBuilder;
    
    public InformationGetter() {
        charaStore = new occult.storage.CharacterStorage();
        storyStore = new occult.storage.StoryStorage();
        itemStore = new occult.storage.ItemStorage();
        stringBuilder = new StringBuilder();     
    }
    
    //Story parts
    public String[] getStoryArray(int storyPart) {
        storyArray = storyStore.getArray(storyPart);
        return storyArray;
    }

        public String getSentence(int i) {
            currentSentence = storyArray[i]; 
            return currentSentence;
        }
    
    
    //Character parts    
    public String[][] getCharacterArray() {
        charaterArray = charaStore.getArray();
        return charaterArray;
    }
    
        public String getFirstName(int i) {
            firstName = charaterArray[i][0];
            return firstName;
        }

        public String getLastName(int i) {
            lastName = charaterArray[i][1];
            return lastName;
        }
        
        public String getFullName(int i) {
            fullName = getFirstName(i) + " " + getLastName(i);
            return fullName;
        }

        public String getAge(int i) {
            age = charaterArray[i][2];
            return age;
        }

        public int getAgeInt(int i) {
            ageInt = Integer.parseInt(charaterArray[i][2]);       
            return ageInt;
        }

        public String getOccupation(int i) {
            occupation = charaterArray[i][3].replace(",", ", ");
            return occupation;
        }

        public String getCountry(int i) {
            country = charaterArray[i][4];
            return country;
        }

        public String getGender(int i) {
            gender = charaterArray[i][5];
            return gender;
        }

        public String getPortraitImgAdress(int i) {
            portrait = "Resources\\Portraits\\" + charaterArray[i][6] + "_128x128_Pixelated.png";
            return portrait;
        }

        public String getCountryImgAdress(int i) {
            countryImg = "Resources\\Flags\\" + charaterArray[i][4] + "_128x73.png";
            return countryImg;
        }   
    

    //Item parts
    public String[][] getItemArray() {
        itemArray = itemStore.getArray();
        return itemArray;
    }
        public String getItem(int i) {
            currentItem = itemArray[i][0];
            return currentItem;
        }
        
        public String getItemImage(int i) {
            currentItemImage = "Resources\\Items\\" + itemArray[i][1] + "64x64.png";
            return currentItemImage;
        }
}
