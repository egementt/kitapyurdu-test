package com.example.kitapyurdutest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {

    Scanner scanner;

    public CSVReader(String filePath) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filePath));
    }


    public ArrayList<String> readFile(){
        ArrayList<String> arrayList = new ArrayList<>();
        scanner.useDelimiter(", ");
        while (scanner.hasNext()){
            String current = scanner.next();
            arrayList.add(current);
        }
        return arrayList;
    }


    public static void main(String[] args) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader("C:\\Users\\egeme\\Desktop\\seleniumWork\\kitapyurdu-test\\src\\test\\java\\com\\example\\kitapyurdutest\\res\\test-text.csv");
        var arr = csvReader.readFile();
        arr.forEach(System.out::println);

    }
}
