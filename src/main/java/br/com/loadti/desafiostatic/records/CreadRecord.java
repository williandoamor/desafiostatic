package br.com.loadti.desafiostatic.records;


import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;

import java.io.IOException;
import java.util.StringTokenizer;

public class CreadRecord {


    public GenericData.Record getRecord(StringTokenizer st, Schema schema) throws IOException {

        GenericData.Record record = null;

        /*Chega o tokenizer para que nao
         * ocorra null ao chamar o nextToken*/
        while (st.hasMoreTokens()) {

            record = new GenericData.Record(schema);

            /*Cria registro*/
            record.put("exchange_id", st.nextToken());
            record.put("calling_number", st.nextToken());
            record.put("called_number", st.nextToken());
            record.put("serial_number", st.nextToken());
            record.put("call_type", Integer.parseInt(st.nextToken()));
            record.put("call_duration", Double.parseDouble(st.nextToken()));
            record.put("start_time", st.nextToken());
            record.put("imsi", st.nextToken());
            record.put("switch_call", st.nextToken());
            record.put("cell_in", st.nextToken());
            record.put("cell_out", st.nextToken());
            record.put("tecnologia", st.nextToken());
            record.put("filename", st.nextToken());
            record.put("first_lac", st.nextToken());
            record.put("last_lac", st.nextToken());
            record.put("ggcn_addrress", st.nextToken());

        }

        return record;
    }
}
