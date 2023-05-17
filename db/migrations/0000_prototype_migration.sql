CREATE TABLE IF NOT EXISTS solutions (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    gameID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS programs (
    id BIGSERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS games (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    interactor VARCHAR NOT NULL,
    rules VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS CompilationReports (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    isSuccessful BOOLEAN NOT NULL,
    compilerMessage VARCHAR NOT NULL,
    path VARCHAR NOT NULL
);
