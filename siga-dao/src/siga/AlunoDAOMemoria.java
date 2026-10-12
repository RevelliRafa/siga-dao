package siga;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlunoDAOMemoria implements AlunoDAO {

    private final Map<String, Aluno> repositorio = new HashMap<>();

    @Override
    public void inserir(Aluno aluno) {
        repositorio.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return repositorio.get(matricula);
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(repositorio.values());
    }

    @Override
    public void atualizar(Aluno aluno) {
        repositorio.put(aluno.getMatricula(), aluno);
    }

    @Override
    public void remover(String matricula) {
        repositorio.remove(matricula);
    }
}