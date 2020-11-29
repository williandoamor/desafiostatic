package br.com.loadti.desafiostatic.cdr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cdr {

    /*Id de controle*/
    private int exchange_id;

    /*Numero que fez a ligacao*/
    private String calling_number;

    /*Numero que recebeu a ligacao*/
    private String called_number;

    /*Serial do aparelho que fez a ligacao*/
    private String serial_number;

    /*Tipo da chamada*/
    private int call_type;

    /*Duracao da chamada*/
    private BigDecimal call_duration;

    /*Inicio da chamada*/
    private String start_time;

    /*Idendificacao do cliente*/
    private int imsi;

    /**/
    private String switch_call;

    /*Antena de entrada*/
    private int cell_in;

    /*Antena de saida*/
    private int cell_out;

    /*Tipo da tecnologida*/
    private String tecnologia;

    /*Nome do arquivo*/
    private String filename;

    /**/
    private BigDecimal first_lac;

    /**/
    private BigDecimal last_lac;

    private String ggcn_addrress;
}
