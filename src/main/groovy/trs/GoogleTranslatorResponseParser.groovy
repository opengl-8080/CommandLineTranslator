package trs

import net.arnx.jsonic.JSON

import org.apache.commons.lang3.SystemUtils
import org.apache.commons.lang3.text.StrBuilder

/**
 * Google 翻訳が返した情報を元に、画面に表示するための文字列を生成するパーサー。
 */
class GoogleTranslatorResponseParser {
    /**コマンドラインオプション*/
    CommandLineOption opt
    /**Google 翻訳の解析結果*/
    def response
    
    /**
     * 翻訳結果を解析して、画面表示用の文字列を取得する。
     * 
     * @return 画面表示用の文字列
     */
    String parse() {
        StrBuilder sb = new StrBuilder()
        
        this.appendFirstTranslateTextTo(sb)
        this.appendExtraTranslateTextTo(sb)
        
        (this.opt.debug ? 'translateText = ' : '') + sb
    }
    
    /**
     * 最初の翻訳結果を追加する。
     */
    private void appendFirstTranslateTextTo(StrBuilder sb) {
        def translates = this.response[0]
        
        translates.each { transtate ->
            if (transtate instanceof ArrayList) {
                def text = transtate[0]
                sb.appendSeparator(SystemUtils.LINE_SEPARATOR)
                  .append(text)
            }
        }

        if (!sb.isEmpty()) {
            sb.insert(0, '[翻訳]' + SystemUtils.LINE_SEPARATOR)
        }
    }
    
    /**
     * その他の翻訳結果を追加する。
     */
    private void appendExtraTranslateTextTo(StrBuilder sb) {
        def extra = this.response[1]

        if (extra instanceof ArrayList) {
            sb.appendNewLine()
            extra.each {
                sb.appendNewLine()
                def partsOfSpeech = it[0]
                def words = it[1]

                sb.append('[').append(partsOfSpeech).appendln(']')
                words.each { word ->
                    sb.append('- ').appendln(word)
                }
            }
        } else {
            sb.appendNewLine()
        }
    }
}
