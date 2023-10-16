# 데이터베이스 수동 생성 명령어
```
CREATE DATABASE wanted DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER wanted@localhost IDENTIFIED BY 'wanted123';
CREATE USER wanted@'%' IDENTIFIED BY 'wanted123';

GRANT ALL PRIVILEGES ON wanted.* TO 'wanted'@'localhost';
GRANT ALL PRIVILEGES ON wanted.* TO 'wanted'@'%';

--test db
CREATE DATABASE wanted_test DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER wanted_test@localhost IDENTIFIED BY 'wanted_test123';
CREATE USER wanted_test@'%' IDENTIFIED BY 'wanted_test123';

GRANT ALL PRIVILEGES ON wanted_test.* TO 'wanted_test'@'localhost';
GRANT ALL PRIVILEGES ON wanted_test.* TO 'wanted_test'@'%';
```
