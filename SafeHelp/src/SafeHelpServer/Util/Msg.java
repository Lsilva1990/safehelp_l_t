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
    
    private String route;
    private MsgStatus status;
    
    /* 
    chave : Object
    */
    
    Map<String,Object> msg;
    Map<String,Map<String,Object>> params;
    Map<String,String> identify;
    
    public Msg(String route)
    {
       this.route = route;
       msg = new HashMap<>();
       params = new HashMap<>();
       identify = new HashMap<>();
       
    }
    
    public String getRoute()
    {
        return route;
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
    public void setMsg( String chave, Object valor )
    {
        msg.put( chave, valor );
    }
    
    public Object getMsg( String chave )
    {
        return msg.get(chave);
    }
    
    public void setParams( String chave, Map<String,Object> msg  )
    {
        params.put( chave, msg );
    }
    
    public Object getParams( String chave )
    {
        return params.get(chave);
    }
    
    public void setParams( String chave, String valor  )
    {
        params.put( chave, msg );
    }
    
    
    @Override
    public String toString()
    {
        String m = "Operacao: "+ route;
        m += "\nStatus: "+status;
        
        m += "\nParâmetros:\n ";
        for (String p : params.keySet() ) { 
            m += p+": " + params.get(p)+"\n";
            for(String mg : msg.keySet() ){
                m += mg+": " + msg.get(msg)+"\n";
            }
        }
        return m;
    }
    
    
}
