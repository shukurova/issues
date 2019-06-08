CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name TEXT UNIQUE NOT NULL
);

CREATE TABLE repositories (
                              id SERIAL PRIMARY KEY,
                              name TEXT NOT NULL,
                              user_id INTEGER NOT NULL
);

CREATE TABLE teams (
                       id SERIAL,
                       user_id INTEGER PRIMARY KEY,
                       repo_id INTEGER NOT NULL
);

CREATE TABLE issues (
                        id SERIAL PRIMARY KEY,
                        repo_id INTEGER,
                        name TEXT NOT NULL,
                        description TEXT NOT NULL,
                        date DATE DEFAULT CURRENT_DATE,
                        rate INTEGER DEFAULT 0,
                        tags TEXT[] NOT NULL DEFAULT ARRAY[]::TEXT[],
                        owner_id INTEGER NOT NULL,
                        assignment_id INTEGER NOT NULL
);