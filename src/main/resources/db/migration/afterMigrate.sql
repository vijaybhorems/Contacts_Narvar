SET search_path TO contacts;

INSERT INTO contact
    (id, version, name, email, profession)
SELECT 1, 0, 'John', 'john@example.com', 'Engineer'
WHERE
    NOT EXISTS (
        SELECT id FROM contact WHERE id = 1
    );

INSERT INTO contact
    (id, version, name, email, profession)
SELECT 2, 0, 'Jane', 'jane@example.com', 'Legal Advisor'
WHERE
    NOT EXISTS (
        SELECT id FROM contact WHERE id = 2
    );