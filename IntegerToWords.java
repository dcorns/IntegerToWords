import java.util.Scanner;
class IntegerToWords {
    static String [] ones = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    static String [] tens = {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    static String [] tenMulty = {"","","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    public static String getWord(String idx, String [] ary) {
        int i = Integer.parseInt(idx);
        return ary[i];
    }
    public static String tensAndBelowToWords(String num, int numberOfPlaces) {
        String result;
        if (numberOfPlaces < 2) {
            return getWord(num, ones);
        }
        char [] places = num.toCharArray();
        int number = Integer.parseInt(num);
        String tensPlace = String.valueOf(places[0]);
        String onesPlace = String.valueOf(places[1]);
            if(number < 20) {
                //System.out.printf("\n"+getWord(onesPlace, tens));
                return getWord(onesPlace, tens);
            }
            result = getWord(tensPlace, tenMulty) + " ";
            if(Integer.parseInt(onesPlace) > 0){
                result = result + getWord(onesPlace, ones);
            }
            //System.out.printf("\n" + result);
            return result;
    }
    public static String threePlacesToWords(String num, Boolean lastThree){
        char [] places = num.toCharArray();
        String conjunction = " ";
        String result = "";
        if(lastThree) {
            conjunction = "and ";
        }
        if(places[0] != '0') {
            String hunsPlace = String.valueOf(places[0]);
            result = getWord(hunsPlace, ones) + " Hundred ";
        }
        if(places[1] != '0') {
            result = result + conjunction + tensAndBelowToWords(String.valueOf(places[1])+String.valueOf(places[2]), 2);
        }
        if(places[1] == '0' && places[2] != '0'){
            String onesPlace = String.valueOf(places[2]);
            result = result + conjunction + getWord(onesPlace, ones);
        }
        return result;
    }
    public static String integerToWords(String num){
        int numberOfPlaces = num.length();
        String result = "";
        if(numberOfPlaces < 3) {
            return tensAndBelowToWords(num, numberOfPlaces);
        }
        if(numberOfPlaces < 4) {
            return threePlacesToWords(num, true);
        }
        char [] places = num.toCharArray();
        String msDigit = String.valueOf(places[0]);
        result = getWord(msDigit, ones) + " Thousand ";
        String leftOverDigits = String.valueOf(places[1]) + String.valueOf(places[2]) + String.valueOf(places[3]);
        return result + integerToWords(leftOverDigits);
    }
    public static void main(String[ ] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer from 0 to 9999: ");
        String num = input.nextLine();
        System.out.print("\n"+integerToWords(num));
    }
}