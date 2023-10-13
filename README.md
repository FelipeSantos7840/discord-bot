<table>
  <tr>
    <td>
      <h1>Lum Discord Bot </h1>
      <p>A Lum é um bot Java para Discord projetado para fornecer atualizações em tempo real de episódios e anúncios do mundo dos animes diretamente no seu servidor Discord.<p>
      <p>Adicione em seu Servidor: <a href="https://discord.com/api/oauth2/authorize?client_id=1160392363240341606&permissions=826781428752&scope=bot">Lum</a></p>
    </td>
    <td align="right">
      <img src="https://thumbs2.imgbox.com/ce/c7/GQCA2kyR_t.jpg" alt="Logo do AniBot">
    </td>
  </tr>
</table>

<hr>

## Sumário

- [Sobre](#sobre)
- [Uso](#uso)
- [Comandos](#comandos)
- [Licença](#licença)

### Sobre
A Lum é um projeto independente com o objetivo de fornecer atualizações em tempo real dos episódios semanais de animes, além de divulgar notícias sobre novos anúncios em canais predefinidos pelo administrador do servidor. Priorizamos a simplicidade e funcionalidade em nossa abordagem.

Nossos dados são atualmente coletados diretamente do feed RSS disponibilizado pela [LiveChart](www.livechart.me) e processados em nossa implementação. É importante observar que a [LiveChart](www.livechart.me) **NÃO** tem **envolvimento** ou **responsabilidade** na implementação da Lum. 👏

### Uso
A Lum está atualmente disponível publicamente para uso em qualquer servidor por meio deste [link](https://discord.com/api/oauth2/authorize?client_id=1160392363240341606&permissions=826781428752&scope=bot). Após a adição ao servidor, o administrador precisará utilizar dois [comandos](#comandos) para definir os canais onde as atualizações de episódios e anúncios serão divulgados. Esses canais podem ser os mesmos, se assim preferir.

Depois de configurado o bot retornará as mensagens no chat contendo os seguintes dados:
+ Atualização de Episódios:
  - Título
  - Número do Episódio
  - Data de Lançamento
  - Imagem
  - Link
+ Notícias:
  - Título
  - Data de Lançamento
  - Imagem
  - Link

### Comandos
+ **/setairingchat**:
  - Este comando destina-se exclusivamente aos usuários com permissões de Administrador no servidor e tem como finalidade informar à Lum qual chat do servidor receberá as atualizações dos episódios!
  - O chat em que o comando for executado será registrado para receber as mensagens através de seu ID.
+ **/setheadlinechat**:
  - Este comando destina-se exclusivamente aos usuários com permissões de Administrador no servidor e tem como finalidade informar à Lum qual chat do servidor receberá as notícias e anúncios!
  - O chat em que o comando for executado será registrado para receber as mensagens através de seu ID.
+ **/aboutlum**:
  - Este comando está liberado para todos os usuários do servidor e retorna uma mensagem comentando sobre a Lum e seus objetivos!

### Licença
Esse projeto foi desenvolvido utilizando a [licença MIT](LICENSE).

Importante informar que Dependencias utilizadas no projeto utilizam suas próprias licenças, refletindo diretamente em restrições na Adição, Alteração e Uso do código fonte da Lum se necessário.
