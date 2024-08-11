--カテゴリー

insert ignore into categories(id,name) values(1,"ラーメン"); 
insert ignore into categories(id,name) values(2,"カレー"); 
insert ignore into categories(id,name) values(3,"ハンバーガー"); 
insert ignore into categories(id,name) values(4,"和食"); 
insert ignore into categories(id,name) values(5,"洋食"); 
insert ignore into categories(id,name) values(6,"寿司"); 
insert ignore into categories(id,name) values(7,"イタリアン"); 
insert ignore into categories(id,name) values(8,"フレンチ"); 
insert ignore into categories(id,name) values(9,"バー"); 
insert ignore into categories(id,name) values(10,"居酒屋"); 
insert ignore into categories(id,name) values(11,"バル"); 
insert ignore into categories(id,name) values(12,"焼肉"); 
insert ignore into categories(id,name) values(13,"昆虫食"); 
insert ignore into categories(id,name) values(14,"沖縄料理"); 
insert ignore into categories(id,name) values(15,"中華料理"); 
insert ignore into categories(id,name) values(16,"タイ料理"); 
insert ignore into categories(id,name) values(17,"韓国料理"); 
insert ignore into categories(id,name) values(18,"ステーキ"); 
insert ignore into categories(id,name) values(19,"ピザ"); 
insert ignore into categories(id,name) values(20,"パスタ"); 
insert ignore into categories(id,name) values(21,"郷土料理"); 
insert ignore into categories(id,name) values(22,"そば"); 
insert ignore into categories(id,name) values(23,"うどん"); 
insert ignore into categories(id,name) values(24,"定食"); 
insert ignore into categories(id,name) values(25,"創作料理"); 
insert ignore into categories(id,name) values(26,"食べ放題"); 
insert ignore into categories(id,name) values(27,"スイーツ"); 
insert ignore into categories(id,name) values(28,"ケバブ"); 
insert ignore into categories(id,name) values(29,"お弁当"); 
insert ignore into categories(id,name) values(30,"惣菜"); 
insert ignore into categories(id,name) values(31,"魚料理"); 
insert ignore into categories(id,name) values(32,"肉料理"); 


--店舗情報
insert ignore into restaurants(id,name,price,category_id,description,address,capacity,evalues,evalues_double) values (1,"にこにこ食堂",1000,1,"おいしいラーメン提供します。","愛知県名古屋市東区",1,3,3.0);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity,evalues,evalues_double) values (2,"激辛カレー店",2000,2,"辛いカレーを提供します。","愛知県名古屋市西区",3,2,2.0);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity,evalues,evalues_double) values (3,"ラーメン中西",1000,1,"中華ラーメン提供します。","愛知県名古屋市緑区",4,4,4.0);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (4,"ナマステネパール",2000,2,"本場のカレー提供します。","インド",5);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (5,"モサバーガー",1500,3,"もさもさのハンバーガーを提供します。","愛知県名古屋市東区",6);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (6,"ドナルドバーガー",1500,3,"鴨肉バーガーが一押しです。","愛知県名古屋市名東区",1);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (7,"横浜商店",1000,1,"横浜家系ラーメン提供します。","愛知県名古屋市中村区",2);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (8,"ラーメン太郎",1500,1,"次郎系ラーメン提供します。","愛知県名古屋市守山区",3);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (9,"だめだめ食堂",500,1,"まずいラーメン提供します。","愛知県北名古屋市",4);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (10,"にこにこん食堂",2500,1,"おいしいラーメン提供します。","愛知県名古屋市東区",5);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (11,"一回軒",1000,2,"辛いカレーを提供します。","愛知県名古屋市西区",2);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (12,"ラーメン一番",3000,1,"中華ラーメン提供します。","愛知県名古屋市緑区",4);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (13,"ナマステネパール２号店",4000,2,"本場のカレー提供します。","ネパール",8);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (14,"足軽バーガー",2000,3,"サイドメニューが豊富です。","愛知県名古屋市東区",9);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (15,"クインバーガー",3000,3,"わっぱーバーガーが一押しです。","愛知県名古屋市名東区",6);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (16,"町山商店",1,3500,"家系ラーメン提供します。","愛知県名古屋市中村区",3);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (17,"ラーメン三郎",2000,1,"次郎系ラーメン提供します。","愛知県名古屋市守山区",3);
insert ignore into restaurants(id,name,price,category_id,description,address,capacity) values (18,"まずまず食堂",1000,1,"まずまずのラーメン提供します。","愛知県北名古屋市",2);

--定休日情報
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (1,1,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (2,2,true,true,true,true,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (3,3,true,true,false,false,false,true,true);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (4,4,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (5,5,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (6,6,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (7,7,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (8,8,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (9,9,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (10,10,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (11,11,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (12,12,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (13,13,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (14,14,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (15,15,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (16,16,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (17,17,false,false,false,false,false,false,false);
insert ignore into holiday(id,restaurant_id,mon,tue,wed,thr,fri,sat,sun) values (18,18,false,false,false,false,false,false,false);


-- roles
INSERT IGNORE INTO roles (id, role_name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, role_name) VALUES (2, 'ROLE_MEMBERSHIP');
INSERT IGNORE INTO roles (id, role_name) VALUES (3, 'ROLE_ADMIN');

--users
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (1, '侍 太郎', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1,true);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (2, '侍 花子', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2,true);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (3, '侍 義勝', 'yoshikatsu.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3,true);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (4, '侍 幸美', 'sachimi.samurai@example.com', 'password', 1, true);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (5, '侍 雅', 'miyabi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (6, '侍 正保',  'masayasu.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (7, '侍 真由美',  'mayumi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (8, '侍 安民',  'yasutami.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (9, '侍 章緒',  'akio.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (10, '侍 祐子','yuko.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (11, '侍 秋美','akimi.samurai@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, email, password, role_id, enabled) VALUES (12, '侍 信平', 'shinpei.samurai@example.com', 'password', 1, false);

--company
INSERT IGNORE INTO company (id, name, postal_code, address,president,established_at, capital,contents,employees) VALUES (1,'NAGOYAMESHI株式会社','101-0022','東京都千代田区神田練塀町300番地 住友不動産秋葉原駅前ビル5F','侍 太郎','2024-04-15',5000,'情報提供サービス',150);


--reviews
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled) VALUES (1,12,1,3,"普通です",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (2,2,1,4,"いいです",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (3,3,1,3,"good",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (4,4,1,1,"全然ダメ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (5,5,1,3,"まあまあ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (6,7,1,5,"よかった",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled) VALUES (7,8,1,3,"ふつう",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (8,9,1,3,"可もなく不可もなく",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (10,10,1,3,"ｂヴぁあいｂｖだｋｊｖｄヴぃｒんヴぁｗｖんまｖｌんヴぇらいヴぁｒんｖ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (11,11,1,3,"ｂヴぁあいｂｖだｋｊｖｄヴぃｒんヴぁｗｖんまｖｌんヴぇらいヴぁｒんｖ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (12,12,1,3,"ｂヴぁあいｂｖだｋｊｖｄヴぃｒんヴぁｗｖんまｖｌんヴぇらいヴぁｒんｖ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (13,1,1,3,"普通です",true);

INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (14,2,3,4,"いいです",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (15,2,2,4,"いいです",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (16,3,2,3,"good",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled) VALUES (17,4,2,1,"全然ダメ",true);
INSERT IGNORE INTO reviews(id,user_id,restaurant_id,evalue,review_comment,enabled)  VALUES (18,5,2,3,"まあまあ",true);
