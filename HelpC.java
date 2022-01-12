/*

#####          #####  ###############  #####            ###############           ###############
#####          #####  ###############  #####            ###############          ###############
#####          #####  #####            #####            #####     #####       ##########
#####          #####  #####            #####            #####     #####       ##########
#####          #####  #####            #####            #####     #####    ##########
#####          #####  #####            #####            #####     #####    ##########
####################  ###############  #####            ###############  ##########
####################  ###############  #####            ###############  ##########
#####          #####  #####            #####            #####              ##########
#####          #####  #####            #####            #####              ##########
#####          #####  #####            #####            #####                 ##########
#####          #####  #####            #####            #####                 ##########
#####          #####  ###############  ###############  #####                    ###############
#####          #####  ###############  ###############  #####                     ###############
 
 */


/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
|* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *| 
|* *  The class HelpC contains some methods that are useful in many different situations.
|* *  This class contains the following methods: 
|* * 
|* *  - toStringArray()         | Description: converts a string to a string-array; returns a string-array
|* *  - compareDoublesPres()    | Description: compares two doubles presciously; returns boolean
|* *  - compareDoubles()        | Description: compares two doubles to a position behind the comma; returns boolean
|* *  - WordCounter()           | Description: counts words in a given string; returns integer
|* * 
|* *  Attention!
|* *  -> There are also some other methods. These aren't accessible (marked as private) and shouldn't be used or changed.
|* * 
|* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *| 
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class HelpC{

    /*
     * This method divides a string into little strings and stores
     * them in a string array. Instead of using toCharArray(), you
     * can use this method, if chars aren't compatible with the rest
     * of your programm. 
     */
    public String[] toStringArray(String inputStr){

        String[] StringArray = new String[inputStr.length()];
        
        for(int i = 0; i < inputStr.length() ; i++){
            char newChar = inputStr.charAt(i);
            String str = "";
            str += newChar;
            StringArray[i] = str;
        }

        return StringArray;
    }


    /*
     * Although it is, generally speaking, not a good idea, to compare doubles, this 
     * method compares two doubles presciously. So, if two doubles should be exactly
     * the same, you can use this method.  
     */
    public boolean compareDoublesPres(double firstInputDouble, double secondInputDouble){

        String firstDoub = "";
        String secondDoub = "";
        firstDoub += firstInputDouble;
        secondDoub += secondInputDouble;

        if(firstDoub.length() != secondDoub.length()){
            return false;
        }else{
            
            if(firstDoub.equals(secondDoub) == true){
                return true;
            }
        }
        return false;
    }


    /*
     * This method is an extension to the method above. You can set a position
     * that says, to which position behind the comma the two doubles should equal
     * each other. For example:
     * System.out.println(HelpC.compareDoubles(12.3456789,12.3456182,5)); -> false
     * System.out.println(HelpC.compareDoubles(12.3456789,12.3456182,4)); -> true
     * 
     * Attention: The first position behind the comma starts with 1 not with 0.
     */

    public boolean compareDoubles(double firstInputDouble, double secondInputDouble, int position){


        String firstDoub = "";
        firstDoub += firstInputDouble;
        String[] firstDoubArray = toStringArray(firstDoub);

        String secondDoub = "";
        secondDoub += secondInputDouble;
        String[] secondDoubArray = toStringArray(secondDoub);
        
        
        int pointAtfirstDoub = searchInStringForChar(firstDoub, ".") + 1;
        int pointAtsecondDoub = searchInStringForChar(secondDoub, ".") + 1;

        if(pointAtfirstDoub != pointAtsecondDoub){
            return false;
        }

        for(int i = 0; i < pointAtfirstDoub; i++){

            if(firstDoubArray[i].equals(secondDoubArray[i]) == false){
                return false;
            }
        }

        for(int i = pointAtfirstDoub; i < (pointAtfirstDoub+position); i++){

            if(firstDoubArray[i].equals(secondDoubArray[i]) == false){
               
                return false;
            }

        }
        return true;
    }


    private int searchInStringForChar(String inputStr, String searchForThis){

        String[] StrArray = toStringArray(inputStr);

        for(int i = 0; i < StrArray.length; i++){

            if(StrArray[i].equals(searchForThis)){
               
                return i;
            }
        }
        return -1;
    }


    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
    *                                                                                                      *
    * The method WordCounter(String inputStr) returns the number of words that the input-string contains.  *
    * If the string contains a NUMBER, the number of words DOES NOT INCREASE.                              *
    * if the string contains something like "Home-Office", the number of words DOES NOT INCREASE.          *
    *                                                                                                      *
    \* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    public int WordCounter(String inputStr){
            
        int numberOfWords = 0;
        String[] StringArray = inputStr.split(" ");
        numberOfWords = StringArray.length;

        for(int i = 0; i < StringArray.length; i++){

            if(searchInStringForNumber(StringArray[i]) == true){
                numberOfWords--;
            }
        }
        return numberOfWords;
    }


    private boolean searchInStringForNumber(String inputStr){

        char[] charArray = inputStr.toCharArray();
        
        for(int currentElement = 0; currentElement < charArray.length; currentElement++){

            if(charArray[currentElement] == '0' || charArray[currentElement] == '1' || charArray[currentElement] == '2' || charArray[currentElement] == '3' || charArray[currentElement] == '4' || charArray[currentElement] == '5' || charArray[currentElement] == '6' || charArray[currentElement] == '7' || charArray[currentElement] == '8' || charArray[currentElement] == '9'){
                return true;
            }
        }
        return false;
    }



    /*
     * If you want to search in a string for a specific symbol (no matter if string
     * or char), use the methods below.  
     */
    public boolean searchInString(String inputStr, String searchForThisStr){

        String[] StringArray = toStringArray(inputStr);
        
        for(int i = 0; i < StringArray.length; i++){

            if(StringArray[i].equals(searchForThisStr) == true){
                return true;
            }
        }
        return false;
    }


    public boolean searchInString(String inputStr, char searchForThisChar){

        String convertToStr = "";
        convertToStr += searchForThisChar;
        return searchInString(inputStr, convertToStr);
    }


    

}