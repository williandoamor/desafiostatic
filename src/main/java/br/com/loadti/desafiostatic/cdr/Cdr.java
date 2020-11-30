package br.com.loadti.desafiostatic.cdr;

import br.com.loadti.desafiostatic.util.Numeric;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.spark.sql.Row;

import java.math.BigDecimal;
import java.util.List;

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
    private Double call_duration;

    /*Inicio da chamada*/
    private String start_time;

    /*Idendificacao do cliente*/
    private String imsi;

    /**/
    private String switch_call;

    /*Antena de entrada*/
    private String cell_in;

    /*Antena de saida*/
    private String cell_out;

    /*Tipo da tecnologida*/
    private String tecnologia;

    /*Nome do arquivo*/
    private String filename;

    /**/
    private String first_lac;

    /**/
    private String last_lac;

    private String ggcn_addrress;


}
