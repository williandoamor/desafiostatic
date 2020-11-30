package br.com.loadti.desafiostatic.objetos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class cdrParameter {

    /*Data inicial passada por parametro*/
    private String dataInicial;

    /*Data final passada por parametro*/
    private String dataFinal;

    /*Numero que fez a ligacao*/
    private String chamador;

    /*Numero que recebeu a ligação*/
    private String recebedor;

    /*Antena de entrada*/
    private String antenain;

    /*Antena de saida*/
    private String antenaout;
}
