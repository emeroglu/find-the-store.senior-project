package com.example.kaan.findthestore.Model;

import java.util.ArrayList;

/**
 * Created by kaan on 06/05/15.
 */
public class Dictionary {

    private static ArrayList<Word> listWords;

    public static void Fill(){

        listWords = new ArrayList<>();

        Word word = new Word();
        word.Keyword = "*GoUp";
        word.Turkish = "Asansörle %1$s. kata çık.";
        word.English = "You should use elevator to go floor %1$s";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoDown";
        word.Turkish = "Asansörle %1$s. kata in.";
        word.English = "You should use elevator to go floor %1$s";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoStraight";
        word.Turkish = "Sağ veya sol yolu kullanarak asansörün karşı tarafına geç.";
        word.English = "You should use either right way or left way to pass opposite of elevator.";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoLeft";
        word.Turkish = "Sola dönerek biraz gittikten sonra, sola dönerek yolu takip et";
        word.English = "You should turn left and after ten meters, you should again turn left and follow the path";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoRight";
        word.Turkish = "Sağa dönerek biraz gittikten sonra, sağa dönerek yolu takip et";
        word.English = "You should turn right and after ten meters, you should again turn right and follow the path";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoLeftWithElevator";
        word.Turkish = "Sağa dönerek biraz gittikten sonra, sola dönerek yolu takip et";
        word.English = "You should turn right and after ten meters, you should turn left and follow the path";

        listWords.add(word);

        word = new Word();
        word.Keyword = "*GoRightWithElevator";
        word.Turkish = "Sola dönerek biraz gittikten sonra, sağa dönerek yolu takip et";
        word.English = "You should turn left and after ten meters, you should turn right and follow the path";

        listWords.add(word);

        word = new Word();

        word.Keyword = "*Find";
        word.Turkish = "Bul";
        word.English = "Find";

        listWords.add(word);

    }

    public static String getTranslation(String key,int lang){

        for (int i = 0; i < listWords.size(); i++) {

            if (listWords.get(i).Keyword == key)
            {
                if (lang == 0)
                    return  listWords.get(i).Turkish;
                else
                    return  listWords.get(i).English;
            }

        }

        return  "";

    }

}
