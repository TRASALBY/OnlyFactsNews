import pymysql
import ReadDBData

keys = ReadDBData.getDBdata()
datas = ReadDBData.getInputdata()

print("연결 중...")
conn = pymysql.connect(host = keys[0], user = keys[1], password = keys[2], db = keys[3], charset = 'utf8')

cur = conn.cursor()

#sql = 'insert into `test1`(`main`) Values (%s), (%s)'
#vals = (datas[0], datas[1])

#sql = 'select * from test1'

sql = 'call proctest'

cur.execute(sql)
conn.commit()

conn.close()
print("연결 종료")

for row in cur:
    print(row)