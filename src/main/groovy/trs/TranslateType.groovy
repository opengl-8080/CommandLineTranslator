package trs

import groovy.transform.ToString;

/**
 * 翻訳方法の種別。
 * <p>
 * どの言語を、何の言語に変換するかの情報を持つ。
 */
@ToString
class TranslateType {
    /**変換元の言語*/
    Language from
    /**変換後の言語*/
    Language to
}
