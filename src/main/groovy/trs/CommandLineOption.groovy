package trs;

import groovy.transform.ToString

/**
 * コマンドラインで指定したオプション情報を保持するクラス。
 */
@ToString
public class CommandLineOption {
    /**プロキシURI*/
    String proxyUri
    /**プロキシ認証のユーザー名*/
    String proxyUsername
    /**プロキシ認証のパスワード*/
    String proxyPassword
    /**デバッグ出力の有効・無効*/
    boolean debug
    /**翻訳方法（日本語から英語か、英語から日本か）の種別*/
    TranslateType translateType
    
    /**
     * プロンプト文字列を取得する。
     * @return プロンプト文字列
     */
    String getPrompt() {
        "${this.translateType.from.name} > ${this.translateType.to.name} : "
    }
    
    /**
     * 翻訳元の言語コードを取得する。
     * @return 翻訳元の言語コード
     */
    String getFromLanguageCode() {
        this.translateType.from.code
    }
    
    /**
     * 翻訳後の言語コードを取得する。
     * @return 翻訳後の言語コード
     */
    String getToLanguageCode() {
        this.translateType.to.code
    }
    
    /**
     * コマンドライン引数を解析して、オプションオブジェクトを生成する。
     * 
     * @param args コマンドライン引数
     * @return 解析結果
     */
    static CommandLineOption parse(args) {
        def cli = new CliBuilder(usage: 'trs <option>')
        cli.with {
            e longOpt: 'e2j', '英語を日本語に翻訳します。デフォルト。'
            j longOpt: 'j2e', '日本語を英語に翻訳します。'
            h longOpt: 'help', 'この usage を表示します。'
            _ longOpt: 'proxy', args: 1, argName: 'proxy uri', 'プロキシの URI を指定します。（例）http://proxy.host.name:8080'
            u longOpt: 'username', args: 1, argName: 'username', 'プロキシ認証のユーザー名を指定します。'
            p longOpt: 'password', args: 1, argName: 'password', 'プロキシ認証のパスワードを指定します。'
            _ longOpt: 'debug', 'デバッグモードで実行します。'
        }
        
        def cmd = cli.parse(args)
        if (!cmd) return null
        
        if (cmd.h) {
            cli.usage()
            return null
        }
        
        CommandLineOption option = new CommandLineOption()
        option.with {
            proxyUri = cmd.proxy ?: null
            proxyUsername = cmd.u ?: null
            proxyPassword = cmd.p ?: null
            debug = cmd.debug
            
            if (cmd.e) {
                translateType = new TranslateType(from: Language.ENGLISH, to: Language.JAPANESE)
            } else if (cmd.j) {
                translateType = new TranslateType(from: Language.JAPANESE, to: Language.ENGLISH)
            } else {
                // default
                translateType = new TranslateType(from: Language.ENGLISH, to: Language.JAPANESE)
            }
        }
        
        return option
    }
}
