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

-- insert recruit
-- INSERT INTO recruit(id, position, reward, content, skill, company_id)
-- VALUES (1, "프론트엔드 개발자", 500000, "프론트엔드 개발자 채용합니다.", "vue.js", 2);
-- INSERT INTO recruit(id, position, reward, content, skill, company_id)
-- VALUES (2, "백엔드 주니어 개발자", 1000000, "백엔드 주니어 개발자 채용합니다.", "vue.js", 3);
-- INSERT INTO recruit(id, position, reward, content, skill, company_id)
-- VALUES (3, "Django 백엔드 개발자", 300000, "Django 백엔드 개발자 채용합니다.", "vue.js", 4);

