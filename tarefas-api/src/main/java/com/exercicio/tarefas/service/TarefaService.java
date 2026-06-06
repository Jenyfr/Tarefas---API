package com.exercicio.tarefas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.exercicio.tarefas.dto.TarefaRequest;
import com.exercicio.tarefas.exception.TarefaNaoEncontradaException;
import com.exercicio.tarefas.model.Tarefa;

@Service
public class TarefaService {

    private final Map<Long, Tarefa> tarefas = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    public List<Tarefa> listarTodas() {
        return new ArrayList<>(tarefas.values());
    }

    public Tarefa buscarPorId(Long id) {
        Tarefa tarefa = tarefas.get(id);

        if (tarefa == null) {
            throw new TarefaNaoEncontradaException(id);
        }

        return tarefa;
    }

    public Tarefa criar(TarefaRequest request) {
        Long id = proximoId.getAndIncrement();
        Boolean concluida = request.getConcluida() != null ? request.getConcluida() : false;

        Tarefa tarefa = new Tarefa(id, request.getTitulo(), request.getDescricao(), concluida);
        tarefas.put(id, tarefa);

        return tarefa;
    }

    public Tarefa atualizar(Long id, TarefaRequest request) {
        buscarPorId(id);

        Boolean concluida = request.getConcluida() != null ? request.getConcluida() : false;
        Tarefa tarefaAtualizada = new Tarefa(id, request.getTitulo(), request.getDescricao(), concluida);
        tarefas.put(id, tarefaAtualizada);

        return tarefaAtualizada;
    }

    public void excluir(Long id) {
        buscarPorId(id);
        tarefas.remove(id);
    }
}
