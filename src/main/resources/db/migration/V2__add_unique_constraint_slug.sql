ALTER TABLE posts
    ADD CONSTRAINT unique_slug UNIQUE(slug);