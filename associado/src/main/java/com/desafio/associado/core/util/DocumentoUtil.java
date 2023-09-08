package com.desafio.associado.core.util;

public final class DocumentoUtil {

    private DocumentoUtil(){}

    public static String somenteNumeros(String documento) {
        if (documento == null) {
            return null;
        }
        return documento.replaceAll("\\D", "");
    }
}