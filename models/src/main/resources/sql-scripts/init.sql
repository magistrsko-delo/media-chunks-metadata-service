INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_000.ts', 5.003);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_001.ts', 5.000);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('video-sample1', '1080p_002.ts', 4.000);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('sample2', '1080p_000.ts', 4.030);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('sample2', '720p_000.ts', 3.000);
INSERT INTO chunk (aws_bucket_name, aws_storage_name, length) VALUES ('sample2', '720p_001.ts', 3.000);


INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (1, '1920x1080', 1, 0);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (1, '1920x1080', 2, 1);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (1, '1920x1080', 3, 2);

INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (2, '1920x1080', 4, 1);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (2, '1920x1080', 5, 0);

INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (2, '1280x720', 5, 0);
INSERT INTO link_media_chunks (fk_media_id, resolution, fk_chunk_id, position) VALUES (2, '1280x720', 6, 1);


