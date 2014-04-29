CommandLineTranslator
=====================

Google翻訳をコマンドラインから利用するプログラム。


#必要な環境
##OS
Windows7 でのみ動作確認済み。

##Java
1.7 以上

#使い方
##ビルド
```text:
>gradlew distZip
```

##解凍
`build\distributions\CommandLineTranslator.zip` を解凍。

##実行
```text:
>cd build\distributions\CommandLineTranslator\bin

>trs
English > Japanese : 
```

英語を入力して Enter で翻訳結果が表示される。


```text:
English > Japanese : Use the switch context button in the upper left corner of this screen to switch between your personal context (opengl-8080) and any organizations you are a member of.
[翻訳]
あなたの個人的なコンテキスト（ OpenGLベース8080 ）と自分がメンバーであるすべての組織との間で切り替えるには、この画面の左上隅にスイッチのコンテキストボタンを使用します。

English > Japanese : english
[翻訳]
英語

[名詞]
- 英語
- 英国人

[形容詞]
- 英語の
- 英国の

English > Japanese :
```

終了は `Ctrl + C` （Windows なら）。

```text:
English > Japanese : バッチ ジョブを終了しますか (Y/N)? y
```

##日本語→英語で翻訳
```text:
>trs -j
Japanese > English : Java（ジャバ）は、狭義ではオブジェクト指向プログラミング言語Javaであり、広義ではプログラミング言語Javaのプログラムの実行環境および開発環境をいう。
[翻訳]
Java ( Java ) is an object -oriented programming language Java in the narrow sense , refers to the development environment and the execution environment of the program of the Java programming language in a broad sense.

Japanese > English :
```

##その他
usage を参照。


```text:
>trs -h
usage: trs <option>
    --debug                 デバッグモードで実行します。
 -e,--e2j                   英語を日本語に翻訳します。デフォルト。
 -h,--help                  この usage を表示します。
 -j,--j2e                   日本語を英語に翻訳します。
 -p,--password <password>   プロキシ認証のパスワードを指定します。
    --proxy <proxy uri>     プロキシの URI
                            を指定します。（例）http://proxy.host.name:8080
 -u,--username <username>   プロキシ認証のユーザー名を指定します。
```
