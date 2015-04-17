package featureGenerator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import classifier.WebpageClass;

public class KeywordsFeatureVectorGenerator implements FeatureVectorGenerator {

    @Override
    public Map<List<Integer>, WebpageClass> getFeatureVectors(Map<File, WebpageClass> trainingFiles) throws Exception {
        // Create 3 sets to hold different files
        Set<File> faculty = new HashSet<>();
        Set<File> student = new HashSet<>();
        Set<File> course = new HashSet<>();
        // iterator all the files
        Set<Entry<File, WebpageClass>> setOfFile = trainingFiles.entrySet();
        for (Entry<File, WebpageClass> entry : setOfFile) {
            if (entry.getValue() == WebpageClass.COURSE) {
                course.add(entry.getKey());
            } else if (entry.getValue() == WebpageClass.STUDENT) {
                student.add(entry.getKey());
            } else {
                faculty.add(entry.getKey());
            }
        }
        List<String> topWords_course = findTopKWord(course);
        List<String> topWords_student = findTopKWord(student);
        List<String> topWords_faculty = findTopKWord(faculty);

        Map<List<Integer>, WebpageClass> result = generateVector(topWords_course, topWords_student, topWords_faculty,
                trainingFiles);

        return result;
    }

    private static Map<List<Integer>, WebpageClass> generateVector(List<String> topWords_course,
            List<String> topWords_student, List<String> topWords_faculty, Map<File, WebpageClass> trainingFiles)
            throws IOException {
        // int k=30;
        Map<List<Integer>, WebpageClass> vectorWithLable = new HashMap<>();

        for (Entry<File, WebpageClass> e : trainingFiles.entrySet()) {
            List<Integer> vector = new LinkedList<>();
            for (int i = 0; i < topWords_course.size(); i++) {
                vector.add(-1);
            }
            Document document = Jsoup.parse(e.getKey(), "UTF-8");

            Queue<String> plainText = new LinkedList<>();
            Map<String, Integer> wordsWithFreq = new HashMap<>();
            Elements tags = document.select("*");
            for (Element element : tags) {
                String temp = element.ownText();
                if (!temp.equals("")) {
                    plainText.add(temp);
                }
            }
            for (String str : plainText) {
                String[] temp = str.split(" ");
                for (String tp : temp) {
                    if (!wordsWithFreq.containsKey(tp.toLowerCase())) {
                        wordsWithFreq.put(tp.toLowerCase(), 1);
                    } else {
                        wordsWithFreq.put(tp.toLowerCase(), wordsWithFreq.get(tp.toLowerCase()) + 1);
                    }
                }
            }

            if (e.getValue() == WebpageClass.COURSE) {
                for (int i = 0; i < topWords_course.size(); i++) {
                    if (wordsWithFreq.containsKey(topWords_course.get(i))) {
                        vector.set(i, wordsWithFreq.get(topWords_course.get(i)));
                    } else {
                        vector.set(i, 0);
                    }
                }
            } else if (e.getValue() == WebpageClass.STUDENT) {
                for (int i = 0; i < topWords_student.size(); i++) {
                    if (wordsWithFreq.containsKey(topWords_student.get(i))) {
                        vector.set(i, wordsWithFreq.get(topWords_student.get(i)));
                    } else {
                        vector.set(i, 0);
                    }
                }
            } else {
                for (int i = 0; i < topWords_faculty.size(); i++) {
                    if (wordsWithFreq.containsKey(topWords_faculty.get(i))) {
                        vector.set(i, wordsWithFreq.get(topWords_faculty.get(i)));
                    } else {
                        vector.set(i, 0);
                    }
                }
            }

            vectorWithLable.put(vector, e.getValue());

        }

        return vectorWithLable;
    }

    private static List<String> findTopKWord(Set<File> fileSet) throws IOException {
        Set<String> ignoreString = new HashSet<>();
        ignoreString.add("");
        ignoreString.add(".");
        ignoreString.add(",");
        ignoreString.add("-");
        ignoreString.add("to");
        ignoreString.add("and");
        ignoreString.add("the");
        ignoreString.add("of");
        ignoreString.add("in");
        ignoreString.add("a");
        ignoreString.add("an");
        ignoreString.add("is");
        ignoreString.add("will");
        ignoreString.add("that");
        ignoreString.add("?");
        ignoreString.add("!");
        ignoreString.add("as");
        ignoreString.add("for");
        ignoreString.add("be");
        ignoreString.add(":");
        ignoreString.add(";");
        ignoreString.add("1996");
        ignoreString.add("or");

        int k = 50;
        List<String> topK = new LinkedList<>();
        Map<String, Integer> wordsWithFreq = new HashMap<>();
        for (File f : fileSet) {
            Queue<String> plainText = new LinkedList<>();
            Document document = Jsoup.parse(f, "UTF-8");
            Elements tags = document.select("*");
            for (Element element : tags) {
                String temp = element.ownText();
                if (!temp.equals("")) {
                    plainText.add(temp);
                }
            }
            for (String str : plainText) {
                String[] temp = str.split(" ");
                for (String tp : temp) {
                    if (!ignoreString.contains(tp.toLowerCase())) {
                        if (!wordsWithFreq.containsKey(tp.toLowerCase())) {
                            wordsWithFreq.put(tp.toLowerCase(), 1);
                        } else {
                            wordsWithFreq.put(tp.toLowerCase(), wordsWithFreq.get(tp.toLowerCase()) + 1);
                        }
                    }
                }
            }
        }

        List<String> words = new LinkedList<>();
        List<Integer> freq = new LinkedList<>();
        for (Entry<String, Integer> e : wordsWithFreq.entrySet()) {
            words.add(e.getKey());
            freq.add(e.getValue());
        }
        for (int i = 0; i < k; i++) {
            int index = findLargestValueIndex(freq);
            String s = words.get(index);
            words.remove(index);
            freq.remove(index);
            topK.add(s);
        }

        return topK;
    }

    private static int findLargestValueIndex(List<Integer> l) {
        int maxValue = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < l.size(); i++) {
            int temp = l.get(i);
            if (maxValue < temp) {
                maxValue = temp;
                index = i;
            }
        }
        return index;
    }

}
