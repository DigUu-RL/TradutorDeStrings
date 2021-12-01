package teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
	public static void main(String[] args) {
		//texto a ser trauzido
		String textoParaTraduzir = "argumento inválido!";
		
		try {
			String textoTraduzido = translate("pt-br", "en", textoParaTraduzir);
			
			System.out.println("Texto traduzido: " + textoTraduzido);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//método que realiza toda a função de tradução
	//sendo necessário passa o idioma original, o odioma desejado e a string que deseja traduzir
	private static String translate(String langFrom, String langTo, String text) throws IOException {
		// minha URL criada apartir de um script do google (basta procurar "Google Scripts" para poder criar a sua com o código fonte)
		String myURL = "https://script.google.com/macros/s/AKfycbwGfvrFvmEuKMM_1oR7BHEXT7bBufdVVTTc4Dbncu8u7Vfj4WENjTt3Ftl0AeD1VI3wHQ/exec";
		
		//string que irá acessar a API de tradução de texto
		String urlStr = myURL +
				"?q=" + URLEncoder.encode(text, "UTF-8") +
				"&target=" + langTo +
				"&source=" + langFrom;
		
		//instaânciando uma nova API
		URL url = new URL(urlStr);
		
		//instânciando um StringBuilder
		StringBuilder response = new StringBuilder();
		
		//pegando a conexão da página web que a API está acessando
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		//preciso entender ainda o que isso significa...
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		//instânciando um BufferedReader, que irá ler as linhas retornadas pela API
		BufferedReader in = new BufferedReader(
				//passando um objeto InputStreamReader como parâmetro
				new InputStreamReader(
						//passando o retorna da API no construtor do InputStreamReader
						con.getInputStream()
				)
		);
		
		//lend
		String inputLine;
		
		//verificando se as linhas que estão sendo lindas estão vazias
		while ((inputLine = in.readLine()) != null) {
			//se houver linhas, ele adiciona o conteúdo no StringBuilder
			response.append(inputLine);
		}
		
		//fecha o leitor de linhas
		in.close();
		
		//retorna o texto já traduzido
		return response.toString();
	}
}
