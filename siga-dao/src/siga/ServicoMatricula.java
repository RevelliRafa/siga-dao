package siga;

import java.util.List;

/**
 * Código INICIAL da atividade — contém o problema PROPOSITAL a refatorar.
 *
 * PROBLEMA 1 — violação do SRP (Responsabilidade Única):
 * esta classe deveria cuidar apenas da REGRA DE NEGÓCIO da matrícula, mas
 * também monta e executa comandos SQL. São dois motivos distintos para mudar:
 * uma alteração na regra acadêmica e uma alteração na estrutura da tabela.
 *
 * PROBLEMA 2 — violação do DIP (Inversão de Dependência):
 * a classe depende diretamente da tecnologia de persistência (BancoSimulado,
 * que aqui faz o papel do driver do banco), em vez de depender de uma abstração.
 *
 * PROBLEMA 3 — duplicação:
 * observe que o mesmo bloco de busca aparece em matricular() e em
 * gerarRelatorio(). Uma mudança na tabela obriga a alterar os dois — e é fácil
 * esquecer um deles.
 *
 * Consequência prática: para testar a regra "média não pode ser negativa",
 * seria necessário um banco de dados disponível. Testar um "if" exigindo
 * infraestrutura é um forte sinal de design acoplado.
 *
 * Tarefa:
 *   - Etapa 2: definir a interface AlunoDAO com operações do domínio
 *     (inserir, buscarPorMatricula, listarTodos, atualizar, remover).
 *   - Etapa 3: implementar AlunoDAOMemoria usando um Map interno.
 *   - Etapa 4: fazer esta classe receber o AlunoDAO pelo construtor e remover
 *     todo o SQL daqui.
 *   - Etapa 5: demonstrar a troca de implementação sem alterar a regra.
 */
public class ServicoMatricula {

    private final AlunoDAO alunoDAO;

    public ServicoMatricula(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void matricular(Aluno aluno) {
        // --- regra de negócio ---
        if (aluno.getMedia() < 0 || aluno.getMedia() > 10) {
            throw new IllegalArgumentException("Média inválida: " + aluno.getMedia());
        }

        alunoDAO.inserir(aluno);
    }

    public void gerarRelatorio() {
        List<Aluno> alunos = alunoDAO.listarTodos();

        System.out.println("=== Relatório de Alunos ===");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}
    public void gerarRelatorio() {
        // Duplicação: o mesmo acesso a dados aparece aqui de novo.
        String sql = "SELECT nome, matricula, media FROM aluno";
        List<String> linhas = BancoSimulado.consultar(sql);

        System.out.println("=== Relatório de Alunos ===");
        for (String linha : linhas) {
            System.out.println(linha);
        }
    }
}
