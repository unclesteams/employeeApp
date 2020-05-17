INSERT INTO ea_employee (name, lastname, email) VALUES
  ('Lokesh', 'Gupta', 'abc@gmail.com'),
  ('Deja', 'Vu', 'xyz@email.com'),
  ('Caption', 'America', 'cap@marvel.com'),
  ('Johnny', 'Italy', 'j@marvel.it');

-- bcrypt hashed password with round = 10 pw was 'Password'
INSERT INTO passwords (email, password) VALUES
  ('abc@gmail.com', '$2y$10$hj8aIup1oTyIao6jAUneIuG0q.P/cw4aNopSAlLjRj7K1BY.pyOzO'),
  ('xyz@email.com', '$2y$10$hj8aIup1oTyIao6jAUneIuG0q.P/cw4aNopSAlLjRj7K1BY.pyOzO'),
  ('cap@marvel.com', '$2y$10$hj8aIup1oTyIao6jAUneIuG0q.P/cw4aNopSAlLjRj7K1BY.pyOzO'),
  ('j@marvel.it', '$2y$10$hj8aIup1oTyIao6jAUneIuG0q.P/cw4aNopSAlLjRj7K1BY.pyOzO');
