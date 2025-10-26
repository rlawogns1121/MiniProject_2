erDiagram
    USER ||--o{ CATEGORY : has
    USER ||--o{ TRANSACTION : has
    USER ||--o{ BUDGET : has
    CATEGORY ||--o{ TRANSACTION : categorized_by

    USER {
      BIGINT   id PK
      VARCHAR  email UK
      VARCHAR  password_hash
      VARCHAR  nickname
    }

    CATEGORY {
      BIGINT  id PK
      BIGINT  user_id FK
      VARCHAR name
    }
    %% UNIQUE (user_id, name)

    TRANSACTION {
      BIGINT    id PK
      BIGINT    user_id FK
      BIGINT    category_id FK
      DATE      date
      INT       amount
      TEXT      memo
      DATETIME  created_at
      DATETIME  updated_at
      SMALLINT  year
      TINYINT   month
    }

    BUDGET {
      BIGINT    id PK
      BIGINT    user_id FK
      SMALLINT  year
      TINYINT   month
      INT       total_amount
      DATETIME  created_at
      DATETIME  updated_at
    }
    %% UNIQUE (user_id, year, month)