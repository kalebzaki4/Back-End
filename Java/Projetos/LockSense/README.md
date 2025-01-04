# LockSense

LockSense é um sistema de segurança para portas digitais desenvolvido em Java. Ele oferece uma solução completa para controle e verificação de acessos, composta por um site de cadastro, um gerador de códigos de segurança e um sistema de validação de códigos na porta.

## Funcionalidades

1. **Cadastro de Usuários**:
    - Um site intuitivo onde usuários podem criar contas e registrar informações necessárias para acessar as portas digitais.

2. **Gerador de Códigos de 6 Dígitos**:
    - Gera códigos únicos e temporários que garantem a segurança do acesso. Esses códigos são vinculados às contas registradas.

3. **Validação na Porta**:
    - Sistema embutido para verificação dos códigos de 6 dígitos gerados, garantindo acesso somente a usuários autorizados.

## Tecnologias Utilizadas

- **Backend**: Java para lógica de negócio e gerenciamento de códigos.
- **Frontend**: Site de cadastro desenvolvido com tecnologias modernas para interfaces intuitivas.
- **Banco de Dados**: Para armazenar informações dos usuários e códigos gerados.

## Como Funciona

1. O usuário acessa o site e cria uma conta.
2. Um código de 6 dígitos é gerado automaticamente pelo sistema após o registro.
3. Esse código é utilizado no dispositivo da porta para validar o acesso.
4. O sistema verifica o código no banco de dados e libera o acesso caso ele seja válido.

## Configuração do Projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/locksense.git
   ```

2. Configure o ambiente:
    - Certifique-se de ter o **Java JDK** instalado.
    - Configure o servidor web para hospedar o site de cadastro.

3. Configure o banco de dados:
    - Crie as tabelas necessárias para armazenar usuários e códigos.

4. Execute o sistema:
   ```bash
   java -jar LockSense.jar
   ```

## Contribuições

Contribuições são bem-vindas! Siga os passos abaixo:

1. Fork o repositório.
2. Crie um branch para sua feature:
   ```bash
   git checkout -b minha-feature
   ```
3. Commit suas alterações:
   ```bash
   git commit -m "Adicionei minha feature"
   ```
4. Envie para o branch principal:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

---

Crie um acesso seguro com o LockSense e proteja suas portas digitais com tecnologia de ponta!

