-- INSERT INTO PROFESSOR (ID, NOME, CPF, DATANASCIMENTO, EMAIL, CELULAR, MATRICULA) VALUES(1, 'João Silva', '123.456.789-00', '1980-05-15', 'joao.silva@gmail.com', '(79)98888-0000', 111);
-- INSERT INTO PROFESSOR (ID, NOME, CPF, DATANASCIMENTO, EMAIL, CELULAR, MATRICULA) VALUES (2, 'Maria Souza', '987.654.321-00', '1975-09-23', 'maria.souza@gmail.com', '(79)97777-0000', 222);
--
--
-- INSERT INTO DISCIPLINA (ID, NOME, NUMEROCREDITOS) VALUES(10, 'Programação I', 4);
-- INSERT INTO DISCIPLINA (ID, NOME, NUMEROCREDITOS) VALUES(20, 'Programação II', 4);
-- INSERT INTO DISCIPLINA (ID, NOME, NUMEROCREDITOS) VALUES(30, 'Programação III', 4);
-- INSERT INTO DISCIPLINA (ID, NOME, NUMEROCREDITOS) VALUES(40, 'Banco de Dados I', 4);
-- INSERT INTO DISCIPLINA (ID, NOME, NUMEROCREDITOS) VALUES(50, 'Banco de Dados II', 4);
--


INSERT INTO PROFESSOR (ID, NOME, CPF, DATANASCIMENTO, EMAIL, CELULAR, MATRICULA, APELIDO) VALUES(1, 'Glauco', '167.832.820-07','2016-03-30', 'glaucoluiz@gmail.com', '(79)98888-8887', '111', 'Ninja');
INSERT INTO PROFESSOR (ID, NOME, CPF, DATANASCIMENTO, EMAIL, CELULAR, MATRICULA, APELIDO) VALUES(2, 'Paulo', '987.654.321-00', '1975-09-23', 'pauloamaral@gmail.com', '(79)98888-8882', '222', 'Guanabara');

INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS,IDPROFESSOR) VALUES('Programação I', 4, 1);
INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS, IDPROFESSOR) VALUES('Lógica', 8, 2);
INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS, IDPROFESSOR) VALUES('Inteligência Artificial', 6, 2);

INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS) VALUES('Programação III', 4);
INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS, IDPROFESSOR) VALUES('Banco de Dados I', 4, 1);
INSERT INTO DISCIPLINA (NOME, NUMEROCREDITOS, IDPROFESSOR) VALUES('Banco de Dados II', 2, 1);

-- INSERT INTO ALUNO (APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME) VALUES('Lelê', '(79)98888-8888', '856.095.680-80', '2009-11-30', 'lele_maria@gmail.com', '123','Letícia Maria Leite de Carvalho', 1);
-- INSERT INTO ALUNO (APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES('Lulu', '(79)99999-9999', '167.832.820-07', '2016-03-30', 'lulu_maria@gmail.com', '456','Luiza Maria Leite de Carvalho', 1);
-- INSERT INTO ALUNO (APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES('Lala', '(79)77999-9999', '168.832.820-07', '2016-03-30', 'lala_maria@gmail.com', '789','Lara Cardoso Santos ', 4);


INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(100, 'Champs', '(79)98888-8888', '856.095.680-80', '2009-11-30', 'osvaldo@gmail.com', '123','Osvaldo Henrique', 1);
INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(200, 'Gordin', '(79)98888-8810', '856.095.060-80', '2009-11-29', 'kaua@gmail.com', '1234','Kauan Vitor', 1);
INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(201, 'Lelê', '(79)98765-4321', '678.954.320-12', '2008-05-17', 'leonardo.souza@gmail.com', '5678', 'Leonardo Souza', 4);
INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(202, 'Nina', '(79)99876-5432', '234.567.890-01', '2010-07-22', 'nina.santos@gmail.com', '9012', 'Marina Santos', 2);
INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(203, 'Beto', '(79)98765-6789', '123.456.789-00', '2007-03-11', 'roberto.oliveira@gmail.com', '3456', 'Roberto Oliveira', 2);
INSERT INTO ALUNO (ID, APELIDO, CELULAR, CPF, DATANASCIMENTO, EMAIL, MATRICULA, NOME, IDDISCIPLINA) VALUES(204, 'Leo', '(79)98765-4322', '456.789.012-34', '2008-01-15', 'leonardo.silva@gmail.com', '12345', 'Leonardo Silva', 3);

-- -- INSERT INTO TURMA (ID, DATAINICIO, DATAFIM, IDPROFESSOR, IDDISCIPLINA) VALUES (3, '2024-08-01', NULL, 2, 30);
-- -- INSERT INTO TURMA (ID, DATAINICIO, DATAFIM, IDPROFESSOR, IDDISCIPLINA) VALUES (4, '2024-08-01', NULL, 2, 40);
--
--
-- INSERT INTO MATRICULA (ID, IDALUNO, IDTURMA) VALUES (1, 100, 1);
-- INSERT INTO MATRICULA (ID, IDALUNO, IDTURMA) VALUES (2, 200, 2);
-- INSERT INTO MATRICULA (ID, IDALUNO, IDTURMA) VALUES (3, 100, 3);
-- INSERT INTO MATRICULA (ID, IDALUNO, IDTURMA) VALUES (4, 200, 4);
--
