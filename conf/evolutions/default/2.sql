# --- !Ups  必要なスキーマの変換方法の記述
# --- Keep Table
INSERT INTO keep (text)
  VALUES ("平日の学習時間を3時間確保できた");

# --- Problem Table 
INSERT INTO problem (text)
  VALUES ("複数テーブルの結合が必要なSQLレポート作成に、時間がかかった");

# --- Try Table 
INSERT INTO try (problem_id, text)
  VALUES (1, "「スッキリわかるSQL入門」の1〜3章を読み直す");

# --- Try_Task Table 
INSERT INTO try_task (try_id, text)
  VALUES (1, "1章");

INSERT INTO try_task (try_id, text)
  VALUES (1, "2章");

# --- Tag Table 
INSERT INTO tag (tag_color_id, name)
  VALUES (1, "自己研鑽");

INSERT INTO tag (tag_color_id, name)
  VALUES (2, "SQL");

# --- Tag_color Table 
INSERT INTO tag_color (color)
  VALUES ("#3696a9");

INSERT INTO tag_color (color)
  VALUES ("#ff7f50");

# --- Keep_Tag Table 
INSERT INTO keep_tag (tag_id, keep_id)
  VALUES (1, 1);

# --- Problem_Tag Table 
INSERT INTO problem_tag (tag_id, problem_id)
  VALUES (2, 1);

# --- Try_Tag Table 
INSERT INTO try_tag (tag_id, try_id)
  VALUES (2, 1);

# --- !Downs  Upsの変換をもとに戻す方法の記述
DROP TABLE keep, problem, try, try_task, keep_tag, problem_tag, try_tag, tag, tag_color;
