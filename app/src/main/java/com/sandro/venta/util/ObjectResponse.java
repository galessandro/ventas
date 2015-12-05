package com.sandro.venta.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por ggranados on 05/12/15.
 */
public class ObjectResponse {

    private boolean error;
    private List<String> mensajes;

    public ObjectResponse(){
        mensajes = new ArrayList<>();
    }

    public void addMensaje(String mensaje){
        mensajes.add(mensaje);
    }

    public void clearMensajes(){
        mensajes.clear();
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensajesError(){
        String mensajeError = "";
        for (String mensaje: mensajes) {
            mensajeError += mensaje + "\n";
        }
        return mensajeError;
    }
}
