CREATE TABLE companies
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT                    NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE jira_boards
(
    id         BIGSERIAL PRIMARY KEY,
    company_id BIGINT                  NOT NULL REFERENCES companies (id) ON DELETE CASCADE,
    name       TEXT                    NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE jira_sprints
(
    id            BIGSERIAL PRIMARY KEY,
    jira_board_id BIGINT                  NOT NULL REFERENCES jira_boards (id) ON DELETE CASCADE,
    name          TEXT                    NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE jira_ticket_embeddings
(
    id                     BIGSERIAL PRIMARY KEY,
    jira_sprint_id         BIGINT                  NOT NULL REFERENCES jira_sprints (id) ON DELETE CASCADE,
    raw_json               TEXT                    NOT NULL,
    openai_compatible_text TEXT                    NOT NULL,
    embedding1536          VECTOR(1536)            NULL,
    embedding3072          VECTOR(3072)            NULL,
    created_at             TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE questions
(
    id            BIGSERIAL PRIMARY KEY,
    question      TEXT                    NOT NULL,
    embedding1536 VECTOR(1536)            NULL,
    embedding3072 VECTOR(3072)            NULL,
    created_at    TIMESTAMP DEFAULT NOW() NOT NULL
);
