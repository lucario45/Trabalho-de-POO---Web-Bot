package roboLigaMagic;

import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainRoboLigaMagic {

	public static void main(String[] args) {  
        String string = "Chandra Nalaar"; 
       
        buscarPreco(string);
    }
    
    public static void buscarPreco(String nomeCarta){
    	
    	// isso aqui � s� pra fazer bang funcionar no meu trabalho.
    	// se voc� for mexer nesse cara, comente essa linha e depois lembre-se de volta-la ao normal k
    	// ass: L�der
    	System.setProperty("http.proxyHost", "spoigpxy0002.indusval.com.br");
		System.setProperty("http.proxyPort", "8080");
        
        String urlbusca = "http://www.ligamagic.com.br/?view=cards%2Fsearch&card="; 
        String url = urlbusca + URLEncoder.encode(nomeCarta); // TODO: Usar uma fun��o que n�o esteja deprecated...
        
        try {		
            Connection connection = Jsoup.connect(url);
					
            Document doc = connection.get();
			
            Element teste = doc.getElementsByClass("lj").get(2); // NOTA: o primeiro � o nome e o segundo � uma outra coisa whatever
            													 // NOTA: Esse algoritmo � bem ad-hoc, qualquer mudan�a no site da liga pode impactar em mudan�as
            												     // nesse cara. ass: L�der
            String t = teste.text();			  // NOTA: limpar esse valor
            System.out.println("Sucesso - "+url); 
            									  // TODO: pegar o resto das informa��es
            									  // TODO: ver se d� pra downloadar e exibir a imagemzinha da carta
            									  // TODO: Ver se n�o seria melhor downlodar tudo, colocar num bancode dados e depois exibir pro usuario.
            } 
            catch (Exception e) {
            	System.out.println("Fudeu fudeu");
                e.printStackTrace();
            } // NOTA: Em referenced libraries, o jar com o jsoup est� configurado para ser o que est� na minha maquina de trabalho
              // se voc� mudar vai dar erro.
              // lembre-se de mudar esse cara depois caso voc� execute o projeto de outra maquina
              // o .jar est� dentro da pasta desse projeto
        	  // Ass: L�der
    }

}
