ALTER TABLE posts ADD COLUMN category_id INT,
    ADD CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE;