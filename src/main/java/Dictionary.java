import java.util.*;

public class Dictionary {

    private ArrayList<String> forwardList = new ArrayList<>();
    private ArrayList<String> backwardList = new ArrayList<>();
    private StringBuilder forwardBuilder = new StringBuilder();
    private StringBuilder backwardBuilder = new StringBuilder();
    private static String word = "WORKING";
    private List<String> preDefinedList = Arrays.asList("WORKING", "RING", "NIK");

    public Dictionary(String input){
        word = input;
    }

    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary(word);
        System.out.println(dictionary.findPossibleWords(0,word.length()-1));
    }


    /**
     * Uses list of predefinedList of strings and returns boolean if the string is part of the list
     * @param word
     * @return boolean
     */
    public boolean isEnglishWord(String word) {
        if (preDefinedList.contains(word)) {
            return true;
        }
        return false;
    }


    /**
     * For loop to process each character of the string in forward direction
     * @param startIndex
     * @return List of words
     */

    public ArrayList<String> findForwardWords(int startIndex) {
        for (int i = startIndex; i < word.length(); ++i) {
            forwardBuilder.append(word.charAt(i));
            if (isEnglishWord(forwardBuilder.toString())) {
                forwardList.add(forwardBuilder.toString());
            }

            if (i < word.length())
                findForwardWords(i + 1);

            forwardBuilder.setLength(forwardBuilder.length() - 1);
        }

        return forwardList;
    }

    /**
     * For loop to process each character of the string in Backward direction
     * @param lastIndex
     * @return List of words
     */
    public ArrayList<String> findBackwardWords(int lastIndex){
        for(int i = lastIndex;i>=0;--i){
            backwardBuilder.append(word.charAt(i));
            if (isEnglishWord(backwardBuilder.toString())) {
                backwardList.add(backwardBuilder.toString());
            }

            if (i >=1)
                findBackwardWords(i - 1);

            backwardBuilder.setLength(backwardBuilder.length() - 1);
        }
        return backwardList;

    }

    /**
     * Combine forward list words and backward list words and remove duplicates
     * @param start
     * @param last
     * @return
     */

    public List<String> findPossibleWords(int start,int last){
        findForwardWords(start);
        findBackwardWords(last);
        forwardList.addAll(backwardList);
        ArrayList<String> listWithOutduplicates = new ArrayList<>(new LinkedHashSet<>(forwardList));
        return listWithOutduplicates;
    }


}

