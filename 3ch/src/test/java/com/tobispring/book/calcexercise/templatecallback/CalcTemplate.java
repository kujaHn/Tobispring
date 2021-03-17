package com.tobispring.book.calcexercise.templatecallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalcTemplate {

    // 템플릿
    public Integer fileReadTemplate(String filePath, BufferedReaderCallBack callBack) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callBack.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null) {
                try { br.close(); }
                catch (IOException e) { System.out.println(e.getMessage()); }
            }
        }
    }
}
