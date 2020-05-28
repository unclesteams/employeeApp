CREATE TABLE ea_employee (
  id SERIAL PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  email VARCHAR(250) UNIQUE NOT NULL,
  description VARCHAR(250)
);

CREATE TABLE passwords (
  id SERIAL PRIMARY KEY,
  email VARCHAR(250) UNIQUE NOT NULL,
  password VARCHAR(500) NOT NULL
);

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