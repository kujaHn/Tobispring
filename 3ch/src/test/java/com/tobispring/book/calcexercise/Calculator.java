package com.tobispring.book.calcexercise;

import com.tobispring.book.calcexercise.templatecallback.BufferedReaderCallBack;
import com.tobispring.book.calcexercise.templatecallback.CalcTemplate;

import java.io.BufferedReader;
import java.io.IOException;

public class Calculator {

    private CalcTemplate calcTemplate;

    public Integer calcSum(final String filePath) throws IOException {
        BufferedReaderCallBack sumCallBack =
                new BufferedReaderCallBack() {
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer sum = 0;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sum += Integer.valueOf(line);
                        }
                        return sum;
                    }
                };
        return calcTemplate.fileReadTemplate(filePath, sumCallBack);
    }
}
