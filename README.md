<a id="readme-top"></a>
# Gestão De Hoteis 🏨:
Este sistema utiliza uma implementação de banco de dados para gerenciar eficientemente as operações de hotéis. Ele é estruturado com tabelas dedicadas para Hotéis, Quartos e Reservas.

## 🔧 Principais Funcionalidades: 
 - **Registrar:** Adiciona novos registros de hotéis, quartos e reservas ao banco de dados.
 - **Remover:** Permite a exclusão de hotéis, quartos ou reservas conforme necessário, garantindo que os dados permaneçam atualizados.
 - **Listar:** Exibe os Nomes dos hotéis, os quartos disponíveis e reservados, além das reservas ativas, facilitando a visualização e a gestão das informações.

## Validações de dados ✅:
- **Registrar Hotel** O sistema notifica o usuário caso ele tente adicionar um hotel com um nome já cadastrado no banco de dados.
- **Remover Hotel:** A interface de remoção só será exibida se existir pelo menos um hotel. Se houver uma tentativa de exclusão, o usuário será notificado de que todas as reservas e quartos associados a esse hotel serão   
    removidos.
- **Registrar Quarto:**  É possível adicionar quartos apenas se existir pelo menos um hotel. Quartos com o mesmo nome podem ser adicionados, mas devem pertencer a hotéis diferentes.
- **Remover Quarto:** A remoção de quartos só será permitida se não houver reservas associadas a eles, ou seja, apenas os quartos com status DISPONÍVEL podem ser removidos.
- **Registrar Reserva:** Garante que haja apenas uma reserva por quarto e a interface exibirá apenas os quartos disponíveis.

## 🗄️ Estrutura do Banco de Dados:
###  Tabela: Hotéis
| Colunas  |  Tipos De Dados  |  Descrição             |
|----------| ---------------- |------------------------|
| **Id**   |       INT        |  Chave Primária, possui auto incremento             |
| **Nome** |  VARCHAR(255)    |  Nome do hotel, chave única                   |

### Tabela: Quartos
| Colunas  |  Tipos De Dados  |  Descrição             |
|----------| ---------------- |------------------------|
| **id**         |       INT        | Chave Primária, possui auto incremento  |
| **numero**     |  VARCHAR(10)   |  Numero do quarto, chave única                      | 
| **Reservado**  |  ENUM('DISPONIVEL', 'RESERVADO') DEFAULT 'DISPONIVEL'        | Status do quarto, que pode ser 'DISPONIVEL' ou 'RESERVADO'            |
| **hotel_nome** |  VARCHAR(255)   |   Chave Estrangeira e única, Delete Cascade                    |

### Tabela: Reservas
| Colunas  |  Tipos De Dados  |  Descrição             |
|----------| ---------------- |------------------------|
| **codigo**     |  VARCHAR(3)      |  Chave Primária,Possui logica para gerar codigo alfaNumerico de três digitos aleatoria |
| **hotel_nome** |  VARCHAR(255)    |  Chave Estrangeira e única, Delete Cascade                      |
| **quarto_Num** |  VARCHAR(10)     |  Chave Estrangeira e única, Delete Cascade                      |

## 💻 Telas 
### Adicionar:
![Add](https://github.com/user-attachments/assets/7e7179ff-618f-4690-a5b9-cce4deb17398)

### Filtro Dia da Semana:
![DiaSemana](https://github.com/user-attachments/assets/96168368-9150-4f59-9d09-fc0af5e3593c)


### Filtro de Turno:
![TurnoFoto](https://github.com/user-attachments/assets/b97a26dc-4ba6-4cd0-9fda-ff8f1d0926ee)

### Adicionando Alimentos:
![PratoPricipal](https://github.com/user-attachments/assets/9068c204-f885-46f4-9cc8-89bb484af716)

### Troca de Valores: 
![Troca](https://github.com/user-attachments/assets/ba29632d-10ab-41e9-a331-395e5b04769a)

### Rodando Codigo:
https://github.com/user-attachments/assets/33bb18b9-61d3-4410-b578-daf3254c73c1

## Mudança de Conexão Importante ⚠️:

```bash
9 private static final String url = "jdbc:mysql://localhost?verifyServerCertificate=false&useSSL=true";
10 private static final String usuario = "root";
11 private static final String senha = "root";

```
### Caso você não deseje criar uma conexão com o banco de dados na porta 3306 com o usuario e senha root vai aqui alguns avisos: 
**Linha 9:** A variável url se conecta ao banco de dados MySQL na porta padrão 3306. Se você precisar usar outra porta, deve incluir isso na URL. EX: jdbc:mysql://localhost:3307.

**Linha 10:** A variável usuario define o nome do usuário que está se conectando ao banco de dados. O padrão é root, mas você pode mudá-lo para outro usuário com permissões  
adequadas.

**Linha 11:** A variável senha especifica a senha do usuário definido na linha 10. Assim como o usuário, você deve alterar para a senha correta conforme configurada no seu banco de dados.

## :octocat: Faça o clone do projeto

```bash
# Clone este repositório
$ gh repo clone Adriano047/GestaoHoteis

# Acesse a pasta do projeto no terminal/cmd
$ cd me

```


## 👨‍🔧 Sobre mim
"Conecte-se comigo no LinkedIn para explorar minha trajetória profissional e colaborar em projetos incríveis."
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://www.linkedin.com/in/cardosodev047/"><img src="https://media.licdn.com/dms/image/v2/D4D03AQFRff9YjluTHQ/profile-displayphoto-shrink_400_400/profile-displayphoto-shrink_400_400/0/1713879990636?e=2147483647&v=beta&t=AIThEkfC267uJ_bVz5bpXdPbuvQlDzdWdeb4JgeSkxQ" width="100px;" alt="Kent C. Dodds"/><br /><sub><b>Adriano Cardoso Santos</b></sub></a><br />
    </tr>
  </tbody>
</table>

<p align="right">(<a href="#readme-top">Voltar ao topo</a>)</p>





