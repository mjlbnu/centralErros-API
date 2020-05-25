CREATE TABLE IF NOT EXISTS level(
	id SERIAL,
	name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO level (name) values ('error');
INSERT INTO level (name) values ('warning');
INSERT INTO level (name) values ('information');
INSERT INTO level (name) values ('other');
