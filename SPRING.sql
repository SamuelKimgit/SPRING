select * from hr_member;

--�Է�
INSERT INTO hr_member (
    u_id,SPRING

    name,
    passwd
) VALUES (
    :v0,
    :v1,
    :v2
);

--�ܰ���ȸ
SELECT
    u_id,
    name,
    passwd
FROM hr_member
WHERE u_id =:USER_ID;
SPRING
