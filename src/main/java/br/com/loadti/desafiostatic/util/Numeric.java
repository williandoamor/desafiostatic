package br.com.loadti.desafiostatic.util;

import java.math.BigDecimal;

public class Numeric {

    /**
     * *@param recebe uma String com o valor
     * *@return Retorna o valor em BigDecimal
     */
    public static BigDecimal textForBigDecimal(String text, int escala, int roundapMode) {

        BigDecimal valor = new BigDecimal(0);

        try {

            /*
             * Caso o text estiver vazio ou null, então retorna um valor zerado
             */
            if (text != null && !text.trim().equals("")) {

                /*
                 * Verificar se tem ponto e virgula
                 */
                if (text.contains(".") && text.contains(",")) {

                    // Remove o ponto. Ex.: 1.234,56 fica 1234,56
                    text = text.replace(".", "");

                    // Substitui a virgula por ponto. Ex.: 1234,56 fica 1234.56
                    text = text.replace(",", ".");

                    valor = new BigDecimal(text).setScale(escala, roundapMode);

                }

                /*
                 * Verificar se tem ponto e não tem virgula
                 */
                else if (text.contains(".") && !text.contains(","))
                    valor = new BigDecimal(text).setScale(escala, roundapMode);

                    /*
                     * Verificar se não tem ponto e tem virgula
                     */
                else if (!text.contains(".") && text.contains(",")) {

                    // Substitui a virgula por ponto. Ex.: 1,23 fica 1.23
                    text = text.replace(",", ".");

                    valor = new BigDecimal(text).setScale(escala, roundapMode);

                } else
                    valor = new BigDecimal(text).setScale(escala, roundapMode);

            }

            return valor;

        } catch (Exception e) {

            return new BigDecimal(0);

        }

    }

}
