/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacoteServidor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EduardoCFN
 */
public class Mensagem implements Serializable
{
    private String operacao;
    private Status status;
    Map<String, Object> params;  
    List<String> lista;
    
    public Mensagem(String operacao)
    {
        this.operacao = operacao;
        params = new HashMap<>();
    }
    
    public String getOperacao()
    {
        return operacao;
    }
    
    public void setStatus(Status s)
    {
        this.status = s;
    }
    
    public Status getStatus()
    {
        return status;
    }
    
    public void setParam( String chave, Object valor)
    {
        params.put(chave, valor);
    }
    
    public Object getParam( String chave)
    {
        return params.get(chave);
    }
    
    public void addString(String s)
    {
        if(lista == null)
        lista.add(s);
    }
    
    //false: A String não está presente na 'lista'.
    //true: A String esta presente na 'lista' e foi removida.
    public boolean removerString(String s)
    {
        return lista.remove(s);
    }
    
    public List<String> retornarLista()
    {
        if(!lista.isEmpty())
        {
             return lista;
        }else
        {
            return null;
        }
          
    }
    
    @Override
    public String toString()
    {
        String m = "Operacao: " + operacao;
        m += "\nStatus: " + status;
        m += "\nParâmetros:\n";
        
        for (String p : params.keySet())
        {
            m += "\n" + p + ": " + params.get(p);
        }
        
        return m;
    }
    
}
