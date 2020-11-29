package br.com.loadti.desafiostatic.response;


import lombok.Data;

@Data
public class Response {

    /*Resposta 0 - Erro, 1 - OK, 2 - OK mas com Restrições*/
    private int response;
    /*Messagem da Resposta*/
    private String message;
    /*Error Código*/
    public int status;
    /*Object Reponse*/
    private Object Object;


    public Response OK() {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage("OK");
        response.setObject(null);
        response.setStatus(200);
        return response;
    }

    public Response OK(Object object) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage("OK");
        response.setObject(object);
        response.setStatus(200);
        return response;
    }

    public Response Vazio() {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage("Dados Não Encontrados");
        response.setObject(null);
        response.setStatus(404);
        return response;
    }

    public Response Vazio(Object object) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage("Dados Não Encontrados");
        response.setObject(object);
        response.setStatus(404);
        return response;
    }

    public Response Mensagem(String message) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage(message);
        response.setObject(null);
        response.setStatus(200);
        return response;
    }

    public Response Mensagem(String message, Object object) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage(message);
        response.setObject(object);
        response.setStatus(200);
        return response;
    }

    public Response Error(String error) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage(error);
        response.setObject(null);
        response.setStatus(500);
        return response;
    }

    public Response Error(String error, Object object) {
        Response response = new Response();
        response.setResponse(1);
        response.setMessage(error);
        response.setObject(object);
        response.setStatus(500);
        return response;
    }
}