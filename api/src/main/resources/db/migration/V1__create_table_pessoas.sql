CREATE TABLE pessoas (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
	nome_completo VARCHAR(255),
    data_nascimento DATE,
    PRIMARY KEY (id)
);