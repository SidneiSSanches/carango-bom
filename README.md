# Carango-BOM

Projeto Carango BOM [e um sistema de gerenciamento de veiculos, no qual os adminsitradores de uma concessionaria cadastram veiculos para venda. os clientes, entao, conseguem ver todos os veiculos disponiveis e os detalhes de cada um.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.



### 📋 Pré-requisitos

De que coisas você precisa para instalar o software e como instalá-lo?

```
Dar exemplos
```

### 🔧 Instalação

Uma série de exemplos passo-a-passo que informam o que você deve executar para ter um ambiente de desenvolvimento em execução.

Diga como essa etapa será:

```
Dar exemplos
```

E repita:

```
Até finalizar
```

Termine com um exemplo de como obter dados do sistema ou como usá-los para uma pequena demonstração.

## ⚙️ Executando os testes

Para testes de funcionalidades dos endpoints acesse:

http://localhost:8080/swagger-ui/index.html#/

### 🔩 Analise os testes de ponta a ponta

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```

### ⌨️ E testes de estilo de codificação

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```

## Processo de autenticação para /authenticate Endpoint

Este projeto usa autenticação baseada em JWT (JSON Web Token) para proteger endpoints de API. Abaixo está uma descrição do processo de autenticação.

* 1.Processo de Autenticação 
O usuário deve fazer login fornecendo suas credenciais (nome de usuário e senha). Se as credenciais forem válidas, um token JWT será gerado e retornado ao usuário.


Exemplo de Requisição:
   {
     "username": "usuario",
     "password": "senha"
   }

Exemplo de Requisição para Dev:
   {
     "username": "usuario",
     "password": "usuario@senha"
   }


Exemplo de Resposta:
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."

* 2.Acessando Endpoints Protegido.

Para acessar endpoints protegidos, o usuário deve incluir o token JWT no cabeçalho da requisição.

Exemplo de Cabeçalho:

   Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

## 📦 Implantação

Adicione notas adicionais sobre como implantar isso em um sistema ativo

## 🛠️ Construído com

Mencione as ferramentas que você usou para criar seu projeto

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - O framework web usado
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [ROME](https://rometools.github.io/rome/) - Usada para gerar RSS

## 🖇️ Colaborando

Por favor, leia o [COLABORACAO.md](https://gist.github.com/usuario/linkParaInfoSobreContribuicoes) para obter detalhes sobre o nosso código de conduta e o processo para nos enviar pedidos de solicitação.

## 📌 Versão

Nós usamos [SemVer](http://semver.org/) para controle de versão. Para as versões disponíveis, observe as [tags neste repositório](https://github.com/suas/tags/do/projeto). 

## ✒️ Autores

Mencione todos aqueles que ajudaram a levantar o projeto desde o seu início

* **Um desenvolvedor** - *Trabalho Inicial* - [umdesenvolvedor](https://github.com/linkParaPerfil)
* **Fulano De Tal** - *Documentação* - [fulanodetal](https://github.com/linkParaPerfil)

Você também pode ver a lista de todos os [colaboradores](https://github.com/usuario/projeto/colaboradores) que participaram deste projeto.

## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/usuario/projeto/licenca) para detalhes.

## 🎁 Expressões de gratidão

* Conte a outras pessoas sobre este projeto 📢;
* Convide alguém da equipe para uma cerveja 🍺;
* Um agradecimento publicamente 🫂;
* etc.


---
⌨️ com ❤️ por [Grupo de Trabalho]() 😊