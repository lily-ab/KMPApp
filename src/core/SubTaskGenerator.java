package core;

import java.util.*;
import java.util.stream.IntStream;

public class SubTaskGenerator {

    public void generate(){
        char[] separators = new char[] {' ', ',', '.'};
        String bestSentence = "Пьер Безухов смотрит аниме";
        Set<String> uniqueWordPairs = GetUniqueWordPairs(bestSentence);

        for (String pair: uniqueWordPairs)
        {
            System.out.println(pair);
        }
    }

    Set<String> GetUniqueWordPairs(String input)
    {
        List<String> words = Arrays.asList(input.split(" "));
        Set result = new HashSet<String>();

        for(int[] sequence: GetUniqueSequences(words.size()))
        {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < words.size(); i++)
            {
                sb.append(words.get(sequence[i])+" ");
            }

            result.add(sb.toString());
        }

        return result;
    }

    char[] separators = new char[] {' ', ',', '.'};

    void Swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    List<int[]> GetUniqueSequences(int nn)
    {
        int[] initialSequence = IntStream.range(0, nn).toArray();
        List result = new ArrayList<int[]>();

        permute(initialSequence, initialSequence.length, initialSequence.length,result);

        return result;
    }

    void permute(int[] array, int size, int n, List result)
    {
        if (size == 1)
            result.add(array.clone());

        for (int i=0; i<size; i++)
        {
            permute(array, size-1, n, result);

            if (size % 2 == 1)
            {
                Swap(array, 0, size - 1);
            }
            else
            {
                Swap(array, i, size - 1);
            }
        }
    }
}
