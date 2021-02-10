package outils;

public class StringUtils {
    /* String chaine = "abcdefgh";
    char[] char_table = chaine.toCharArray();
    char_table[0]=Character.toUpperCase(char_table[0]);
    chaine = new String(char_table); */
    public static String firstLetter(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

}
