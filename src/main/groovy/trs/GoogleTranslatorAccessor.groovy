package trs

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.MediaType

import net.arnx.jsonic.JSON;

import org.glassfish.jersey.apache.connector.ApacheConnectorProvider
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties

/**
 * Google 翻訳に REST アクセスを実行するクラス。
 */
class GoogleTranslatorAccessor {
    
    /**コマンドラインオプション*/
    CommandLineOption opt
    
    /**
     * REST リクエストを実行し、 JSON の返却結果を Java オブジェクトに変換して返す。
     * <p>
     * Java オブジェクトは、 配列を ArraryList に、連想配列を LinkedHashMap に変換した形式になる。
     * 
     * @param text 翻訳するテキスト
     * @return Google 翻訳が返した JSON 文字列を Java オブジェクトに変換した結果。
     */
    def request(String text) {
        Client client = this.createRestClient()
        String responseText = this.getResponseText(client, text)
        JSON.decode(responseText)
    }
    
    /**
     * REST リクエストを実行して、結果を文字列で取得する。
     * 
     * @param client REST クライアント
     * @param text 翻訳するテキスト
     * @return サーバーが返した文字列
     */
    private String getResponseText(Client client, String text) {
        client.target("https://translate.google.co.jp")
              .path("translate_a/t")
              .queryParam("client", "t")
              .queryParam("ie", "UTF-8")
              .queryParam("oe", "UTF-8")
              .queryParam("sl", this.opt.fromLanguageCode)
              .queryParam("tl", this.opt.toLanguageCode)
              .queryParam("otf", "1")
              .queryParam("q", text)
              .request(MediaType.APPLICATION_JSON_TYPE)
              .get(String.class);
    }
    
    /**
     * REST Client を生成する。
     * 
     * @return REST Client
     */
    private Client createRestClient() {
        if (this.opt.proxyUri) {
            ClientConfig config = new ClientConfig()
            config.connectorProvider(new ApacheConnectorProvider())
            config.property(ClientProperties.PROXY_URI, this.opt.proxyUri)
            
            if (this.opt.proxyUsername && this.opt.proxyPassword) {
                config.property(ClientProperties.PROXY_USERNAME, this.opt.proxyUsername)
                config.property(ClientProperties.PROXY_PASSWORD, this.opt.proxyPassword)
            }
            
            ClientBuilder.newClient(config)
        } else {
            ClientBuilder.newClient()
        }
    }
}
