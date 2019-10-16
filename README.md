# SharedPrefApp
App Android Didático: Utilizar Shared Preferences para persistir o login do usuário.

<table>
<tr align=center>
<td><img src="https://github.com/machadowma/SharedPrefApp/blob/master/screen_capture_1.png" align="left" height="360" width="180" ></td>
<td><img src="https://github.com/machadowma/SharedPrefApp/blob/master/screen_capture_2.png" align="left" height="360" width="180" ></td>
</tr>
<tr align=center>
<td>Figura 1</td>
<td>Figura 2 </td>
</tr>
</table>

Este aplicativo possui uma tabela em SQLite para cadastro de usuário. Após realizar o cadastro, um usuário pode se autenticar e seu login ficará armazenado em SharedPreferences para visualização em outras activities do aplicativo.

# API SharedPreferences

Se você tem uma coleção relativamente pequena de valores-chave que gostaria de salvar, use a API SharedPreferences.

## Utilizando SharedPreferences

Use *getSharedPreferences()* se precisar de vários arquivos de preferências compartilhadas identificados por nome, que você especifica com o primeiro parâmetro. Você pode chamar isso de qualquer contexto no seu aplicativo.

Por exemplo, o código a seguir acessa o arquivo de preferências compartilhadas identificado por *"meuappsharedpref"* e o abre usando o modo privado para que o arquivo seja acessível apenas pelo seu aplicativo:

```
SharedPreferences sharedPref = getSharedPreferences("meuappsharedpref", MODE_PRIVATE);
```

Ao nomear seus arquivos de preferências compartilhadas, você deve usar um nome que seja identificável exclusivamente para o seu aplicativo. Uma maneira fácil de fazer isso é prefixar o nome do arquivo com o ID do aplicativo.

## Escrevendo em SharedPreferences

Para gravar em um arquivo de preferências compartilhadas, crie um *SharedPreferences.Editor* chamando *edit ()* em sua *SharedPreferences*:
```
SharedPreferences sharedPref = getSharedPreferences("meuappsharedpref", MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPref.edit();
editor.putString( "login", editTextLogin.getText().toString() );
editor.commit();
```
Para gravar a alteração na memória e no disco, utilize *apply ()* ou *commit ()*. Usar *apply ()* altera o objeto *SharedPreferences* na memória imediatamente, mas grava as atualizações no disco de forma assíncrona. Como alternativa, você pode usar o *commit ()* para gravar os dados no disco de forma síncrona. Mas como *commit ()* é síncrono, evite chamá-lo a partir do thread principal, pois isso pode pausar a renderização da interface do usuário.

## Lendo em SharedPreferences
Para recuperar valores de um arquivo de preferências compartilhadas, chame métodos como *getInt ()* e *getString ()*, fornecendo a chave para o valor desejado e, opcionalmente, um valor padrão para retornar se a chave não estiver presente. Por exemplo:
```
SharedPreferences sharedPref = getSharedPreferences("meuappsharedpref", MODE_PRIVATE);
String login = sharedPref.getString("login","");
```

## Limpando ou excluindo dados em SharedPreferences
Use *remove ("key_name")* para excluir um valor específico e *clear ()* para remover todos os dados:
```
SharedPreferences sharedPref = getSharedPreferences("meuappsharedpref", MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPref.edit();
editor.clear();
editor.commit();
```
Em um sistema de login, além de limplar os dados de *SharedPreferences* você pode finalizar as *Activities* ativas e abrir novamente a tela de login:
```
Intent intent = new Intent(this,LoginActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
startActivity(intent);
```

# Referências
* https://developer.android.com/training/data-storage/shared-preferences#java
* https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
* https://medium.com/android-dev-br/sharedpreferences-d34768fcda45
* https://www.alura.com.br/artigos/salvando-informacoes-com-o-shared-preferences

# License

MIT License

Copyright (c) 2019 machadowma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
