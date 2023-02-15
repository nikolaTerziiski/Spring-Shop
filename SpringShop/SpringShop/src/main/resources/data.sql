INSERT INTO roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, name)
VALUES (2, 'MASTER');
INSERT INTO roles (id, name)
VALUES (3, 'USER');

INSERT INTO categories (id, name)
VALUES (1, 'DENTAL');
INSERT INTO categories (id, name)
VALUES (2, 'MEDCONSUM');
INSERT INTO categories (id, name)
VALUES (3, 'EMERGENCY');
INSERT INTO categories (id, name)
VALUES (4, 'DISINFECTION');
INSERT INTO categories (id, name)
VALUES (5, 'DEFIBRILLATORS');


INSERT INTO users (id, email, password,username)
VALUES (1, 'admin@admin.com', '97e1ec5db28835ff6a5b5d3d646c3e589c096706d13df839d4b70364e0a21b715da62fb6f0d8914f', 'admin');
INSERT INTO users (id, email, password,username)
VALUES (1, 'users@user.com', '97e1ec5db28835ff6a5b5d3d646c3e589c096706d13df839d4b70364e0a21b715da62fb6f0d8914f', 'user');
INSERT INTO users (id, email, password,username)
VALUES (1, 'asd@asd.com', '97e1ec5db28835ff6a5b5d3d646c3e589c096706d13df839d4b70364e0a21b715da62fb6f0d8914f', 'asd');

INSERT INTO users_roles(user_id, roles_id)
VALUES (1,1);
INSERT INTO users_roles(user_id, roles_id)
VALUES (1,2);
INSERT INTO users_roles(user_id, roles_id)
VALUES (1,3);
INSERT INTO users_roles(user_id, roles_id)
VALUES (2,3);
INSERT INTO users_roles(user_id, roles_id)
VALUES (3,3);



