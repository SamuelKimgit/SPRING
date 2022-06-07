select * from hr_member;

--입력
INSERT INTO hr_member (
    u_id,
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


DELETE FROM hr_member
WHERE
        u_id = :v0
    AND name = :v1
    AND passwd = :v2
    AND u_level = :v3
    AND login = :v4
    AND recommend = :v5
    AND email = :v6
    AND reg_dt = :v7;