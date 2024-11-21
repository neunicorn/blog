CREATE TABLE comments(
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL ,
    body TEXT NOT NULL,
    created_at BIGINT NOT NULL,
    CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts(id)
) ENGINE=InnoDB