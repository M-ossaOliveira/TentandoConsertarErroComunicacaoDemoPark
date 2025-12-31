package com.mballem.demoparkapi.util;

import java.time.LocalDateTime;

public class EstacionamentoUtils {

    //formato como é LocalDateTime
    //2023-03-16T15:23:48.616463500
    //como desejamos que seja
    //20230316-152348

    public static String gerarRecibo(){
        LocalDateTime date = LocalDateTime.now();
        String recibo=date.toString().substring(0,19);
        return recibo.replace("-","")
                .replace(":","")
                .replace("T","-");
    }
}
