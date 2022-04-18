import pymysql
import ReadDBData

keys = ReadDBData.getLogindata()
datas = ReadDBData.getInputdata()
csvreader = ReadDBData.getCSVdata()

print("연결 중...")
conn = pymysql.connect(host = keys[0], user = keys[1], password = keys[2], db = keys[3], charset = 'utf8')

cur = conn.cursor()

sql = 'Insert Into `test1`(`name`, `time`) Values (%s, %s)'

for line in csvreader:
    vals = (line[1], line[2])
    cur.execute(sql, vals)

#sql = 'select * from test1'

conn.commit()

conn.close()
print("연결 종료")

#for row in cur:
#    print(row)