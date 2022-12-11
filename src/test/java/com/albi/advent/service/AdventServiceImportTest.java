package com.albi.advent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdventServiceImportTest {

    @InjectMocks
    AdventServiceImport adventServiceImport;

    @BeforeEach
    void tearDown() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidYearAndDayShouldReturnAStringOfValues() {
        int year = 2022;
        int day = 1;
        assertThat(adventServiceImport.getImputBasedOnYearAndDay(year, day)).isNotEmpty();
    }

    @Test
    void givenInvalidYearAndDayShouldReturnAStringOfValues() {
        int year = 20;
        int day = 0;
        assertThrows(RuntimeException.class, () -> adventServiceImport.getImputBasedOnYearAndDay(year, day));
    }
}