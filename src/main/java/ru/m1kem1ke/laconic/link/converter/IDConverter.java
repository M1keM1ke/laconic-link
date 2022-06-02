package ru.m1kem1ke.laconic.link.converter;

import java.util.List;

public interface IDConverter {
    List<Integer> convertBase10ToAnother(int id);

    Integer convertAnotherToBase10ID(List<Character> ids);
}
