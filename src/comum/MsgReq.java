package comum;

import java.io.Serializable;
import java.util.UUID;

public class MsgReq implements Serializable{
    private String id;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private int funcionalidade;

    public MsgReq() {
    }

    public MsgReq(String var1, String var2, String var3, String var4, int funcionalidade) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.funcionalidade = funcionalidade;
        this.id = UUID.randomUUID().toString().substring(0, 5);
    }

    public String getId() {
        return id;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }

    public int getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(int funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public String toStringAluno() {
        return "Aluno Registrado{" +
                "id='" + id + '\'' +
                ", Nome do Aluno='" + var1 + '\'' +
                ", Curso='" + var2 + '\'' +
                ", Valor='" + var3 + '\'' +
                ", Status='" + var4 + '\'' +
                '}';
    }

    public String toStringProf() {
        return "Professor Registrado{" +
                "id='" + id + '\'' +
                ", Nome do Professor='" + var1 + '\'' +
                ", Curso='" + var2 + '\'' +
                ", Salario='" + var3 + '\'' +
                ", Status='" + var4 + '\'' +
                '}';
    }

    public void toTable(){

    }
}
