package p169624.ft.unicamp.br.aula02.alunos;


public class Aluno {
    String descricao;

    int foto;

    String nome;

    public Aluno(String paramString1, int paramInt, String paramString2) {
        this.nome = paramString1;
        this.foto = paramInt;
        this.descricao = paramString2;
    }

    public String getDescricao() { return this.descricao; }

    public int getFoto() { return this.foto; }

    public String getNome() { return this.nome; }

    public void setDescricao(String paramString) { this.descricao = paramString; }

    public void setFoto(int paramInt) { this.foto = paramInt; }

    public void setNome(String paramString) { this.nome = paramString; }
}
