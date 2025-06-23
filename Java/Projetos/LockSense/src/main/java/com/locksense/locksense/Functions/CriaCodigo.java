package com.locksense.locksense.Functions;

import java.util.Random;

public class CriaCodigo {
    public static int criaCodigo() {
        System.out.println("Gerando código...");
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        System.out.println("Código de acesso gerado: " + codigo);
        return codigo;
    }
}
