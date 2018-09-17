## Instituto Federal do Ceará
Engenharia de Computação  
Programação Paralela e Distribuída  
2018.2  
Prof. Cidcley T. de Souza (cidcley@gmail.com)  

### Projeto de RMI
1) Objetivo: Implementar o Jogo de Tabuleiro PONG-HAU-KI em RMI.  
Obs.: Não deverá haver nenhum Socket na implementação.  
Esse jogo tem origem na China e se estendeu para alguns países da Ásia como Coréia. O objetivo do jogo é movimentar as peças pelas linhas até que se consiga bloquear o movimento das peças do adversário.

2) Funcionalidades Básicas

* Controle de turno, com definição de quem inicia a partida; 
* Movimentação de Peças; 
* Detecção de desistência; 
* Chat para comunicação durante toda a partida; 
* Reiniciar partida; 

### Critérios de Avaliação
Interface do Jogo - GUI (0-10);  
Interface de Operações (0-10);  
Implementação de Funcionalidades (0-10);  

**Trabalho Individual**  
**Data de Entrega:** 12/09 (nota cheia)  
Entrega até 17/08 (-2 pontos)  
Depois disso o trabalho será desconsiderado.  

### Observações:  
1. **TODOS** os trabalhos só serão aceitos se apresentados **pessoalmente** pelo aluno na sala de aula na data final de entrega ou, em casos excecionais, a combinar com o professor.  
2. **TODOS** os trabalhos só serão recebidos através do link até às 12h da data de entrega.  
3. Não serão aceitos trabalhos enviados de qualquer outra forma.  
4. Devem ser entregues **TODOS** os códigos.  
5. Deverá ser entregue, se a linguagem de programação permitir, um código executável (.jar, .exe, etc).  

### Comandos via terminal
> **Terminal 1**  
> rubnsbarbosa$ javac ServerService.java  
> rubnsbarbosa$ javac Server.java  
> rubnsbarbosa$ rmic ServerService  
> **Terminal 2**  
> rubnsbarbosa$ rmiregistry  
> **Terminal 1**  
> rubnsbarbosa$ java Server  

### Client View
![captura de tela 2018-09-15 as 14 58 01](https://user-images.githubusercontent.com/17646546/45589190-0e881680-b8f8-11e8-9cf1-2b5877eddc70.png)

