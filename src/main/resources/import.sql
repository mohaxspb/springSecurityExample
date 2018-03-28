INSERT INTO users(name_first, name_second, name_third, username, password, avatar, enabled) VALUES('Ivan', 'Ivanov', 'Ivanovich', 'test@test.ru', '{noop}password', 'https://avatars0.githubusercontent.com/u/9077598', 'true');
INSERT INTO authorities(user_id,authority) VALUES(1,'ROLE_ADMIN');
INSERT INTO authorities(user_id,authority) VALUES(1,'ROLE_USER');