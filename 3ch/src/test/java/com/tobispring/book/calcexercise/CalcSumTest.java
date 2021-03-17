package com.tobispring.book.calcexercise;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CalcSumTest {

    Calculator calculator;
    String numFilePath;

    @BeforeEach void setUp() {
        this.calculator = new Calculator();
        this.numFilePath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    @DisplayName("파일에서 합구하기")
    void sumOfNumbers() throws IOException {
        Assertions.assertThat(calculator.calcSum(this.numFilePath)).isSameAs(10);
    }
}
