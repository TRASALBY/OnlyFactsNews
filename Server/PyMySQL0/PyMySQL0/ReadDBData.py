import csv

def getLogindata():
    fname = "DBlogin.txt"

    f = open(fname, 'r')

    lines = f.readlines()
    keys = []
    f.close()

    for i in lines:
        a = i.split()
        keys.append(a[2])

    return keys

def getInputdata():
    fname = "InputData.txt"

    with open(fname, 'r') as f:
        lines = f.read()
        keys = lines.split('\n')

    return keys

def getCSVdata():
    fname = "test_naver_news.csv"
    csvre = []
    
    with open(fname, 'r') as f:
        rdr = csv.reader(f)
        for line in rdr:
            csvre.append(line)

    return csvre
