LOAD DATA INFILE '/Users/qqllzs/woori_project/final_project/woori-zip-BE/out/production/resources/data/consumption_analysis.csv'
INTO TABLE consumption_analysis
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(customer_type, book, car, cloth, culture, food, grocery, customer_count);