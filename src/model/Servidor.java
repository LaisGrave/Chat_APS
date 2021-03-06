package model;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
/**
 *
 * @author Lais
 */
public class Servidor{
    
    private int porta;
    
    public Servidor(int porta){
        setPorta(porta);
    }
           
        public void rodarServer(){
            //String mensagemRecebida;
            ArrayList <PrintStream> clientes = new ArrayList<>();
            try{
            ServerSocket soc = new ServerSocket (this.porta);
            Socket socket;
            
            
                while(true){
                    socket = soc.accept();      
                       clientes.add(new PrintStream(socket.getOutputStream()));
                       TratamentodeMensagem mensagem = new TratamentodeMensagem (socket, clientes);
                       
                }
            } catch (IOException ex){
                ex.printStackTrace();
            
            }
     }

    /**
     * @return the porta
     */
    public int getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }
    }
    
   

