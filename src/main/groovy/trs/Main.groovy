package trs

class Main {
    static void main(args) {
        CommandLineOption option = CommandLineOption.parse(args)
        if (!option) return
        
        print option.prompt
        
        System.in.eachLine {text ->
            def response = new GoogleTranslatorAccessor(opt: option).request(text)
            def parsedText = new GoogleTranslatorResponseParser(opt: option, response: response).parse()
            
            println parsedText
            print option.prompt
        }
    }
}
