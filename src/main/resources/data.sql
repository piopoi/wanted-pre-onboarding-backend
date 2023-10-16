DELETE FROM company;
DELETE FROM member;
DELETE FROM recruit;

-- insert company
INSERT INTO company(id, name, country, city) VALUES (1, '원티드랩', '한국', '서울');
INSERT INTO company(id, name, country, city) VALUES (2, '원티드코리아', '한국', '부산');
INSERT INTO company(id, name, country, city) VALUES (3, '네이버', '한국', '판교');
INSERT INTO company(id, name, country, city) VALUES (4, '카카오', '한국', '판교');

-- insert member
INSERT INTO member(id, name) VALUES (1, '사용자1');
INSERT INTO member(id, name) VALUES (2, '사용자2');
INSERT INTO member(id, name) VALUES (3, '사용자3');
