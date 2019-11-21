/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author EduardoCFN
 */
public class Aplicacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
  
        ArrayList<String> lista = new ArrayList(),
        listaB = new ArrayList();
        
        String mensagem = "Texto1 Texto2 Texto3 Texto4 Texto5";
        
        
        SocketServidor servidor = new 
        
        
        //Ignorando o codigo abaixo.
         System.exit(0);
        
        for(int i=0; i<=50 ;i++)
        {
            String s = String.valueOf(i)+ ": " + mensagem;
            lista.add(s);
        }
        
        
        Iterator i= lista.iterator();
        
        while(i.hasNext())
        {
            String texto = (String) i.next();
            
            System.out.println(texto);
        }
        
        System.out.println("\n\n\nListaB Vazia");
        
        i= listaB.iterator();
        
        while(i.hasNext())
        {
            String texto = (String) i.next();
            
            System.out.println(texto);
        }
        
         System.out.println("\n\n\nListaB Clonada");
        
        listaB.addAll(lista);
        
        i= listaB.iterator();
         
        while(i.hasNext())
        {
            String texto = (String) i.next();
            
            System.out.println(texto);
        }
        
        
        
        
        
    }
    
}
