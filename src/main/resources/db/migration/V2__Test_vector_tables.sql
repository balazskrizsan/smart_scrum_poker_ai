CREATE TABLE companies
(
    id         BIGSERIAL PRIMARY KEY,
    name       TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE jira_boards
(
    id         BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL REFERENCES companies (id) ON DELETE CASCADE,
    name       TEXT   NOT NULL,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE jira_sprints
(
    id            BIGSERIAL PRIMARY KEY,
    jira_board_id BIGINT NOT NULL REFERENCES jira_boards (id) ON DELETE CASCADE,
    name          TEXT   NOT NULL,
    created_at    TIMESTAMP DEFAULT NOW()
);

CREATE TABLE jira_ticket_embeddings
(
    id             BIGSERIAL PRIMARY KEY,
    jira_sprint_id BIGINT NOT NULL REFERENCES jira_sprints (id) ON DELETE CASCADE,
    raw_json       TEXT,
    embedding1536  VECTOR(1536),
    embedding3072  VECTOR(3072),
    created_at     TIMESTAMP DEFAULT NOW()
);
