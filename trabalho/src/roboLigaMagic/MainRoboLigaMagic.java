package roboLigaMagic;

import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainRoboLigaMagic {

	public static void main(String[] args) {  
        String string = "Kalitas, Bloodchief of Ghet"; 
        // NOTA: Se o rob� n�o encontrar o nome da carta exatamente, ele vai cuspir Informa��es aleat�rias bizarras.
        // Isso tamb�m vale caso o nome da carta esteja qualquer lingua que n�o o ingl�s na busca ��
       
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
            
            Elements precosEQuantidades = doc.select("tr > td > p"); // NOTA: o primeiro � o nome e o segundo � uma outra coisa whatever
			 														 // NOTA: Esse algoritmo � bem ad-hoc, qualquer mudan�a no site da liga pode impactar em mudan�as
  			 														 // nesse cara. ass: L�der
		  				 										     // NOTA: limpar esse valor
            
            Element precoElemento = precosEQuantidades.get(0);
            Element quantidadeElemento    = precosEQuantidades.get(1);
            
            String precoSujo = precoElemento.text();
            String quantidadeSuja = quantidadeElemento.text();
            String precos[] = precoSujo.split(" ");
            String preco = precos[precos.length-1];
            String quantidades[] = quantidadeSuja.split(" ");
            String quantidade = quantidades[0];
            
            Elements tagBannerLoja = doc.select("tr > td.banner-loja > a > img");
            Element tagLojaMaisBarata = tagBannerLoja.get(0);
            String nomeLoja = tagLojaMaisBarata.attr("title");
            
            System.out.println("preco = "+preco+" ; Quantidade: "+quantidade+" Loja: "+nomeLoja);
            
            
            						
            									  // TODO: ver se d� pra downloadar e exibir a imagemzinha da carta
            									  // TODO: Ver se n�o seria melhor downlodar tudo, colocar num banco de dados e depois exibir pro usuario.
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
