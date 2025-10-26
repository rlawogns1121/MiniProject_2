MERGE INTO users (id, email, password_hash, nickname) KEY(id)
VALUES (1, 'demo@local.dev', '{noop}pass', '데모');

-- categories
MERGE INTO categories (id, user_id, name) KEY(id)
VALUES (1, 1, '식비'),
       (2, 1, '교통'),
       (3, 1, '기타');

-- budgets
MERGE INTO budgets (id, user_id, year, month, total_amount) KEY(id)
VALUES (1, 1, 2025, 10, 500000);

-- transactions
MERGE INTO transactions (id, user_id, category_id, date, amount, memo, year, month, created_at, updated_at) KEY(id)
VALUES
  (1, 1, 1, DATE '2025-10-25', 12000, '점심', 2025, 10, NOW(), NOW()),
  (2, 1, 2, DATE '2025-10-25',  1800, '버스비', 2025, 10, NOW(), NOW());