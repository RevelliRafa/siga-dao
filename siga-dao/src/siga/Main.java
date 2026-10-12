package siga;

/**
 * Ponto de entrada do SIGA (código INICIAL da atividade da Aula 7).
 *
 * O programa FUNCIONA: matricula alunos e gera o relatório. Mas repare na
 * saída: o ServicoMatricula está emitindo comandos SQL, ou seja, a regra de
 * negócio conhece a tecnologia de persistência. Sua tarefa é extrair essa
 * responsabilidade para um DAO.
 */
package siga;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIGA — Demonstração Padrão DAO ===");

        // 1. Instanciação do DAO em memória
        AlunoDAO daoMemoria = new AlunoDAOMemoria();

        // 2. Injeção de dependência no serviço de domínio
        ServicoMatricula servico = new ServicoMatricula(daoMemoria);

        // 3. Matrícula com dados válidos
        System.out.println("\n--- Matriculando alunos ---");
        Aluno aluno1 = new Aluno("Ana Silva", "RA12345", 8.5);
        Aluno aluno2 = new Aluno("Carlos Souza", "RA67890", 9.0);

        servico.matricular(aluno1);
        servico.matricular(aluno2);

        // 4. Relatório extraído via DAO
        servico.gerarRelatorio();

        // 5. Teste da regra de negócio (sem precisar de infraestrutura de banco)
        System.out.println("\n--- Teste de validação de regra de negócio (média inválida) ---");
        try {
            Aluno alunoInvalido = new Aluno("Aluno Teste", "RA00000", -1.0);
            servico.matricular(alunoInvalido);
        } catch (IllegalArgumentException e) {
            System.out.println("Regra validada com sucesso sem banco: " + e.getMessage());
        }
    }
}