package cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import comum.*;

public class Cliente {

    static Conexao conexao;
    static Socket socket;

    public Cliente() {
        try {
            socket = new Socket("localhost", 9600);
        } // fase de conexao
        catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void print_client(){
        System.out.println("\n+" + "-".repeat(75) + "+");
        System.out.printf("| %14s " + "%20s" + "%15s |", "", "-- { Sistema Gerenciamento Educacional } --", "");
        System.out.println();
        System.out.println("+" + "-".repeat(75) + "+");
        System.out.println("| developed by: [Davi, Diogo, Kleyton, Marcelo]" + " ".repeat(29) + "|");
        System.out.println("+" + "-".repeat(75) + "+");
    }

    public void clear_console(boolean pr_cl){

        // TODO: Function to clear console

        if(pr_cl){
            print_client();
        }
    }

    public static void main(String args[]) {

        print_client();
        Scanner in = new Scanner(System.in);

        int principal_option = 0;
        do {
            System.out.println("\n]------------------------[ Gerenciador Educacional ]------------------------[");

            System.out.println("\nEscolha uma Opcao de Gerenciamento: ");
            System.out.println("  1 - Alunos\n  2 - Professores\n  3 - Cursos\n  4 - Financeiro\n  5 - Acessos\n  0 - exit");

            System.out.print("Digite: ");
            try{
                principal_option = Integer.parseInt(in.nextLine());
                if(principal_option < 1 || principal_option > 5){
                    break;
                }
            } catch (Exception error_input_main){
                principal_option = 0;
            }

            switch (principal_option){
                case 1:
                    System.out.println("\n" + "-".repeat(10) +"[ Gerenciamento de Alunos ]"+"-".repeat(10)+"\nQual acao deseja prosseguir: ");
                    System.out.println("  1 - Cadastrar\n  2 - Listar\n  3 - Pesquisar\n  4 - Alterar");
                    System.out.print("Digite: ");
                    int escolha_aluno = 0;

                    try{
                        escolha_aluno = Integer.parseInt(in.nextLine());
                        if(escolha_aluno < 1 || escolha_aluno > 4){
                            break;
                        }
                    } catch (Exception e){
                        break;
                    }

                    switch (escolha_aluno){

                        case 1: // Cadastrar
                            System.out.println("\n-----[ Cadastro de Aluno ]-----");
                            System.out.print("Nome Completo: ");
                            String op1 = in.nextLine();
                            System.out.print("Curso: ");
                            String op2 = in.nextLine();
                            System.out.print("Valor da Mensalidade: ");
                            String op3 = in.nextLine();
                            System.out.print("Status [ativo / inativo]: ");
                            String op4 = in.nextLine();

                            System.out.print("Confirma o Cadastro [s/n]: ");
                            String confirma_cadastro = in.nextLine();

                            if (confirma_cadastro.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Aluno NAO Cadastrado!");
                                break;
                            }

                            MsgReq requisicao_aluno = new MsgReq(op1, op2, op3, op4, principal_option);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno);

                            MsgResp resposta_aluno = (MsgResp) conexao.receive(socket);
                            System.out.println("\n[ OK ] - Aluno Cadastrado!");
                            break;

                        case 2: // Listar
                            System.out.println("\n-----[ Lista de Alunos Cadastros no Sistema ]-----");
                            MsgReq requisicao_aluno_2 = new MsgReq("", "", "", "", 12);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno_2);

                            MsgResp resposta_aluno_2 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_2.listar_aluno();

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 3: // Pesquisar
                            System.out.println("\n-----[ Pesquisa de Alunos Cadastrados ]-----");
                            System.out.println("Para realizar a pesquisa é necessário informar o valor e coluna. \nPor Exemplo: Pesquise por todos os alunos 'ativo', usando a coluna 'Status'");
                            System.out.println("É preciso infomar umas das colunas: ['Nome', 'Curso', 'Status']\n");

                            System.out.print("Valor: ");
                            String valor_escolha_aluno = in.nextLine();

                            System.out.print("Nome da Coluna: ");
                            String variable_escolha_aluno = in.nextLine();

                            MsgReq requisicao_aluno_3 = new MsgReq(variable_escolha_aluno, valor_escolha_aluno, "", "", 13);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno_3);

                            MsgResp resposta_aluno_3 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_3.listar_aluno_by(variable_escolha_aluno, valor_escolha_aluno);

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 4: // Alterar Valor
                            System.out.println("\n-----[ Alterar Dados de Alunos Cadastrados ]-----");
                            System.out.println("Para alterar/atualizar informaçoes de um aluno é necessário ter o ID.");
                            System.out.println("É preciso infomar umas das colunas: ['Nome', 'Curso', 'Valor', 'Status']\n");
                            System.out.println("Por exemplo: O aluno com o ID '14871' está ativo mas deixou de frequentar as aulas e passa a ser inativo>");

                            System.out.print("ID do Aluno: ");
                            String id_aluno_alt = in.nextLine();

                            System.out.print("Coluna: ");
                            String variable_aluno_alt = in.nextLine();

                            System.out.print("Atualizar por: ");
                            String valor_aluno_alt = in.nextLine();

                            System.out.print("Confirma Atualização [s/n]: ");
                            String confirma_atualizacao = in.nextLine();

                            if (confirma_atualizacao.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Aluno '"+id_aluno_alt+"' NAO Atualizado!");
                                break;
                            }

                            MsgReq requisicao_aluno_4 = new MsgReq(id_aluno_alt, variable_aluno_alt, valor_aluno_alt, "", 14);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno_4);

                            MsgResp resposta_aluno_4 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_4.alterar_aluno(id_aluno_alt, variable_aluno_alt, valor_aluno_alt);
                            System.out.println("\n[ OK ] - Aluno '"+id_aluno_alt+"' Atualizado!");
                            break;

                    } // Fim switch (escolha_aluno)
                    break;

                case 2: // Professores
                    System.out.println("\n" + "-".repeat(10) +"[ Gerenciamento de Professores ]"+"-".repeat(10)+"\nQual acao deseja prosseguir: ");
                    System.out.println("  1 - Cadastrar\n  2 - Listar\n  3 - Pesquisar\n  4 - Alterar");
                    System.out.print("Digite: ");
                    int escolha_professor = 0;

                    try{
                        escolha_professor = Integer.parseInt(in.nextLine());
                        if(escolha_professor < 1 || escolha_professor > 4){
                            break;
                        }
                    } catch (Exception e){
                        break;
                    }

                    switch (escolha_professor){
                        case 1: // Cadastro
                            System.out.println("\n-----[ Cadastro de Professor ]-----");

                            System.out.print("Nome Completo: ");
                            String nome_prof = in.nextLine();
                            System.out.print("Cursos administrado: ");
                            String cursos_prof = in.nextLine();
                            System.out.print("Salario: ");
                            String salario_prof = in.nextLine();
                            System.out.print("Status: ");
                            String stats_prof = in.nextLine();

                            System.out.print("Confirma o Cadastro [s/n]: ");
                            String confirma_cadastro_pf = in.nextLine();

                            if (confirma_cadastro_pf.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Professor NAO Cadastrado!");
                                break;
                            }

                            MsgReq requisicao_prof = new MsgReq(nome_prof, cursos_prof, salario_prof, stats_prof, principal_option);

                            new Cliente();
                            conexao.send(socket, requisicao_prof);

                            MsgResp resposta_prof = (MsgResp) conexao.receive(socket);
                            System.out.println("\n[ OK ] - Aluno Cadastrado!");
                            break;

                        case 2: // Listar
                            System.out.println("\n-----[ Lista de Professores Cadastros no Sistema ]-----");
                            MsgReq requisicao_prof_2 = new MsgReq("", "", "", "", 22);

                            new Cliente();
                            conexao.send(socket, requisicao_prof_2);

                            MsgResp resposta_prof_2 = (MsgResp) conexao.receive(socket);
                            resposta_prof_2.listar_professor();

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 3: // Pesquisar
                            System.out.println("\n-----[ Pesquisa de Professores Cadastrados ]-----");
                            System.out.println("Para realizar a pesquisa é necessário informar o valor e coluna. \nPor Exemplo: Pesquise por todos os professores 'ativo', usando a coluna 'Status'");
                            System.out.println("É preciso infomar umas das colunas: ['ID', 'Nome', 'Curso', 'Status']\n");

                            System.out.print("Valor: ");
                            String valor_escolha_prof = in.nextLine();
                            System.out.print("Nome da Coluna: ");
                            String variable_escolha_prof = in.nextLine();

                            MsgReq requisicao_aluno_3 = new MsgReq(variable_escolha_prof, valor_escolha_prof, "", "", 23);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno_3);

                            MsgResp resposta_aluno_3 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_3.listar_professor_by(variable_escolha_prof, valor_escolha_prof);

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 4: // Alterar Valor
                            System.out.println("\n-----[ Alterar Dados de Professores Cadastrados ]-----");
                            System.out.println("Para alterar/atualizar informaçoes de um professor é necessário ter o ID.");
                            System.out.println("É preciso infomar umas das colunas: ['Nome', 'Curso', 'Salario', 'Status']\n");
                            System.out.println("Por exemplo: O professor com o ID '54321' ganhou um aumento e seu salario passa a ser maior");

                            System.out.print("ID do Professor: ");
                            String id_prof_alt = in.nextLine();
                            System.out.print("Coluna: ");
                            String variable_prof_alt = in.nextLine();
                            System.out.print("Atualizar por: ");
                            String valor_prof_alt = in.nextLine();

                            System.out.print("Confirma Atualização [s/n]: ");
                            String confirma_atualizacao_pf = in.nextLine();

                            if (confirma_atualizacao_pf.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Aluno '"+id_prof_alt+"' NAO Atualizado!");
                                break;
                            }

                            MsgReq requisicao_aluno_4 = new MsgReq(id_prof_alt, variable_prof_alt, valor_prof_alt, "", 24);

                            new Cliente();
                            conexao.send(socket, requisicao_aluno_4);

                            MsgResp resposta_aluno_4 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_4.alterar_professor(id_prof_alt, variable_prof_alt, valor_prof_alt);
                            System.out.println("\n[ OK ] - Aluno '"+id_prof_alt+"' Atualizado!");
                            break;

                    }
                    break;
                case 3: // Cursos
                    System.out.println("\n" + "-".repeat(10) +"[ Gerenciamento de Cursos ]"+"-".repeat(10)+"\nQual acao deseja prosseguir: ");
                    System.out.println("  1 - Cadastrar\n  2 - Listar\n  3 - Pesquisar\n  4 - Alterar");
                    System.out.print("Digite: ");
                    int escolha_curso = 0;

                    try{
                        escolha_curso = Integer.parseInt(in.nextLine());
                        if(escolha_curso < 1 || escolha_curso > 4){
                            break;
                        }
                    } catch (Exception e){
                        break;
                    }

                    switch (escolha_curso){
                        case 1: // Cadastro
                            System.out.println("\n-----[ Cadastro de Curso ]-----");

                            System.out.print("Nome do Curso: ");
                            String nome_curso = in.nextLine();
                            System.out.print("Carga Horaria: ");
                            String carga_horaria = in.nextLine();
                            System.out.print("Modalidade: ");
                            String modalidade = in.nextLine();
                            System.out.print("Status: ");
                            String stats_curso = in.nextLine();

                            System.out.print("Confirma o Cadastro [s/n]: ");
                            String confirma_cadastro_cr = in.nextLine();

                            if (confirma_cadastro_cr.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Curso NAO Cadastrado!");
                                break;
                            }

                            MsgReq requisicao_curso = new MsgReq(nome_curso, carga_horaria, modalidade, stats_curso, principal_option);

                            new Cliente();
                            conexao.send(socket, requisicao_curso);

                            MsgResp resposta_curso = (MsgResp) conexao.receive(socket);
                            System.out.println("\n[ OK ] - Curso Cadastrado!");
                            break;

                        case 2: // Listar
                            System.out.println("\n-----[ Lista de Cursos Cadastros no Sistema ]-----");
                            MsgReq requisicao_curso_2 = new MsgReq("", "", "", "", 32);

                            new Cliente();
                            conexao.send(socket, requisicao_curso_2);

                            MsgResp resposta_curso_2 = (MsgResp) conexao.receive(socket);
                            resposta_curso_2.listar_cursos();

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 3: // Pesquisar
                            System.out.println("\n-----[ Pesquisa de Cursos Cadastrados ]-----");
                            System.out.println("Para realizar a pesquisa é necessário informar o valor e coluna. \nPor Exemplo: Pesquise por todos os cursos 'AED', usando a coluna 'Modalidade'");
                            System.out.println("É preciso infomar umas das colunas: ['ID', 'Nome', 'Modalidade', 'Status']\n");

                            System.out.print("Valor: ");
                            String valor_escolha_curso = in.nextLine();
                            System.out.print("Nome da Coluna: ");
                            String variable_escolha_curso = in.nextLine();

                            MsgReq requisicao_curso_3 = new MsgReq(variable_escolha_curso, valor_escolha_curso, "", "", 33);

                            new Cliente();
                            conexao.send(socket, requisicao_curso_3);

                            MsgResp resposta_aluno_3 = (MsgResp) conexao.receive(socket);
                            resposta_aluno_3.listar_cursos_by(variable_escolha_curso, valor_escolha_curso);

                            System.out.print("Enter para continuar. ");
                            in.nextLine();
                            break;

                        case 4: // Alterar Valor
                            System.out.println("\n-----[ Alterar Dados de Cursos Cadastrados ]-----");
                            System.out.println("Para alterar/atualizar informaçoes de um curso é necessário ter o ID.");
                            System.out.println("É preciso infomar umas das colunas: ['Nome', 'Carga', 'Modalidade', 'Status']\n");
                            System.out.println("Por exemplo: O curso com o ID '15975' deixou de ser ativo e passa a ser ativo");

                            System.out.print("ID do Curso: ");
                            String id_curso_alt = in.nextLine();
                            System.out.print("Coluna: ");
                            String variable_curso_alt = in.nextLine();
                            System.out.print("Atualizar por: ");
                            String valor_curso_alt = in.nextLine();

                            System.out.print("Confirma Atualização [s/n]: ");
                            String confirma_atualizacao_cr = in.nextLine();

                            if (confirma_atualizacao_cr.toLowerCase().equalsIgnoreCase("s")){}else{
                                System.out.println("\n[ Falha ] - Curso '"+id_curso_alt+"' NAO Atualizado!");
                                break;
                            }

                            MsgReq requisicao_curso_4 = new MsgReq(id_curso_alt, variable_curso_alt, valor_curso_alt, "", 34);

                            new Cliente();
                            conexao.send(socket, requisicao_curso_4);

                            MsgResp resposta_curso_4 = (MsgResp) conexao.receive(socket);
                            resposta_curso_4.alterar_professor(id_curso_alt, variable_curso_alt, valor_curso_alt);
                            System.out.println("\n[ OK ] - Curso '"+id_curso_alt+"' Atualizado!");
                            break;

                    }
                    break;

            }

        } while (principal_option != 0);


        try {
            socket.close();                               // fase de desconex�o
        } catch (IOException e) {
            System.out.println("N�o encerrou a conex�o corretamente" + e.getMessage());
        }
    }
}