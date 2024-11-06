<a id="readme-top"></a>
# Gestão De Hoteis 🏨:
Este sistema utiliza uma implementação de banco de dados para gerenciar eficientemente as operações de hotéis. Ele é estruturado com tabelas dedicadas para Hotéis, Quartos e Reservas.

## 🔧 Principais Funcionalidades: 
 - **Registrar:** Adiciona novos registros de hotéis, quartos e reservas ao banco de dados.
 - **Remover:** Permite a exclusão de hotéis, quartos ou reservas conforme necessário, garantindo que os dados permaneçam atualizados.
 - **Listar:** Exibe os Nomes dos hotéis, os quartos disponíveis e reservados, além das reservas ativas, facilitando a visualização e a gestão das informações.

## Validações de dados ✅:
- **Geral:** Garante que os inputs não sejam enviados vazios e realiza uma limpeza nos espaços anteriores e posteriores com o método .trim()
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
| **codigo**     |   VARCHAR(3)          | Chave Primária,Possui logica para gerar codigo alfaNumerico de três digitos aleatorios  |
| **numero**     |  VARCHAR(5)   |  Numero do quarto, chave única                      | 
| **Reservado**  |  ENUM('DISPONIVEL', 'RESERVADO') DEFAULT 'DISPONIVEL'        | Status do quarto, que pode ser 'DISPONIVEL' ou 'RESERVADO'            |
| **hotel_nome** |  VARCHAR(255)   |   Chave Estrangeira e única, Delete Cascade                    |

### Tabela: Reservas
| Colunas  |  Tipos De Dados  |  Descrição             |
|----------| ---------------- |------------------------|
| **codigo**     |  VARCHAR(3)      |  Chave Primária,Possui logica para gerar codigo alfaNumerico de três digitos aleatorios |
| **hotel_nome** |  VARCHAR(255)    |  Chave Estrangeira e única, Delete Cascade                      |
| **quarto_Num** |  VARCHAR(5)     |  Chave Estrangeira e única, Delete Cascade                      |

## 💻 Telas
### Menus:
   #### Menu Principal:
   ![Menu](https://github.com/user-attachments/assets/6ada155f-47d2-49ad-b41d-6160db3496f3)
   #### Menu Hotel:
   ![MenuHotel](https://github.com/user-attachments/assets/ab2df1ec-207c-442e-b445-6c05d3fc288a)
   
   **Obs:** Os menus de Quarto e Reserva são iguais a esse.
### Hotel 🚪:
   #### Adicionando: 
   ![AddHotel](https://github.com/user-attachments/assets/3f35f3af-dec8-40e7-b473-494018f0d005)
   #### Removendo:
   ![RemoveHotel](https://github.com/user-attachments/assets/6f3a21ac-7d39-4b78-94e0-29c3254a3351)
   #### Listando:
   ![ListHotel](https://github.com/user-attachments/assets/8f6bd535-ba24-4908-8e7e-1c5d88704155)

### Quarto 🔑:
   #### Adicionando:
   **Informando numero do quarto.**
   
   ![addQuarto](https://github.com/user-attachments/assets/d3b16b6b-7e71-459a-a731-bcf416dd73aa)
   
   **Selecionando o Hotel que deseja atribuir aquele quarto.**
   
   ![addQuarto2](https://github.com/user-attachments/assets/1e0048fb-4544-4122-9b10-abef8665cdf4)
   #### Removendo: 
   ![RemoveQuarto](https://github.com/user-attachments/assets/82367a00-2871-4c74-9399-a302a1b37dbd)
   #### Listando:
   ![ListaQuarto](https://github.com/user-attachments/assets/cb23cccb-5232-4045-a800-5a294c01a705)


### 🛎️ Reserva:
   #### Adicionando: 
   **Selecionando o Hotel**
   
   ![addReserva](https://github.com/user-attachments/assets/49df026d-3d72-48fa-863a-f3b722cee14b)

   **Selecionando o Quarto.**
   
   ![addReserva2](https://github.com/user-attachments/assets/8ebffd6f-0780-4916-a9a1-edf24dd06e70)
   #### Removendo:
   ![RemoverReserva](https://github.com/user-attachments/assets/6833e18d-b53a-486d-9895-5af4baf332b2)
   #### Listando:
   ![ListarReservas](https://github.com/user-attachments/assets/7ba70ce1-587b-4538-bf1c-a6dfbd4e7b5e)

### Rodando Codigo:
https://github.com/user-attachments/assets/33bb18b9-61d3-4410-b578-daf3254c73c1

## Mudança de Conexão Importante ⚠️:
![Conexão](https://github.com/user-attachments/assets/a363d006-5b52-4269-9357-1258e12620ea)

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
