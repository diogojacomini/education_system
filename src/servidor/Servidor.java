package servidor;

import comum.*;
import java.net.*;

public class Servidor {

    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao conexao;
    static String msg;

    public Servidor() {

        try {
            serversocket = new ServerSocket(9600);
            System.out.println("\n+" + "-".repeat(75) + "+");
            System.out.printf("| %21s " + "%20s" + "%22s |", "", "-- { Sistema Distribuido } --", "");
            System.out.println("\n+" + "-".repeat(75) + "+");
        } catch (Exception e) {
            System.out.println("Falha ao Criar Server Socket...");
        }
    }

    public static void main(String args[]) {
        MsgReq requisicao = new MsgReq();
        MsgResp resposta = new MsgResp();

        do {
            new Servidor();
            if (connect()) {

                System.out.println("\n]------------------------[ Requisicao ]------------------------[");
                requisicao = (MsgReq) conexao.receive(client_socket);

                System.out.println("Inputs: ");
                System.out.println("v1: " + requisicao.getVar1());
                System.out.println("v2: " + requisicao.getVar2());
                System.out.println("v3: " + requisicao.getVar3());
                System.out.println("v4: " + requisicao.getVar4());
                System.out.println("Funcionalidade: " + requisicao.getFuncionalidade());

                System.out.println("\nResultado da Requisicao: \n******************************************************");
                switch (requisicao.getFuncionalidade()){
                    // ------- Cadastros ------- \\
                    case 1: // Aluno
                        resposta.setResultado(new MsgReq(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3(), requisicao.getVar4(), requisicao.getFuncionalidade()));
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Aluno Cadastrado!\n");
                        break;

                    case 2: // Professor
                        resposta.setResultadoProf(new MsgReq(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3(), requisicao.getVar4(), requisicao.getFuncionalidade()));
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Professor Cadastrado!\n");
                        break;

                    case 3: // Curso
                        resposta.setResultadoCurso(new MsgReq(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3(), requisicao.getVar4(), requisicao.getFuncionalidade()));
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Curso Cadastrado!\n");
                        break;

                    case 4: // Despesa
                        resposta.setResultadoDespesa(new MsgReq(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3(), requisicao.getVar4(), requisicao.getFuncionalidade()));
                        resposta.setStatus(0);
                        System.out.println("/n[ OK ] - Despesa Cadastrada!\n");
                        break;

                    // ----- Acoes Aluno ----- \\
                    case 12: // Listar alunos
                        resposta.listar_aluno();
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Lista de Alunos.");
                        break;

                    case 13: // Pesquisa por alunos
                        System.out.println("Pesquisa feita por Chave(v1): '" + requisicao.getVar1() + "' Valor(v2): '" + requisicao.getVar2() + "'");
                        resposta.listar_aluno_by(requisicao.getVar1(), requisicao.getVar2());
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Pesquisa Realizada!");
                        break;

                    case 14: // Alteracao de dados de aluno
                        System.out.println("Alteracao do ID(v1): '" + requisicao.getVar1() + "' na coluna(v2): '" + requisicao.getVar2() + "' novo valor(v3): " + requisicao.getVar3());
                        resposta.alterar_aluno(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3());
                        resposta.setStatus(0);
                        resposta.listar_aluno_by("ID", requisicao.getVar1());
                        System.out.println("\n[ OK ] - Aluno '"+requisicao.getVar1()+"' Atualizado!");
                        break;

                    // ----- Acoes Professor ----- \\
                    case 22: // Listar todos os professores cadastrados
                        resposta.listar_professor();
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Lista de Professores.");
                        break;

                    case 23: // Pesquisa Professores
                        System.out.println("Pesquisa feita por Chave(v1): '" + requisicao.getVar1() + "' Valor(v2): '" + requisicao.getVar2() + "'");
                        resposta.listar_professor_by(requisicao.getVar1(), requisicao.getVar2());
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Pesquisa Realizada!");
                        break;

                    case 24: // Alteracao de valor professor
                        System.out.println("Alteracao do ID(v1): '" + requisicao.getVar1() + "' na coluna(v2): '" + requisicao.getVar2() + "' novo valor(v3): " + requisicao.getVar3());
                        resposta.alterar_professor(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3());
                        resposta.setStatus(0);
                        resposta.listar_professor_by("ID", requisicao.getVar1());
                        System.out.println("\n[ OK ] - Professor '"+requisicao.getVar1()+"' Atualizado!");
                        break;

                    // ----- Acoes Curso ----- \\
                    case 32: // Listar cursos cadastrados
                        resposta.listar_cursos();
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Lista de Cursos.");
                        break;

                    case 33: // Pesquisar
                        System.out.println("Pesquisa feita por Chave(v1): '" + requisicao.getVar1() + "' Valor(v2): '" + requisicao.getVar2() + "'");
                        resposta.listar_cursos_by(requisicao.getVar1(), requisicao.getVar2());
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Pesquisa Realizada!");
                        break;

                    case 34: // Alteracao
                        System.out.println("Alteracao do ID(v1): '" + requisicao.getVar1() + "' na coluna(v2): '" + requisicao.getVar2() + "' novo valor(v3): " + requisicao.getVar3());
                        resposta.alterar_cursos(requisicao.getVar1(), requisicao.getVar2(), requisicao.getVar3());
                        resposta.setStatus(0);
                        resposta.listar_cursos_by("ID", requisicao.getVar1());
                        System.out.println("\n[ OK ] - Curso '"+requisicao.getVar1()+"' Atualizado!");
                        break;

                    // ----- Acoes Despesas ----- \\
                    case 42: // Listar Despesas
                        resposta.setStatus(0);
                        resposta.listar_despesas();
                        System.out.println("\n[ OK ] - Lista de Despesas.");
                        break;

                    case 43: // Pesquisar Despesas
                        System.out.println("Pesquisa feita por Chave(v1): '" + requisicao.getVar1() + "' Valor(v2): '" + requisicao.getVar2() + "'");
                        resposta.listar_despesas_by(requisicao.getVar1(), requisicao.getVar2());
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Pesquisa Realizada!");
                        break;

                    // ----- Acoes Financeiro ----- \\
                    case 51: // Lucro Atual
                        resposta.consulta_lucro();
                        resposta.setStatus(0);
                        System.out.println("\n[ OK ] - Total Lucro.");
                        break;

                    case 52: // Custos Professores
                        resposta.setStatus(0);
                        resposta.consulta_custo_professores();
                        System.out.println("\n[ OK ] - Total Gastos Professores.");
                        break;

                    case 53: // Receita Total
                        resposta.setStatus(0);
                        resposta.consulta_receita();
                        System.out.println("\n[ OK ] - Total de Receita.");
                        break;

                    case 54: // Total com Despesas
                        resposta.setStatus(0);
                        resposta.consulta_despesas();
                        System.out.println("\n[ OK ] - Total de Despesas.");
                        break;

                }

                if (resposta.getStatus() == 0){
                    System.out.println("\n]------------------------[ Requisicao feita com Sucesso! ]------------------------[\n");
                } else{
                    System.out.println("ERROR: falha na requisicao!!");
                }

                conexao.send(client_socket, resposta);

                try {
                    client_socket.close();
                    serversocket.close();
                }
                catch (Exception e) {
                    System.out.println("Nao encerrou a conexao corretamente" + e.getMessage());
                }
            }

        }while(true);
    }

    static boolean connect() {
        boolean ret;
        try {
            client_socket = serversocket.accept();              // fase de conexão
            ret = true;
        } catch (Exception e) {
            System.out.println("Nao fez conexao" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
