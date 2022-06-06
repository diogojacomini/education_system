package comum;

import java.io.Serializable;
import java.util.ArrayList;

public class MsgResp implements Serializable{
    private MsgReq resultado;
    private int status;
    private ArrayList<MsgReq> numeros = new ArrayList<MsgReq>();
    private ArrayList<MsgReq> profes = new ArrayList<MsgReq>();
    private ArrayList<MsgReq> cursos = new ArrayList<MsgReq>();

    public MsgResp() {
    }

    public MsgResp(MsgReq resultado, int status) {
        this.resultado = resultado;
        this.status = status;
    }

    public MsgReq getResultado() {
        return resultado;
    }

    public void setResultado(MsgReq resultado) {
        this.resultado = resultado;
        numeros.add(resultado);
    }
    public void setResultadoProf(MsgReq resultado){
        this.resultado = resultado;
        profes.add(resultado);
    }
    public void setResultadoCurso(MsgReq resultado){
        this.resultado = resultado;
        cursos.add(resultado);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void listar_aluno() {
        System.out.println("Quantidade de Alunos: " + numeros.size());

        int max_value_v1 = 6;
        int max_value_v2 = 7;
        for(MsgReq n: numeros) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }
        //Id / nome / curso(s) / valor / status
        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Curso", "Valor Mens", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        for(MsgReq student: numeros){
            System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                    student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
            System.out.println(); // value
        }

        System.out.println("+" + finalResult + "+"); // final

    }

    public void listar_aluno_by(String variable, String valor){
        variable = variable.toLowerCase();
        valor = valor.toLowerCase();

        int max_value_v1 = 6;
        int max_value_v2 = 7;
        for(MsgReq n: numeros) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }

        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Curso", "Valor Mens", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        switch (variable){
            case "id":
                for(MsgReq student: this.numeros) {
                    if (student.getId().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "nome":
                for(MsgReq student: this.numeros) {
                    if (student.getVar1().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "curso":
                for(MsgReq student: this.numeros) {
                    if (student.getVar2().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "status":
                for(MsgReq student: this.numeros) {
                    if (student.getVar4().toLowerCase().equalsIgnoreCase(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
        }
        System.out.println("+" + finalResult + "+"); // final
    }

    public void alterar_aluno(String id, String variable, String valor){
        variable = variable.toLowerCase();

        for(MsgReq n: this.numeros){
            if (n.getId().contains(id)){
                switch (variable){
                    case "nome":
                        n.setVar1(valor);
                        break;
                    case "curso":
                        n.setVar2(valor);
                        break;
                    case "valor":
                        n.setVar3(valor);
                        break;
                    case "status":
                        n.setVar4(valor);
                        break;
                }
            }
        }
    }

    public void listar_professor() {
        System.out.println("Quantidade de Professores: " + profes.size());

        int max_value_v1 = 6;
        int max_value_v2 = 7;
        for(MsgReq n: profes) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }
        //Id / nome / curso(s) / valor / status
        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Cursos", "Salario", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        for(MsgReq student: profes){
            System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                    student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
            System.out.println(); // value
        }

        System.out.println("+" + finalResult + "+"); // final
    }

    public void listar_professor_by(String variable, String valor){
        variable = variable.toLowerCase();
        valor = valor.toLowerCase();

        int max_value_v1 = 6;
        int max_value_v2 = 7;
        for(MsgReq n: numeros) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }

        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Curso", "Salario", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        switch (variable){
            case "id":
                for(MsgReq student: this.numeros) {
                    if (student.getId().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "nome":
                for(MsgReq student: this.numeros) {
                    if (student.getVar1().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "curso":
                for(MsgReq student: this.numeros) {
                    if (student.getVar2().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "status":
                for(MsgReq student: this.numeros) {
                    if (student.getVar4().toLowerCase().equalsIgnoreCase(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
        }
        System.out.println("+" + finalResult + "+"); // final
    }

    public void alterar_professor(String id, String variable, String valor){
        variable = variable.toLowerCase();

        for(MsgReq n: this.profes){
            if (n.getId().contains(id)){
                switch (variable){
                    case "nome":
                        n.setVar1(valor);
                        break;
                    case "curso":
                        n.setVar2(valor);
                        break;
                    case "salario":
                        n.setVar3(valor);
                        break;
                    case "status":
                        n.setVar4(valor);
                        break;
                }
            }
        }
    }
    public void listar_cursos() {
        System.out.println("Quantidade de Cursos: " + cursos.size());

        int max_value_v1 = 6;
        int max_value_v2 = 12;
        for(MsgReq n: cursos) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }
        //Id / Nome do Curso / Carga Horaria / Modabilidade / Status
        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Carga Hrs.", "Modabil.", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        for(MsgReq student: cursos){
            System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                    student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
            System.out.println(); // value
        }

        System.out.println("+" + finalResult + "+"); // final
    }

    public void listar_cursos_by(String variable, String valor){
        variable = variable.toLowerCase();
        valor = valor.toLowerCase();

        int max_value_v1 = 6;
        int max_value_v2 = 12;
        for(MsgReq n: cursos) {
            if(n.getVar1().length() > max_value_v1){
                max_value_v1 = n.getVar1().length();
            }
            if(n.getVar2().length() > max_value_v2){
                max_value_v2 = n.getVar2().length();
            }
        }

        String string_line = "-";
        String finalResult = string_line.repeat(14+5+max_value_v1+max_value_v2+10+10);

        System.out.println("+" + finalResult + "+"); // inicio

        System.out.printf("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |", "ID", "Nome", "Carga Hrs.", "Modabil.", "Status");
        System.out.println();
        System.out.printf("| %5s " + "+ %"+max_value_v1+"s " + "+ %"+max_value_v2+"s " + "+ %10s " + "+ %10s |",
                string_line.repeat(5), string_line.repeat(max_value_v1), string_line.repeat(max_value_v2), string_line.repeat(10), string_line.repeat(10));
        System.out.println(); // sep

        switch (variable){
            case "id":
                for(MsgReq student: this.cursos) {
                    if (student.getId().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "nome":
                for(MsgReq student: this.cursos) {
                    if (student.getVar1().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "modalidade":
                for(MsgReq student: this.cursos) {
                    if (student.getVar2().toLowerCase().contains(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
            case "status":
                for(MsgReq student: this.cursos) {
                    if (student.getVar4().toLowerCase().equalsIgnoreCase(valor)) {
                        System.out.format("| %5s " + "| %"+max_value_v1+"s " + "| %"+max_value_v2+"s " + "| %10s " + "| %10s |",
                                student.getId(), student.getVar1(), student.getVar2(), student.getVar3(), student.getVar4());
                        System.out.println(); // value
                    }
                }
                break;
        }
        System.out.println("+" + finalResult + "+"); // final
    }

    public void alterar_cursos(String id, String variable, String valor){
        variable = variable.toLowerCase();

        for(MsgReq n: this.cursos){
            if (n.getId().contains(id)){
                switch (variable){
                    case "nome":
                        n.setVar1(valor);
                        break;
                    case "carga":
                        n.setVar2(valor);
                        break;
                    case "modalidade":
                        n.setVar3(valor);
                        break;
                    case "status":
                        n.setVar4(valor);
                        break;
                }
            }
        }
    }
}
