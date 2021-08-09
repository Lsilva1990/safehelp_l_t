/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SafeHelpServer.Util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author elder
 */
public class Msg implements Serializable{
    
    private String operacao;
    private MsgStatus status;
    
    /* 
    chave : Object
    */
    
    Map<String, Object> params;
    
    public Msg(String operacao)
    {
       this.operacao = operacao;
       params = new HashMap<>();
    }
    
    public String getOperacao()
    {
        return operacao;
    }
    
    public void setStatus(MsgStatus s)
    {
        this.status = s;
    }
    public MsgStatus getStatus()
    {
        return status;
    }
    /*
        "NOME" --> "José"
        "IDADE" --> 35
     */
    public void setParam( String chave, Object valor )
    {
        params.put( chave, valor );
    }
    
    public Object getParam( String chave )
    {
        return params.get(chave);
    }
    
    @Override
    public String toString()
    {
        String m = "Operacao: "+ operacao;
        m += "\nStatus: "+status;
        
        m += "\nParâmetros:\n ";
        for (String p : params.keySet() ) { 
            m += p+": " + params.get(p)+"\n";  
        }
        return m;
    }
    
    
}
