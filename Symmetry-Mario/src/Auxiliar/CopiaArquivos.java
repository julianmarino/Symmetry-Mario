package Auxiliar;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.nio.channels.FileChannel;  
  
public class CopiaArquivos {
  
    /** 
     * Copia arquivos de um local para o outro 
     * @param origem - Arquivo de origem 
     * @param destino - Arquivo de destino 
     * @param overwrite - Confirma��o para sobrescrever os arquivos 
     * @throws IOException 
     */  
    public static void copy(File origem,File destino,boolean overwrite) throws IOException{  
        if (destino.exists() && !overwrite){  
            System.err.println(destino.getName()+" j� existe, ignorando...");  
            return;  
        }  
        FileInputStream fisOrigem = new FileInputStream(origem);  
        FileOutputStream fisDestino = new FileOutputStream(destino);  
        FileChannel fcOrigem = fisOrigem.getChannel();    
        FileChannel fcDestino = fisDestino.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigem.close();    
        fisDestino.close();  
    }  
      
    /** 
     * Copia todos os arquivos de dentro de uma pasta para outra 
     * @param origem - Diret�rio onde est�o os arquivos a serem copiados 
     * @param destino - Diret�rio onde os arquivos ser�o copiados 
     * @param overwrite - Confirma��o para sobrescrever os arquivos 
     * @throws IOException 
     */  
    public static void copyAll(File origem,File destino,boolean overwrite) throws IOException{  
        if (!destino.exists())  
            destino.mkdir();  
        if (!origem.isDirectory())  
            throw new UnsupportedOperationException("Origem deve ser um diret�rio");  
        if (!destino.isDirectory())  
            throw new UnsupportedOperationException("Destino deve ser um diret�rio");  
        File[] files = origem.listFiles();  
        for (File file : files) {  
            if (file.isDirectory())  
                copyAll(file,new File(destino+"\\"+ file.getName()),overwrite);  
            else{  
                //System.out.println("Copiando arquivo: "+file.getName());  
                copy(file, new File(destino+"\\"+file.getName()),overwrite);  
            }  
        }  
    }  

    /**
     * Copia uma tela para um diret�rio j� definido "telasEinfo/Telas/" e "telasEinfo/InfoTelas/"
     * @param nome: nome da tela
     * @throws IOException
     */
    public void copy(String nome) throws IOException{  
    	File tela = new File("tela");  
        File infoTela = new File("infoTela");
        
        File copiaTela = new File("telasEinfo/Telas/tela" + nome);  
        File copiaInfoTela = new File("telasEinfo/InfoTelas/info" + nome);
        
        //System.out.println("Copiando aquivos...");
        //Salva a Tela
        FileInputStream fisOrigemTela = new FileInputStream(tela);  
        FileOutputStream fisDestinoTela = new FileOutputStream(copiaTela);  
        FileChannel fcOrigem = fisOrigemTela.getChannel();    
        FileChannel fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();  
        
        //Salva as informa�oes da tela
        fisOrigemTela = new FileInputStream(infoTela);  
        fisDestinoTela = new FileOutputStream(copiaInfoTela);  
        fcOrigem = fisOrigemTela.getChannel();    
        fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();
        
        //System.out.println("Finalizado!!!");
    }
    
    
    /**
     * Copia da tela para o diret�rio desejado
     * @param nome: nome da tela
     * @param diretorio: diret�rio da tela
     * @throws IOException
     */
    //current method
    public void copy(String nome, String diretorio) throws IOException{  
    	File tela = new File("tela");  
        File infoTela = new File("infoTela");
        
        File copiaTela = new File(diretorio + "/Telas/tela" + nome);  
        File copiaInfoTela = new File(diretorio + "/InfoTelas/info" + nome);
        
        //System.out.println("Copiando aquivos...");
        //Salva a Tela
        FileInputStream fisOrigemTela = new FileInputStream(tela);  
        FileOutputStream fisDestinoTela = new FileOutputStream(copiaTela);  
        FileChannel fcOrigem = fisOrigemTela.getChannel();    
        FileChannel fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();  
        
        //Salva as informa�oes da tela
        fisOrigemTela = new FileInputStream(infoTela);  
        fisDestinoTela = new FileOutputStream(copiaInfoTela);  
        fcOrigem = fisOrigemTela.getChannel();    
        fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();
        
        //System.out.println("Finalizado!!!");
    }
    
    /**
     * Copia a tela e seu arquivo de informa��es de um diret�rio para outro
     * @param nomeOrigem: nome da tela no diret�rio principal de origem
     * @param diretorioOrigem: nome do diret�rio de origem(diretorio raiz, anterior a pasta de Telas e Infos)    
     * @param diretorioDestino: nome do diret�rio destino(diretorio raiz, anterior a pasta de Telas e Infos)
     * @param nomeDestino: nome que a tela recebet� no destino. Por padr�o, o nome da tela no destino ser� "tela + nomeDestino".Caso seja passado como par�metro ""(um par de aspas duplas), o nome no destino ser� o mesmo da origem
     * @throws IOException
     */
    public void copy(String nomeOrigem, String diretorioOrigem, String diretorioDestino, String nomeDestino) throws IOException{  
    	char[] nome_char = nomeOrigem.toCharArray();
    	String numero = "";
    	for(int i = 4; i < nome_char.length; i++){
    		numero = numero + "" + nome_char[i];
    	}
    	
    	File tela = new File(diretorioOrigem + "Telas/" + nomeOrigem);  
        File infoTela = new File(diretorioOrigem + "InfoTelas/info" + numero);
        
        if(nomeDestino == "")
        	nomeDestino = numero;
        
        File copiaTela = new File(diretorioDestino + "/Telas/tela" + nomeDestino);  
        File copiaInfoTela = new File(diretorioDestino + "/InfoTelas/infoo" + nomeDestino);
        
        //System.out.println("Copiando aquivos...");
        //Salva a Tela
        FileInputStream fisOrigemTela = new FileInputStream(tela);  
        FileOutputStream fisDestinoTela = new FileOutputStream(copiaTela);  
        FileChannel fcOrigem = fisOrigemTela.getChannel();    
        FileChannel fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();  
        
        //Salva as informa�oes da tela
        fisOrigemTela = new FileInputStream(infoTela);  
        fisDestinoTela = new FileOutputStream(copiaInfoTela);  
        fcOrigem = fisOrigemTela.getChannel();    
        fcDestino = fisDestinoTela.getChannel();    
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);    
        fisOrigemTela.close();    
        fisDestinoTela.close();
        
        //System.out.println("Copiado com sucesso!");
    }
    
    /*
    public static void main(String[] args) {  
        File file = new File("D:\\Workspace");  
        File dest = new File("D:\\Destino");  
        try {  
            copyAll(file, dest,true);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
     */
}