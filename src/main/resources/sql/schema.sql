CREATE TABLE IF NOT EXISTS tasks
(
    -- BIGSERIAL を使うと、BIGINT かつ 自動採番になります
    id BIGSERIAL NOT NULL PRIMARY KEY,
    summary VARCHAR(256) NOT NULL,
    description TEXT, -- i を補完
    status VARCHAR(256) NOT NULL
);