import pymysql
import ReadDBData

def getTime(putin):
    cutword = putin.split()
    ymd = cutword[0].split(".")
    hms = cutword[2].split(":")
    if (cutword[1] == "오전") or (hms[0] == "12"):
        pl = 0
    else:
        pl = 12
    
    hms[0] = int(hms[0]) + pl
    datetime = ymd[0] + "-" + ymd[1] + "-" + ymd[2] + " " + str(hms[0]) + ":" + hms[1] + ":" + "00"
    return datetime

keys = ReadDBData.getLogindata()
datas = ReadDBData.getInputdata()
csvreader = ReadDBData.getCSVdata()

print("연결 중...")
conn = pymysql.connect(host = keys[0], user = keys[1], password = keys[2], db = keys[3], charset = 'utf8')

cur = conn.cursor()

sql = 'Insert Into `onlyfacts`(`title`, `body`,`time`, `field`, `sourcelink`) Values (%s, %s,%s, %s,%s)'

for line in csvreader:
    timeval = getTime(line[2])

    vals = (line[0], line[1],timeval,line[3],line[4])
    cur.execute(sql, vals)

#sql = 'select * from test1'

conn.commit()

conn.close()
print("연결 종료")