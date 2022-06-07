package servidor;

import comum.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {

    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao conexao;
    static String msg;
    // static MsgResp resposta;

    public Servidor() {

        try {
            serversocket = new ServerSocket(9600);
            System.out.println("Sistema distribuida no ar ...");
        } catch (Exception e) {
            System.out.println("Nao criei o server socket...");
        }
    }

    public static void main(String args[]) {
        //Requisicao_main escolha_main = new Requisicao_main();
        MsgReq requisicao_aluno = new MsgReq();
        MsgResp resposta_aluno = new MsgResp();
        //MsgReqProf requisicao_prof = new MsgReqProf();
        //MsgRespProf resposta_prof = new MsgRespProf();


        do {
            new Servidor();
            if (connect()) {
                requisicao_aluno = (MsgReq) conexao.receive(client_socket);
                System.out.println("v1: "+requisicao_aluno.getVar1());
                System.out.println("v2: "+requisicao_aluno.getVar2());
                System.out.println("v3: "+requisicao_aluno.getVar3());
                System.out.println("v4: "+requisicao_aluno.getVar4());

                System.out.println("\nResultado: \n");
                switch (requisicao_aluno.getFuncionalidade()){
                    case 1:
                        resposta_aluno.setResultado(new MsgReq(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3(), requisicao_aluno.getVar4(), requisicao_aluno.getFuncionalidade()));
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_aluno();
                        break;

                    case 2:
                        resposta_aluno.setResultadoProf(new MsgReq(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3(), requisicao_aluno.getVar4(), requisicao_aluno.getFuncionalidade()));
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_professor();
                        break;

                    case 3:
                        resposta_aluno.setResultadoCurso(new MsgReq(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3(), requisicao_aluno.getVar4(), requisicao_aluno.getFuncionalidade()));
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_cursos();
                        break;

                    case 4:
                        resposta_aluno.setResultadoDespesa(new MsgReq(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3(), requisicao_aluno.getVar4(), requisicao_aluno.getFuncionalidade()));
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_despesas();
                        break;

                    case 12: // Listar Alunos -> 1 - Aluno | 2 - Listar
                        resposta_aluno.listar_aluno();
                        resposta_aluno.setStatus(0);
                        break;

                    case 13: // Pesquisa Alunos -> 1 - Aluno | 3 - Pesquisa
                        System.out.println("Pesquisa feita por Chave: " + requisicao_aluno.getVar1() + "Valor: " + requisicao_aluno.getVar2());
                        resposta_aluno.listar_aluno_by(requisicao_aluno.getVar1(), requisicao_aluno.getVar2());
                        resposta_aluno.setStatus(0);
                        break;

                    case 14: // 1 - Aluno | 4 - Alteracao
                        resposta_aluno.alterar_aluno(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3());
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_aluno();
                        break;

                    case 22: // Listar Professores -> 2 - Professores | 2 - Listar
                        resposta_aluno.listar_professor();
                        resposta_aluno.setStatus(0);
                        break;

                    case 23: // Pesquisa Professores -> 2 - Professores | 3 - Pesquisa
                        System.out.println("Pesquisa feita por Chave: " + requisicao_aluno.getVar1() + " Valor: " + requisicao_aluno.getVar2());
                        resposta_aluno.listar_professor_by(requisicao_aluno.getVar1(), requisicao_aluno.getVar2());
                        resposta_aluno.setStatus(0);
                        break;

                    case 24: // Alteracao de Valor Professor -> 2 Professores | 4 - Alteracao
                        resposta_aluno.alterar_professor(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3());
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_professor();
                        break;

                    case 32: // Listar Cursos
                        resposta_aluno.listar_cursos();
                        resposta_aluno.setStatus(0);
                        break;

                    case 33: // Pesquisar
                        System.out.println("Pesquisa feita por Chave: " + requisicao_aluno.getVar1() + " Valor: " + requisicao_aluno.getVar2());
                        resposta_aluno.listar_cursos_by(requisicao_aluno.getVar1(), requisicao_aluno.getVar2());
                        resposta_aluno.setStatus(0);
                        break;

                    case 34: // Alteracao
                        resposta_aluno.alterar_cursos(requisicao_aluno.getVar1(), requisicao_aluno.getVar2(), requisicao_aluno.getVar3());
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_professor();
                        break;

                    case 42: // Listar Despesas
                        resposta_aluno.setStatus(0);
                        resposta_aluno.listar_despesas();
                        break;

                    case 43: // Pesquisar Despesas
                        resposta_aluno.listar_despesas_by(requisicao_aluno.getVar1(), requisicao_aluno.getVar2());
                        resposta_aluno.setStatus(0);
                        break;

                    case 51: // Lucro Atual
                        resposta_aluno.consulta_lucro();
                        resposta_aluno.setStatus(0);
                        break;

                    case 52: // Custos Professores
                        resposta_aluno.setStatus(0);
                        resposta_aluno.consulta_custo_professores();
                        break;

                    case 53: // Receita Total
                        resposta_aluno.setStatus(0);
                        resposta_aluno.consulta_receita();
                        break;

                    case 54: // Total com Despesas
                        resposta_aluno.setStatus(0);
                        resposta_aluno.consulta_despesas();
                        break;

                }

                // if se não for suportado.

                // SwithCase
                // If opt2 != 0, calcula a divisão, set status para 2;
                conexao.send(client_socket, resposta_aluno);
                try {
                    client_socket.close();
                    serversocket.close();
                } // desconexao
                catch (Exception e) {
                    System.out.println("N�o encerrou a conex�o corretamente" + e.getMessage());
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
            System.out.println("N�o fez conex�o" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
