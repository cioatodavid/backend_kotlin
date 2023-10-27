-- Insert Users
INSERT INTO users (username, password) VALUES ('user1', 'password1');
INSERT INTO users (username, password) VALUES ('user2', 'password2');

-- Insert Schemas
INSERT INTO schemas (name, user_id, created_at, updated_at) VALUES ('schema1', 1, 1657891200000, 1657891200000);
INSERT INTO schemas (name, user_id, created_at, updated_at) VALUES ('schema2', 2, 1657891200000, 1657891200000);

-- Insert Entities
INSERT INTO entities (name, schema_id, positionx, positiony) VALUES ('entity1', 1, 10.5, 20.5);
INSERT INTO entities (name, schema_id, positionx, positiony) VALUES ('entity2', 1, 15.5, 25.5);
INSERT INTO entities (name, schema_id, positionx, positiony) VALUES ('entity3', 2, 30.5, 40.5);

-- Insert Attributes
INSERT INTO attributes (name, data_type, size, is_primary_key, is_foreign_key, is_nullable, is_unique, default_value, entity_id) VALUES ('attribute1', 'VARCHAR', 255, true, false, false, true, 'default', 1);
INSERT INTO attributes (name, data_type, size, is_primary_key, is_foreign_key, is_nullable, is_unique, default_value, entity_id) VALUES ('attribute2', 'INTEGER', 0, false, false, false, false, null, 1);
INSERT INTO attributes (name, data_type, size, is_primary_key, is_foreign_key, is_nullable, is_unique, default_value, entity_id) VALUES ('attribute3', 'TEXT', 0, false, false, true, false, null, 2);
