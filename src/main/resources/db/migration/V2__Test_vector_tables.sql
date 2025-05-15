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

CREATE TABLE jira_issues
(
    id                     BIGSERIAL PRIMARY KEY,
    jira_sprint_id         BIGINT                  NOT NULL REFERENCES jira_sprints (id) ON DELETE CASCADE,
    raw_json               TEXT                    NOT NULL,
    openai_compatible_text TEXT                    NOT NULL,
    created_at             TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE questions
(
    id         BIGSERIAL PRIMARY KEY,
    question   TEXT                    NOT NULL,
    created_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE vector_models
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE vector_store_1536
(
    question_id     BIGINT NULL REFERENCES questions (id) ON DELETE CASCADE,
    jira_issue_id   BIGINT NULL REFERENCES jira_issues (id) ON DELETE CASCADE,
    vector_model_id BIGINT NOT NULL REFERENCES vector_models (id) ON DELETE CASCADE,
    embedding       VECTOR(1536) NOT NULL,
    UNIQUE (question_id, jira_issue_id, vector_model_id),
    CHECK (
        (question_id IS NOT NULL AND jira_issue_id IS NULL) OR
        (question_id IS NULL AND jira_issue_id IS NOT NULL)
        )
);

CREATE TABLE vector_store_3072
(
    question_id     BIGINT NULL REFERENCES questions (id) ON DELETE CASCADE,
    jira_issue_id   BIGINT NULL REFERENCES jira_issues (id) ON DELETE CASCADE,
    vector_model_id BIGINT NOT NULL REFERENCES vector_models (id) ON DELETE CASCADE,
    embedding       VECTOR(3072) NOT NULL,
    UNIQUE (question_id, jira_issue_id, vector_model_id),
    CHECK (
        (question_id IS NOT NULL AND jira_issue_id IS NULL) OR
        (question_id IS NULL AND jira_issue_id IS NOT NULL)
        )
);
