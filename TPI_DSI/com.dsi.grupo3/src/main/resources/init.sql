

DROP TABLE IF EXISTS maridajes CASCADE;
CREATE TABLE maridajes (
  mid                   INTEGER        AUTO_INCREMENT NOT NULL,
  nombre              VARCHAR(64)                     NOT NULL,
  descripcion                VARCHAR(64)                     NOT NULL,
  PRIMARY KEY (mid),
  UNIQUE (nombre)
);



INSERT INTO maridajes (nombre, descripcion) VALUES
('Maridaje 1', 'Perfecto con carnes rojas y quesos fuertes.'),
('Maridaje 2', 'Ideal con pescados y mariscos frescos.');


/*
SELECT * FROM maridajes;*/
