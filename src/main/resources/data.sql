DELETE
FROM photos;
DELETE
FROM contacts;
DELETE
FROM users;

INSERT INTO users (LAST_NAME, FIRST_NAME, PATRONYMIC, BIRTHDAY)
VALUES ('petrunov', 'anton', 'nikolaevich', '1984-11-17'),
       ('Sochnev', 'Ignat', 'Ivanovich', '2000-10-30'),
       ('Ivanov', 'Denis', null, '2000-01-01')

INSERT INTO contacts (user_id, type, VALUE)
VALUES (0, 'EMAIL', 'petrunov.ru@gmail.com'),
       (0, 'PHONE', '+7 (931) 22-11-019'),
       (1, 'EMAIL', 'ignat@fanat.ru'),
       (1, 'PHONE', '3333333');

INSERT INTO photos (user_id, photo_url)
VALUES (0, 'https://lh3.googleusercontent.com/Xp816EpaPnUsWlHok7YAPg0X3lu9cwfao9scHoATVqdw-WoZZPdYjtF3OCJFcqyIjTrC4StvxqdPqsFYY-sErkVwlki23jA7ElcRFttZ7lRAS6GXZWcpqkmHGeSFzhw5VtAxVUg8K3a8PbYhYKEv0C2XDBBECqZH_GlducLHaOkVsG7uFuLCpLvmgiAMi_cIbqKmeMHgj_lH6f4Tt5mIM42nlRRsc5i8x2PE66jQmpog2u4CU3ILPYae9wHpCufs2al9D4Q-HO3RHn9vdHLMZ-FHKwWeVlTwaQASfdMFRznkRuJ-WOrpZPnpc-VB_We3u2DTxTLvqsUik22IPaXNmoMlcQa3x9UhSH9AZWxAe0nnVEGLjpLNcH_M0fSbQqkQa9v5TERxRZgt8yFKvcpubdtDSmddKcF3imT4QMZSdUyLzdsxNQ_61w-4nEwxRjfqxpCp53Dd_r7-kwOS-hYbFCKJJXtc8T0r3eOR3QEG2u5LJqB2dTN9tD6XYj1wWkD6McHWeSGnPnM3538XeucCH7kGo9cP8bFDxVy87np-BIAJAeNn9WYqdi6XhEoT-2nwVsWF0spxcVvaA-Ju5s1RMWmEvf_wznLiCRyuoO0-iRlAKu0a6_92Gs4Gt6ce4VVLpuWKdiSTIwMxf8H1aXkVdwpm-Wk-xTdEixWLPfv2c46omcQW4tIva5qARGoHKjOmzII-rgv2rQ0EAVKkVGyyrK7JVRjPhuQebOTNX9qb_yICYg2N8pqb16LS_XTqpx-EMGUBTmwkTi2IHMamoqDhOQjeYlPqwF5GtNWgON6leGr0X57ARM-jZW1bVGxGVXBewI6Bf2ZHC8SzKX24-7bMRy_LpuDi8irhJzUB1JU_vofnsxCl7_8cXMwiwvM5SezhuxftysZHLNv3sEUaAq1HUKFE0SOgjwnffJEgMcM=w568-h757-no?authuser=0')