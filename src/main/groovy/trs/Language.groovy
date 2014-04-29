package trs

/**
 * 翻訳する言語
 */
enum Language {
    /**日本語*/
    JAPANESE('Japanese', 'ja'),
    /**英語*/
    ENGLISH('English', 'en');
    
    /**言語の英語表現（例：Japanese）*/
    final String name
    /**言語コード（例：ja）*/
    final String code
    
    private Language(String name, String code) {
        this.name = name
        this.code = code
    }
}
