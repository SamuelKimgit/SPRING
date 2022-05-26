select * from hr_member;

--입력
INSERT INTO hr_member (
    u_id,SPRING

    name,
    passwd
) VALUES (
    :v0,
    :v1,
    :v2
);

--단건조회
SELECT
    u_id,
    name,
    passwd
FROM hr_member
WHERE u_id =:USER_ID;
SPRING
