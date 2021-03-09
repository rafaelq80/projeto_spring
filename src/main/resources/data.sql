-- Participante 
INSERT INTO participantes (
        data_criacao,
        data_atualizacao,
        email,
        nome,
        observacoes
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'joao@email.com.br',
        'João',
        'Aluno interessado'
    );
INSERT INTO participantes (
        data_criacao,
        data_atualizacao,
        email,
        nome,
        observacoes
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'maria@email.com.br',
        'Maria',
        'Aluna hiperativa'
    );
INSERT INTO participantes (
        data_criacao,
        data_atualizacao,
        email,
        nome,
        observacoes
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'livia@email.com.br',
        'Lívia',
        'Aluna interessada'
    );
-- Turma
INSERT INTO turmas (
        data_criacao,
        data_atualizacao,
        descricao,
        tipo,
        participante_id
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'Turma Verde',
        'Turma Presencial',
        1
    );
INSERT INTO turmas (
        data_criacao,
        data_atualizacao,
        descricao,
        tipo,
        participante_id
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'Turma Amarela',
        'Turma Presencial',
        1
    );
INSERT INTO turmas (
        data_criacao,
        data_atualizacao,
        descricao,
        tipo,
        participante_id
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'Turma Vinho',
        'Turma Online',
        2
    );
INSERT INTO turmas (
        data_criacao,
        data_atualizacao,
        descricao,
        tipo,
        participante_id
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'Turma Azul',
        'Turma Presencial',
        2
    );
INSERT INTO turmas (
        data_criacao,
        data_atualizacao,
        descricao,
        tipo,
        participante_id
    )
VALUES (
        CURRENT_TIMESTAMP(),
        CURRENT_TIMESTAMP(),
        'Turma Branca',
        'Turma Online',
        3
    );