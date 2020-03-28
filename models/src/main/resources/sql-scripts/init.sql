INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_000.ts', 5.003);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_001.ts', 5.000);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_002.ts', 4.000);


INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id) VALUES (4, '1920x1080', 1);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id) VALUES (4, '1920x1080', 2);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id) VALUES (4, '1920x1080', 3);


