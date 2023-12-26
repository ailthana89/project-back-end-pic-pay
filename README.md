# Desafio Back-end PicPay

### Descrição: 

Temos 2 tipos de usuários, os comuns e lojistas, ambos têm carteira com dinheiro e realizam transferências entre eles. Vamos nos atentar somente ao fluxo de transferência entre dois usuários.

### Requisitos do projeto:

- API RESTFul

Para ambos tipos de usuário: 
  - Nome Completo
  - CPF (CPF/CNPJ devem ser únicos no sistema)
  - E-mail (e-mails devem ser únicos no sistema)
  - Senha

### Funcionalidades:

- Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.

- Lojistas só recebem transferências, não enviam dinheiro para ninguém.

- Validar se o usuário tem saldo antes da transferência.

- Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, use este mock para simular (https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc).

- A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que envia.

- No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de email, sms) enviada por um serviço de terceiro e eventualmente este serviço pode estar indisponível/instável. Use este mock para simular o envio (https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6).

### Payload

>POST /transaction

~~~
{
    "value": 100.0,
    "payer": 4,
    "payee": 15
}
~~~

### Tecnologias:

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>

---
Link do projeto: https://github.com/PicPay/picpay-desafio-backend



